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
package com.hybris.services.entitlements.condition;

import com.hybris.services.entitlements.domain.Condition;
import com.hybris.services.entitlements.domain.Grant;

import java.util.Map;

/**
 * Abstract action handler.
 */
public abstract class ActionHandler
{
	/**
	 * Returns true if
	 * {@link #execute(com.hybris.services.entitlements.domain.Condition,
	 * java.util.Map, com.hybris.services.entitlements.domain.Grant)}
	 * can accept given parameters.
	 *
	 * <p>
	 *     Put here null checks to point which parameters are mandatory.
	 * 	   The case when both arguments is null is handled upper, so you can safely skip this check.
	 * </p>
	 * <p>@see com.hybris.services.entitlements.condition.metered.MeteredConditionStrategy.CheckHandler</p>
	 *
	 * @param condition grant condition
	 * @param executionParameterMap check criterion data
	 * @return true if the arguments can be passed further to {@link #execute}
	 */
	public boolean applicable(final Condition condition, final Map<String, String> executionParameterMap)
	{
		return condition != null && executionParameterMap != null;
	}

	/**
	 * Apply action to condition/grant.
	 *
	 * @param condition grant condition
	 * @param executionParameterMap check criterion data
	 * @param grant parent grant
	 * @return true if condition fits given execution parameters
	 */
	public abstract boolean execute(Condition condition, Map<String, String> executionParameterMap, Grant grant);
}
