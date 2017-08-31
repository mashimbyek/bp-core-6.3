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
package com.hybris.services.entitlements.condition.string;

import com.hybris.services.entitlements.api.Actions;
import com.hybris.services.entitlements.condition.AbstractConditionStrategy;
import com.hybris.services.entitlements.condition.ActionHandler;
import com.hybris.services.entitlements.condition.ConditionParameter;
import com.hybris.services.entitlements.domain.Condition;
import com.hybris.services.entitlements.domain.Grant;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * Implementation of string condition strategy, which requires a simple "string" parameter for both granting
 * and checking an entitlement.
 */
public class StringConditionStrategy extends AbstractConditionStrategy
{
	/**
	 * Condition value.
	 */
	public static final String GRANT_PARAMETER_STRING = "string";

	/**
	 * Execution value.
	 */
	public static final String EXECUTION_PARAMETER_STRING = "string";

	@Override
	protected List<ConditionParameter> getGrantParameters()
	{
		return Arrays.asList(new ConditionParameter(GRANT_PARAMETER_STRING));
	}

	@Override
	protected List<ConditionParameter> getExecutionParameters()
	{
		return Arrays.asList(new ConditionParameter(EXECUTION_PARAMETER_STRING));
	}

	@Override
	protected void fillActionHandlers(final Map<String, ActionHandler> container)
	{
		container.put(Actions.CHECK, new CheckHandler());
	}

	/**
	 * Check action handler.
	 */
	private static class CheckHandler extends ActionHandler
	{
		@Override
		public boolean execute(final Condition condition, final Map<String, String> executionParameterMap, final Grant grant)
		{
			if (condition == null && executionParameterMap == null)
			{
				return true;
			}
			else if (executionParameterMap == null || condition == null)
			{
				return false;
			}

			final String entitlementString = (String) condition.getProperty(GRANT_PARAMETER_STRING);
			final String queryString = executionParameterMap.get(EXECUTION_PARAMETER_STRING);
			return StringUtils.equals(entitlementString, queryString);
		}
	}
}
