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
package com.hybris.services.entitlements.condition.timeframe;

import com.hybris.services.entitlements.api.Actions;
import com.hybris.services.entitlements.condition.AbstractConditionStrategy;
import com.hybris.services.entitlements.condition.ActionHandler;
import com.hybris.services.entitlements.condition.ActionHandlerFactory;
import com.hybris.services.entitlements.condition.ConditionParameter;
import com.hybris.services.entitlements.domain.Condition;
import com.hybris.services.entitlements.domain.Grant;
import com.hybris.services.entitlements.validation.AbstractConversionStrategy;
import com.hybris.services.entitlements.validation.ValidationViolations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

/**
 * Implementation of time frame condition Checker.
 */
public class TimeframeConditionStrategy extends AbstractConditionStrategy implements ActionHandlerFactory
{
	/**
	 * Start time bound.
	 */
	public static final String GRANT_PARAMETER_START = "startTime";

	/**
	 * End time bound.
	 */
	public static final String GRANT_PARAMETER_END = "endTime";

	/**
	 * Time and date point.
	 */
	public static final String EXECUTION_PARAMETER_TIME = "time";

	private AbstractConversionStrategy<Date> dateTimeConverter;

	@Override
	protected List<ConditionParameter> getGrantParameters()
	{
		return Arrays.asList(
				new ConditionParameter(GRANT_PARAMETER_START, true, dateTimeConverter),
				new ConditionParameter(GRANT_PARAMETER_END, false, dateTimeConverter));
	}

	@Override
	protected List<ConditionParameter> getExecutionParameters()
	{
		return Arrays.asList(new ConditionParameter(EXECUTION_PARAMETER_TIME, true, dateTimeConverter));
	}

	@Override
	protected void fillActionHandlers(final Map<String, ActionHandler> container)
	{
		container.put(Actions.CHECK, new CheckHandler());
	}

	@Override
	public void validateGrantParameters(final Map<String, String> parameterMap, final ValidationViolations errors)
	{
		super.validateGrantParameters(parameterMap, errors);
		final Date begin = dateTimeConverter.convert(parameterMap.get(GRANT_PARAMETER_START));
		final Date end = dateTimeConverter.convert(parameterMap.get(GRANT_PARAMETER_END));
		if (begin != null && end != null && begin.getTime() > end.getTime())
		{
			errors.add("Start point is later than end point");
		}
	}

	@Required
	public void setDateTimeConverter(final AbstractConversionStrategy<Date> strategy)
	{
		this.dateTimeConverter = strategy;
	}

	private class CheckHandler extends ActionHandler
	{
		@Override
		public boolean applicable(final Condition condition, final Map<String, String> executionParameterMap)
		{
			return true;
		}

		@Override
		public boolean execute(final Condition condition, final Map<String, String> executionParameterMap, final Grant grant)
		{
			if (condition == null)
			{
				return true;
			}

			final Date startTime = dateTimeConverter.convert(condition.getProperty(GRANT_PARAMETER_START));
			final Date endTime = dateTimeConverter.convert(condition.getProperty(GRANT_PARAMETER_END));
			final Date time = (executionParameterMap == null)
					? new Date()
					: dateTimeConverter.convert(executionParameterMap.get(EXECUTION_PARAMETER_TIME));
			Assert.notNull(startTime, "Error converting startTime");
			Assert.notNull(time, "Error converting current time");
			return isOrderedInterval(startTime, time) && isOrderedInterval(time, endTime);
		}
	}

		private static boolean isOrderedInterval(final Date beginning, final Date finishing)
		{
			return finishing == null || !finishing.before(beginning);
		}
}
