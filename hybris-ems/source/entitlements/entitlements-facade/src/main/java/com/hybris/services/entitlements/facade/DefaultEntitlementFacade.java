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
package com.hybris.services.entitlements.facade;

import com.hybris.commons.conversion.ConversionException;
import com.hybris.kernel.api.PersistenceManager;
import com.hybris.kernel.api.exceptions.BatchUpdateViolationException;
import com.hybris.kernel.api.exceptions.PrimaryKeyViolationException;
import com.hybris.services.entitlements.api.exceptions.ServiceException;
import com.hybris.services.entitlements.api.exceptions.ValidationException;
import com.hybris.kernel.persistence.StaleStateException;
import com.hybris.services.entitlements.api.Actions;
import com.hybris.services.entitlements.api.EntitlementFacade;
import com.hybris.services.entitlements.api.ExecuteManyData;
import com.hybris.services.entitlements.api.ExecuteManyResult;
import com.hybris.services.entitlements.api.ExecuteResult;
import com.hybris.services.entitlements.api.GrantData;
import com.hybris.services.entitlements.api.status.Status;
import com.hybris.services.entitlements.condition.ConditionData;
import com.hybris.services.entitlements.condition.CriterionData;
import com.hybris.services.entitlements.conversion.EntitlementFacadeConverter;
import com.hybris.services.entitlements.domain.Condition;
import com.hybris.services.entitlements.domain.Criterion;
import com.hybris.services.entitlements.domain.Grant;
import com.hybris.services.entitlements.service.GrantService;
import com.hybris.services.entitlements.service.UserGrants;
import com.hybris.services.entitlements.validation.BatchExecutionValidatorData;
import com.hybris.services.entitlements.validation.ExecutionValidatorData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class hides collaboration between services, entitlements and conditions.
 * <p>
 * This is the default implementation
 * </p>
 */
public class DefaultEntitlementFacade implements EntitlementFacade
{
	private static final int UPDATE_ATTEMPT_TIME_OFFSET_DEFAULT = 25;

	private final Logger logger = LoggerFactory.getLogger(DefaultEntitlementFacade.class);

	private int maxUpdateAttempts = 3;
	private int updateAttemptTimeOffset = UPDATE_ATTEMPT_TIME_OFFSET_DEFAULT;

	private GrantService grantService;
	private EntitlementFacadeValidator validator;
	private EntitlementFacadeConverter converter;
	@Autowired
	private PlatformTransactionManager transactionManager;
	@Autowired
	private PersistenceManager persistenceManager;

	@Override
	@Transactional
	public GrantData createGrant(final GrantData data)
	{
		if (data == null)
		{
			throw new ValidationException("grantData must not be null");
		}
		validator.validateObject(data);
		Grant grant = null;
		final String id = UUID.randomUUID().toString();
		try
		{
			grant = converter.convert(data);
			grant.setGrantId(id);
			if (grant.getGrantTime() == null)
			{
				grant.setGrantTime(new Date());
			}
			persistenceManager.flush();
		}
		catch (final ConversionException e)
		{
			throw new ValidationException("Error converting DTO", e);
		}
 		catch (final PrimaryKeyViolationException e)
		{
			persistenceManager.detach(grant);
			logger.warn("Duplicate id generated: " + id);
			throw new ServiceException("Creation failed. Try again.", e);
		}
		grantService.updateConditions(id, new ArrayList<>(grant.getConditions()));
		return converter.convert(grant);
	}

	@Override
	public ExecuteResult execute(
			final String action,
			final String userId,
			final String entitlementType,
			final List<CriterionData> criterionData,
			final boolean details)
	{
		validator.validateObject(new ExecutionValidatorData(entitlementType, userId, criterionData, action));
		return executeWithoutValidation(action, userId, entitlementType, criterionData, details, null);
	}

	@Override
	@Transactional
	public ExecuteManyResult execute(
			final String action,
			final String userId,
			final List<ExecuteManyData> executeManyDataList,
			final Boolean details)
	{
		validator.validateObject(new BatchExecutionValidatorData(action, userId, executeManyDataList));
		if (!Actions.CHECK.equals(action))
		{
			throw new ValidationException("action: " + action + " is not allowed");
		}
		validator.validateObject(executeManyDataList);
		final ExecuteManyResult executeManyResult = new ExecuteManyResult();
		for (final ExecuteManyData executeManyData : executeManyDataList)
		{
			final ExecuteResult executeResult = execute(action, userId, executeManyData.getEntitlementType(),
					executeManyData.getCriterionDataList(), details);
			executeManyResult.addExecuteResult(executeResult);
		}
		return executeManyResult;
	}

	@Override
	@Transactional(readOnly = true)
	public List<GrantData> getGrants(final String userId, final String entitlementType,
			final String grantSource, final String grantSourceId)
	{
		// TODO US5974 requirements:
		// -user actions should be disabled
		// -admin actions are not affected
		// TODO so maybe we need separate method getEntitlementsAdmin/getEntitlementsUser? or add status argument to this method?
		final List<Grant> grants = grantService.getGrants(
                userId, entitlementType, grantSource, grantSourceId, null
        );

		return convertGrantsBack(grants);
	}

	@Override
	@Transactional
	public GrantData getGrant(final String grantId)
	{
		final Grant grant = grantService.getGrant(grantId);
		return converter.convert(grant);
	}

	@Override
	@Transactional
	public void revokeGrants(final String userId, final String entitlementType,
			final String grantSource, final String grantSourceId)
	{
		grantService.revokeGrants(userId, entitlementType, grantSource, grantSourceId);
	}

	@Override
	@Transactional
	public void revokeGrant(final String grantId)
	{
		grantService.revokeGrant(grantId);
	}

    @Override
	public GrantData updateGrantStatus(final String grantId, final Status status)
	{
		return multiAttemptedUpdate(new Functor<GrantData>()
		{
			@Override
			GrantData run()
			{
				final Grant grant = grantService.updateGrantStatus(grantId, status);
				return converter.convert(grant);
			}
		}, "Grant status wasn't updated due to concurrent operations", "Grant status update terminated");
    }

	@Override
	public GrantData updateConditions(final String grantId, final List<ConditionData> conditionDataList)
	{
		validator.validateConditions(conditionDataList);

		return multiAttemptedUpdate(new Functor<GrantData>()
		{
			@Override
			GrantData run()
			{
				final Grant grant = grantService.updateConditions(grantId, convertConditions(conditionDataList));
				return converter.convert(grant);
			}
		}, "Condition update failed due to concurrent operations", "Condition update terminated");
	}

	@Override
	public GrantData createGrantProperty(final String grantId, final String name, final String value)
	{
		validator.validateProperty(name, value);
		return multiAttemptedUpdate(new Functor<GrantData>()
		{
			@Override
			GrantData run()
			{
				final Grant grant = grantService.getGrant(grantId);
				if (grant.getProperty(name) != null)
				{
					throw new ValidationException("Property " + name + " already exist");
				}
				grant.setProperty(name, value);
				return converter.convert(grant);
			}
		}, "Property creation failed due to concurrent operations", "Property creation terminated");
	}

	@Override
	public GrantData deleteGrantProperty(final String grantId, final String name)
	{
		return multiAttemptedUpdate(new Functor<GrantData>()
		{
			@Override
			GrantData run()
			{
				final Grant grant = grantService.getGrant(grantId);
				grant.setProperty(name, null);
				return converter.convert(grant);
			}
		}, "Property creation failed due to concurrent operations", "Property creation terminated");
	}

	@Override
    public GrantData updateGrantProperty(final String grantId, final String name, final String value)
    {
		validator.validateProperty(name, value);

		return multiAttemptedUpdate(new Functor<GrantData>()
		{
			@Override
			GrantData run()
			{
				final Grant grant = grantService.getGrant(grantId);
				if (grant.getProperty(name) == null)
				{
					throw new ValidationException("Property " + name + " doesn't exist.");
				}
				grant.setProperty(name, value);
				return converter.convert(grant);
			}
		}, "Grant property update failed due to concurrent updates", "Grant property update terminated");
    }

	@Override
	public GrantData addGrantProperty(final String grantId, final String key, final int amountToAdd)
	{
		return multiAttemptedUpdate(new Functor<GrantData>()
		{
			@Override
			GrantData run()
			{
				final Grant grant = grantService.getGrant(grantId);
				final Object grantPropertyValueObject = grant.getProperty(key);
				if (grantPropertyValueObject == null)
				{
					throw new ValidationException("Property " + key + " doesn't exist.");
				}
				final int grantPropertyValueInt = converter.convertToInt(grantPropertyValueObject);
				final int newGrantPropertyValueInt = grantPropertyValueInt + amountToAdd;
				final String newGrantPropertyValueStr = String.valueOf(newGrantPropertyValueInt);
				validator.validateProperty(key, newGrantPropertyValueStr);
				grant.setProperty(key, newGrantPropertyValueStr);
				return converter.convert(grant);
			}
		}, "Grant property add failed due to concurrent updates", "Grant property add terminated");
	}

	private ExecuteResult executeWithoutValidation(final String action, final String userId,
			final String entitlementType, final List<CriterionData> criterionData, final boolean details,
			final String comparatorType)
	{
		final List<Criterion> criteria = convertCriteria(criterionData);

		return multiAttemptedUpdate(new Functor<ExecuteResult>()
		{
			@Override
			ExecuteResult run()
			{
				final UserGrants grants = grantService.execute(action, userId, entitlementType, criteria, comparatorType);
				final ExecuteResult xResult = new ExecuteResult(grants.isValid());
				if (details)
				{
					xResult.setGrantDataList(convertGrantsBack(grants.getRelatedGrants()));
				}
				return xResult;
			}
		}, "Grant update failed due to concurrent updates", "Grant update terminated");
	}

	@Override
	public GrantData deleteConditions(final String grantId, final String conditionType)
	{
		if (conditionType == null)
		{
			return updateConditions(grantId, Collections.<ConditionData>emptyList());
		}
		return multiAttemptedUpdate(new Functor<GrantData>()
		{
			@Override
			GrantData run()
			{
				final GrantData dto = getGrant(grantId);
				final List<ConditionData> oldConditions = dto.getConditions();
				final List<ConditionData> newConditions = new ArrayList<>(oldConditions.size());
				for (ConditionData condition : oldConditions)
				{
					if (!condition.getType().equals(conditionType))
					{
						newConditions.add(condition);
					}
				}
				final Grant model = grantService.updateConditions(grantId, convertConditions(newConditions));
				return converter.convert(model);
			}
		}, "Condition removing failed due to concurrent operations", "Condition removing terminated");
	}

	private List<Condition> convertConditions(final List<ConditionData> conditionDataList)
	{
		if (conditionDataList != null)
		{
			return conditionDataList.stream().map(converter::convert).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	private List<Criterion> convertCriteria(final List<CriterionData> criterionDataList)
	{
		if (criterionDataList != null)
		{
			return criterionDataList.stream().map(converter::convert).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	private List<GrantData> convertGrantsBack(final List<Grant> grants)
	{
		final List<GrantData> result = new ArrayList<>(grants.size());
		for (final Grant item : grants)
		{
			result.add(converter.convert(item));
		}
		return result;
	}

	@Required
	public void setGrantService(final GrantService grantServiceArg)
	{
		grantService = grantServiceArg;
	}

	@Required
	public void setValidator(final EntitlementFacadeValidator validatorArg)
	{
		validator = validatorArg;
	}

	@Required
	public void setConverter(final EntitlementFacadeConverter converter)
	{
		this.converter = converter;
	}

	public void setMaxUpdateAttempts(final int maxUpdateAttempts)
	{
		this.maxUpdateAttempts = maxUpdateAttempts;
	}

	public void setUpdateAttemptTimeOffset(final int updateAttemptTimeOffset)
	{
		this.updateAttemptTimeOffset = updateAttemptTimeOffset;
	}

	/**
	 * Executes "op" up to maxUpdateAttempts times in case of concurrency error.
	 *
	 * @param op what to execute
	 * @param maxAttemptMessage message to throw what max attempt count was reached
	 * @param terminationMessage message to throw in case of interrupt
	 * @param <T> type of result what returns "op"
	 * @return the result of successful "op" execution
	 */
	private <T> T multiAttemptedUpdate(final Functor<T> op, final String maxAttemptMessage, final String terminationMessage)
	{
		int attempt = maxUpdateAttempts;
		while (true)
		{
			final TransactionStatus session = transactionManager.getTransaction(null);
			try
			{
				final T result = op.run();
				transactionManager.commit(session);
				return result;
			}
			catch (final StaleStateException e)
			{
				attempt--;
				if (attempt <= 0)
				{
					throw new BatchUpdateViolationException(maxAttemptMessage, e);
				}
				try
				{
					logger.info("Concurrent update");
					Thread.sleep(new Random().nextInt(updateAttemptTimeOffset));
				}
				catch (final InterruptedException e1)
				{
					throw new BatchUpdateViolationException(terminationMessage, e);
				}
			}
			finally
			{
				if (!session.isCompleted())
				{
					transactionManager.rollback(session);
				}
			}
		}
	}

	private abstract class Functor<T>
	{
		abstract T run();
	}
}
