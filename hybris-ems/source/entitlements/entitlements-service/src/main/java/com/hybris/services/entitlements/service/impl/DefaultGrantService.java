/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 *
 */
package com.hybris.services.entitlements.service.impl;

import static com.hybris.kernel.api.Restrictions.eq;
import static com.hybris.kernel.api.Restrictions.neq;
import static com.hybris.services.entitlements.condition.metered.MeteredConditionStrategy.EXECUTION_PARAMETER_QUANTITY;
import static com.hybris.services.entitlements.condition.metered.MeteredConditionStrategy.GRANT_PARAMETER_MAX_QUANTITY;
import static com.hybris.services.entitlements.condition.metered.MeteredConditionStrategy.PARAMETER_REMAINING_QUANTITY;

import com.hybris.kernel.api.CriteriaQuery;
import com.hybris.kernel.api.PersistenceManager;
import com.hybris.kernel.api.TypedRestriction;
import com.hybris.kernel.api.exceptions.ManagedObjectNotFoundException;
import com.hybris.services.entitlements.api.exceptions.ValidationException;
import com.hybris.services.entitlements.api.Actions;
import com.hybris.services.entitlements.api.ObjectNotFoundException;
import com.hybris.services.entitlements.api.status.Status;
import com.hybris.services.entitlements.condition.AbstractConditionStrategy;
import com.hybris.services.entitlements.condition.ActionHandler;
import com.hybris.services.entitlements.condition.ActionHandlerFactory;
import com.hybris.services.entitlements.conversion.DateTimeConverter;
import com.hybris.services.entitlements.domain.Condition;
import com.hybris.services.entitlements.domain.Criterion;
import com.hybris.services.entitlements.domain.Grant;
import com.hybris.services.entitlements.service.ConditionTypeFactory;
import com.hybris.services.entitlements.service.GrantService;
import com.hybris.services.entitlements.service.UserGrants;
import com.hybris.services.entitlements.sorting.GrantComparator;
import com.hybris.services.entitlements.sorting.GrantComparatorFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;


/**
 * Service provides access to the Persistence Manager.
 * <p>
 * This is default implementation
 */
public class DefaultGrantService implements GrantService
{

	private static final String CSV_SEPARATOR = ",";
	private static final String METERED_CONDITION_TYPE = "metered";

	private ConditionTypeFactory conditionTypeFactory;
	private GrantComparatorFactory grantComparatorFactory;
	private PersistenceManager persistenceManager;


	@Autowired
	private DateTimeConverter dateTimeConverter;
	private final Logger logger = LoggerFactory.getLogger(DefaultGrantService.class);

	@Override
	public int revokeGrants(final String userId, final String entitlementType, final String grantSource, final String grantSourceId)
	{
		final List<Grant> grants = getGrants(userId, entitlementType, grantSource, grantSourceId, null);
		for (final Grant grant : grants)
		{
			grant.setStatus(Status.REVOKED.name());
			// TODO add logging of found entitlements?
		}

		return grants.size();
	}

	@Override
	public List<Grant> getGrants(
			final String userId, final String entitlementType, final String grantSource,
			final String grantSourceId, final Status status)
	{
		if (userId == null)
		{
			throw new ValidationException("userId must be defined");
		}

		final List<TypedRestriction<Grant>> restrictions = new ArrayList<>(10);
		restrictions.add(eq(Grant.USERID, userId));

        if (status != null)
        {
			restrictions.add(eq(Grant.STATUS, status.name()));
        }
		else
		{
			restrictions.add(neq(Grant.STATUS, Status.REVOKED.name()));
		}

		if (entitlementType != null)
		{
			restrictions.add(eq(Grant.ENTITLEMENTTYPE, entitlementType));
		}
		if (grantSource != null)
		{
			restrictions.add(eq(Grant.GRANTSOURCE, grantSource));
		}
		if (grantSourceId != null)
		{
			restrictions.add(eq(Grant.GRANTSOURCEID, grantSourceId));
		}
		return get(restrictions);
	}

	@Override
	public Grant newGrant()
	{
		return persistenceManager.create(Grant.class);
	}

    @Override
    public Condition newCondition()
	{
        return persistenceManager.create(Condition.class);
    }

	@Override
	public void revokeGrant(final String grantId)
	{
        final Grant grant = getGrant(grantId);
		grant.setStatus(Status.REVOKED.name());
	}

	@Override
	public Grant getGrant(final String id)
	{
		try
		{
			final Grant grant = persistenceManager.getByIndex(Grant.ACCESS_KEY, id);
			if (Status.REVOKED.equals(Status.valueOf(grant.getStatus())))
			{
				throw new ObjectNotFoundException("The object with id = [" + id +"] doesn't exist.");
			}
			return grant;
		}
		catch (final ManagedObjectNotFoundException e)
		{
			throw new ObjectNotFoundException("The object with id = [" + id +"] doesn't exist.", e);
		}

	}

    @Override
    public Grant updateConditions(final String id, final List<Condition> conditions)
	{
        final Grant grant = getGrant(id);
		grant.setConditions(conditions);
		for (final Condition condition : conditions)
		{
			final ActionHandlerFactory handlerFactory = conditionTypeFactory.getExecutor(condition.getType());
			if (handlerFactory != null)
			{
				final ActionHandler initHandler = handlerFactory.getActionHandler(AbstractConditionStrategy.INTERNAL_ACTION_INIT);
				if (initHandler != null)
				{
					initHandler.execute(condition, null, grant);
				}
			}
		}
        return grant;
    }

	@Override
	public Grant updateGrantStatus(final String id, final Status status)
	{
		final Grant grant = getGrant(id);
		grant.setStatus(status.name());
		return grant;
	}

	@Override
	public UserGrants execute(
			final String action,
			final String userId,
			final String entitlementType,
			final List<Criterion> criteria,
			final String grantComparatorType)
	{
		final List<Grant> constGrants = getGrants(userId, entitlementType, null, null, Status.ACTIVE);
		final GrantComparator grantComparator = grantComparatorFactory.getGrantComparator(grantComparatorType);
		final List<Grant> grants = new ArrayList<>(constGrants.size());
		grants.addAll(constGrants);
		Collections.sort(grants, grantComparator);
		final XGrantList applicableItems = getApplicableItems(grants, criteria);
		final XGrantList effectiveItems = getEffectiveItems(applicableItems);

		if (action == null || Actions.CHECK.equals(action))
		{
			if (effectiveItems.isEmpty())
			{
				return new UserGrants(false, applicableItems.getGrants());
			}
			else
			{
				return new UserGrants(effectiveItems.getGrants());
			}
		}
		if (Actions.USE.equals(action))
		{
			final List<Grant> triggeredItems = trackUsage(effectiveItems);
			if (triggeredItems.isEmpty())
			{
				return new UserGrants(false, applicableItems.getGrants());
			}
			else
			{
				final List<String> logMessages = buildLoggerMessages(action, userId, entitlementType, triggeredItems, criteria);

				for(String logMessage : logMessages)
				{
					logger.info(logMessage);
				}
				return new UserGrants(true, triggeredItems);
			}
		}
		throw new ValidationException(String.format("Unknown action \"%s\"", action));
	}

	private List<String> buildLoggerMessages(final String action, final String userId, final String entitlementType,
			final List<Grant> triggeredItems,  final List<Criterion> criteria)
	{
		final Date currentDate = Calendar.getInstance().getTime();
		final String timestamp = dateTimeConverter.convertDateToString(currentDate);

		final List<String> messages = new ArrayList<>(triggeredItems.size());

		String quantity = null;
		if ((criteria != null) && (criteria.size() > 0))
		{
			for (Criterion criterion : criteria)
			{
				if(METERED_CONDITION_TYPE.equals(criterion.getType()))
				{
					quantity = criterion.getProperty(EXECUTION_PARAMETER_QUANTITY);
				}
			}
		}

		for(Grant item : triggeredItems)
		{
			final List<Condition> conditions = new ArrayList<>(item.getConditions());
			if(conditions.size() > 0)
			{
				for(Condition con : conditions)
				{
					 if(METERED_CONDITION_TYPE.equals(con.getType()))
					 {

						 final int remainingQuanitity = Integer.valueOf((String) item.getProperty(PARAMETER_REMAINING_QUANTITY));
						 final String maxQuantity = (String) con.getProperty(GRANT_PARAMETER_MAX_QUANTITY);
						 final Integer intQuanitity = Integer.valueOf(quantity);
						 if(remainingQuanitity < 0 && intQuanitity > Math.abs(remainingQuanitity))
						 {
							 final int quantityBeforeOverage =  intQuanitity + remainingQuanitity;
							 final int quantityAfterOverage = -1 * remainingQuanitity;
							 final String messageBeforeOverage = buildMessage(action, userId, entitlementType, timestamp,
									 String.valueOf(quantityBeforeOverage), item, 0, false, maxQuantity);
							 final String messageAfterOverage  = buildMessage(action, userId, entitlementType, timestamp,
									 String.valueOf(quantityAfterOverage), item, remainingQuanitity, true, maxQuantity);
							 messages.add(messageBeforeOverage);
							 messages.add(messageAfterOverage);
						 }
						 else
						 {
							 if(remainingQuanitity < 0)
							 {
								 final String message = buildMessage(action, userId, entitlementType, timestamp,
										 quantity, item, remainingQuanitity, true, maxQuantity);
								 messages.add(message);
							 }
							 else
							 {
								 final String message = buildMessage(action, userId, entitlementType, timestamp,
										 quantity, item, remainingQuanitity, false, maxQuantity);
								 messages.add(message);
							 }

						 }


					 }
				}
			}
		}
		return messages;
	}

	private String buildMessage(final String action, final String userId, final String entitlementType, final String timestamp,
			final String quantity, final Grant item, final int remainingQuanitity, final boolean overage, final String maxQuantity)
	{
		final StringBuilder message = new StringBuilder();
		message.append(action);
		message.append(CSV_SEPARATOR);
		message.append(timestamp);
		message.append(CSV_SEPARATOR);
		message.append(userId);
		message.append(CSV_SEPARATOR);
		message.append(item.getGrantId());
		message.append(CSV_SEPARATOR);
		message.append(entitlementType);
		message.append(CSV_SEPARATOR);
		message.append(maxQuantity);
		message.append(CSV_SEPARATOR);
		message.append(quantity);
		message.append(CSV_SEPARATOR);
		message.append(remainingQuanitity);
		message.append(CSV_SEPARATOR);
		message.append(overage);
		return message.toString();
	}

	private XGrantList getApplicableItems(final List<Grant> grants, final List<Criterion> criteria)
	{
		final XGrantList result = new XGrantList();
		for (final Grant grant : grants)
		{
			final Map<String, GrantComparisonItem> items = new HashMap<>();

			// Create item for each known condition type
            addCheckers(items, grant);
			// Fill the items with criteria
			addCriteria(items, criteria);
			// Fill the items with grant's conditions
			addConditions(items, grant);
			boolean valid = true;
			for (final GrantComparisonItem item : items.values())
			{
				if (!item.isApplicable(Actions.CHECK))
				{
					valid = false;
					break;
				}
			}
			if (valid)
			{
				result.add(items.values());
			}
		}

		return result;
	}

	private XGrantList getEffectiveItems(final XGrantList items)
	{
		final XGrantList result = new XGrantList();
		for (Collection<GrantComparisonItem> entry : items)
		{
			boolean valid = true;
			for (final GrantComparisonItem n : entry)
			{
				if (!n.execute(Actions.CHECK))
				{
					valid = false;
					break;
				}
			}
			if (valid)
			{
				result.add(entry);
			}
		}
		return result;
	}

	/**
	 * Tracks usage of entitlement(s).
	 *
	 * @param items grants which passed CHECK action
	 * @return grant condition that accepted the usage action
	 */
	private List<Grant> trackUsage(final List<Collection<GrantComparisonItem>> items)
	{
		final List<Grant> result = new ArrayList<>(items.size());
		for (final Collection<GrantComparisonItem> collection : items)
		{
			boolean valid = true;
			for (final GrantComparisonItem item : collection)
			{
				if (!item.execute(Actions.USE))
				{
					valid = false;
					// cancel changes made in object
					persistenceManager.detach(item.getGrant());
					break;
				}
			}
			if (valid)
			{
				result.add(collection.iterator().next().getGrant());
				return result;
			}
		}
		return result;
	}

	private void addConditions(final Map<String, GrantComparisonItem> items, final Grant grant)
	{
		if (!grant.getConditions().isEmpty())
		{
			for (final Condition condition : grant.getConditions())
			{
				final GrantComparisonItem item = items.get(condition.getType());
				if (item != null)
				{
					item.addCondition(condition);
				}
			}
		}
	}

	private void addCriteria(final Map<String, GrantComparisonItem> items, final List<Criterion> criteria)
	{
		for (final Criterion criterion : criteria)
		{
			final GrantComparisonItem item = items.get(criterion.getType());
			if (item != null)
			{
				item.addCriterion(criterion);
			}
		}
	}

	private void addCheckers(final Map<String, GrantComparisonItem> items, final Grant grant)
	{
		for (final String type : conditionTypeFactory.getKnownTypes())
		{
			final GrantComparisonItem item = new GrantComparisonItem();
			item.setActionHandlerFactory(conditionTypeFactory.getExecutor(type));
			item.setGrant(grant);
			item.setConditionType(type);
			items.put(type, item);
		}
	}

	/**
     * Filters entitlements with helps of complex WHERE clause produced by {@link Map} of parameters.
	 *
     * @param restrictions fields to search on. Take field names from {@link Grant} class.
     * Strict comparison is performed.
     * @return found entitlements
     */
	private List<Grant> get(final List<TypedRestriction<Grant>> restrictions) // TODO <T>
	{
		Assert.notEmpty(restrictions, "At least one parameter must be defined");
		return where(
				persistenceManager.createCriteriaQuery(Grant.class),
				restrictions).resultList();
	}

    /**
     * Builds complex where clause by specified list of restrictions
     * <p>
     * This method adds WHERE(restriction_1 and restriction_2 and .. and restriction_N)
     * @param criteriaQuery  old {@link CriteriaQuery}
     * @param restrictions the list of restrictions to be added
     * @return new {@link CriteriaQuery} based on old {@link CriteriaQuery}
     */
	private CriteriaQuery<Grant> where(final CriteriaQuery<Grant> criteriaQuery, final List<TypedRestriction<Grant>> restrictions)
	{
		CriteriaQuery<Grant> result = criteriaQuery.where(restrictions.get(0));
		for (int i = 1; i < restrictions.size(); i++)
		{
			result = result.and(restrictions.get(i));
		}
		return result;
	}

	@Required
	public void setPersistenceManager(final PersistenceManager persistenceManager)
	{
		this.persistenceManager = persistenceManager;
	}

	@Required
	public void setConditionTypeFactory(final ConditionTypeFactory conditionTypeFactory)
	{
		this.conditionTypeFactory = conditionTypeFactory;
	}

	@Required
	public void setGrantComparatorFactory(final GrantComparatorFactory grantComparatorFactory)
	{
		this.grantComparatorFactory = grantComparatorFactory;
	}

	private static class XGrantList extends ArrayList<Collection<GrantComparisonItem>>
	{
		List<Grant> getGrants()
		{
			final List<Grant> result = new ArrayList<>(size());
			for (int i = 0; i < size(); i++)
			{
				result.add(getGrant(i));
			}
			return result;
		}

		Grant getGrant(final int n)
		{
			return get(n).iterator().next().getGrant();
		}
	}
}

