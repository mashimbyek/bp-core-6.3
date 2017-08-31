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


import com.hybris.services.entitlements.api.Actions;
import com.hybris.services.entitlements.validation.AbstractConversionStrategy;
import com.hybris.services.entitlements.validation.ValidationViolations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;


/**
 * Base class for condition handling.
 */
public abstract class AbstractConditionStrategy implements GrantValidator, ActionHandlerFactory
{
	/**
	 * Initialization action. Called after condition is set to grant.
	 *
	 * In the handler strategy can update host grant according to condition status.
	 */
	public static final String INTERNAL_ACTION_INIT = "#init#";

	private static final Pattern CLASSNAME_SUFFIX = Pattern.compile("Strategy|ConditionStrategy");
	private Map<String, ActionHandler> handlers;

	@Override
	public String getConditionType()
	{
		final String simpleName = getClass().getSimpleName();
		String str = simpleName;
		str = CLASSNAME_SUFFIX.matcher(str).replaceAll("").toLowerCase();
		Assert.isTrue(str.length() < simpleName.length(), "Wrong class name. Class must be named <condition type>ConditionStrategy");
		return str;
	}

	/**
	 * List of parameters used for condition creation.
	 *
	 * @return list of possible parameters
	 */
	protected abstract List<ConditionParameter> getGrantParameters();

	/**
	 * List of parameters used for condition execution.
	 *
	 * @return list of possible parameters
	 */
	protected abstract List<ConditionParameter> getExecutionParameters();

	/**
	 * Add handlers of {@link com.hybris.services.entitlements.api.Actions} that are supported by
	 * your condition.
	 *
	 * @param container map to add the handlers to
	 */
	protected abstract void fillActionHandlers(final Map<String, ActionHandler> container);

	@Override
	public void validateGrantParameters(final Map<String, String> parameterMap, final ValidationViolations errors)
	{
		validateParameters(parameterMap, getGrantParameters(), errors);
	}

	@Override
	public void validateExecutionParameters(final Map<String, String> parameterMap, final ValidationViolations errors)
	{
		validateParameters(parameterMap, getExecutionParameters(), errors);
	}

	@Override
	public ActionHandler getActionHandler(final String action)
	{
		return getHandlers().get(StringUtils.isEmpty(action) ? Actions.CHECK : action);
	}

	protected void validateParameters(final Map<String, String> parameterMap,
			final List<ConditionParameter> template, final ValidationViolations errors)
	{
		for (final ConditionParameter parameter : template)
		{
			if (parameterMap.containsKey(parameter.getName()))
			{
				final AbstractConversionStrategy validator = parameter.getValidator();
				if (validator != null)
				{
					validator.validate(parameter.getName(), parameterMap.get(parameter.getName()), errors);
				}
			}
			else if (parameter.isRequired())
			{
				errors.add(parameter.getName(), null, "Required field was not found");
			}
		}
	}

	@Override
	public void validateProperty(final String key, final String value, final ValidationViolations errors)
	{
	}

	/**
	 * List of supported actions with corresponding handlers.
	 *
	 * @return map action name to handler
	 */
	protected Map<String, ActionHandler> getHandlers()
	{
		if (handlers == null)
		{
			handlers = new HashMap<>();
			fillActionHandlers(handlers);
			Assert.notEmpty(handlers, "Condition must support at least one action");
		}
		return handlers;
	}
}
