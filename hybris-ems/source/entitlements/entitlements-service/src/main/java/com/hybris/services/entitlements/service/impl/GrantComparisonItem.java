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

import com.hybris.services.entitlements.condition.ActionHandler;
import com.hybris.services.entitlements.condition.ActionHandlerFactory;
import com.hybris.services.entitlements.domain.Condition;
import com.hybris.services.entitlements.domain.Criterion;
import com.hybris.services.entitlements.domain.Grant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

/**
 * Class to execute an action on condition and relative criterion.
 */
public class GrantComparisonItem
{
	private Grant grant;
	private String conditionType;
	private final List<Condition> conditions = new ArrayList<>();
	private final List<Map<String,String>> criteria = new ArrayList<>();
	private ActionHandlerFactory actionHandlerFactory;

	void setGrant(final Grant grant)
	{
        // Yakovlev M REVIEW: I think, there should be more informative exception
		Assert.isTrue(this.grant == null || this.grant == grant, "Overriding grants is not allowed");
		this.grant = grant;
	}

	public Grant getGrant()
	{
		return grant;
	}

	public String getConditionType()
	{
		return conditionType;
	}

	void setConditionType(final String conditionType)
	{
		this.conditionType = conditionType;
	}

	void setActionHandlerFactory(final ActionHandlerFactory actionHandlerFactory)
	{
		this.actionHandlerFactory = actionHandlerFactory;
	}

	void addCondition(final Condition condition)
	{
		conditions.add(condition);
	}

	void addCriterion(final Criterion criterion)
	{
		criteria.add(criterion.getProperties());
	}

	boolean isApplicable(final String action)
	{
		final ActionHandler handler = actionHandlerFactory.getActionHandler(action);
		if (handler == null)
		{
			return true;
		}
		if (conditions.isEmpty() && criteria.isEmpty())
		{
			return true;
		}
		unifyLists();
		for (final Condition condition : conditions)
		{
			for (final Map<String,String> criterion : criteria)
			{
				if (handler.applicable(condition, criterion))
				{
					return true;
				}
			}
		}
		return false;
	}

	boolean execute(final String action)
	{
		final ActionHandler handler = actionHandlerFactory.getActionHandler(action);
		if (handler == null)
		{
			return true;
		}
		if (conditions.isEmpty() && criteria.isEmpty())
		{
			return true;
		}
		unifyLists();
		for (final Condition condition : conditions)
		{
			for (final Map<String,String> criterion : criteria)
			{
				if (handler.applicable(condition, criterion) &&
					handler.execute(condition, criterion, grant))
				{
					return true;
				}
			}
		}
		return false;
	}

	private void unifyLists()
	{
		if (conditions.isEmpty())
		{
			conditions.add(null);
		}
		if (criteria.isEmpty())
		{
			criteria.add(null);
		}
	}
}
