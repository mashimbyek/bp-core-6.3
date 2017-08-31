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
package com.hybris.services.entitlements.condition.path;

import com.hybris.services.entitlements.api.Actions;
import com.hybris.services.entitlements.condition.AbstractConditionStrategy;
import com.hybris.services.entitlements.condition.ActionHandler;
import com.hybris.services.entitlements.condition.ConditionParameter;
import com.hybris.services.entitlements.domain.Condition;
import com.hybris.services.entitlements.domain.Grant;
import com.hybris.services.entitlements.validation.AbstractConversionStrategy;
import com.hybris.services.entitlements.validation.ValidationViolations;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;


/**
 * Hierarchical condition checking strategy.
 */
public class PathConditionStrategy extends AbstractConditionStrategy
{
	/**
	 * Root path.
	 */
	public static final String GRANT_PARAMETER_PATH = "path";

	/**
	 * Ignore case. Optional. Default value is false.
	 */
	public static final String GRANT_PARAMETER_CASE_INSENSITIVE = "nocase";

	/**
	 * Path item separator. Can be any string. Optional. Default value is DEFAULT_SEPARATOR.
	 */
	public static final String GRANT_PARAMETER_SEPARATOR = "separator";

	/**
	 * Path to compare.
	 */
	public static final String EXECUTION_PARAMETER_FILE = "file";

	/**
	 * Default path item separator.
	 */
	public static final String DEFAULT_SEPARATOR = "/";

	private AbstractConversionStrategy<Boolean> booleanConverter;

	@Required
	public void setBooleanConverter(final AbstractConversionStrategy<Boolean> strategy)
	{
		booleanConverter = strategy;
	}

	private boolean startsWith(final String value, final String start, final boolean ignoreCase)
	{
		return (ignoreCase && StringUtils.startsWithIgnoreCase(value, start)) ||
				(!ignoreCase && value.startsWith(start));
	}

	private String getSeparator(final Condition condition)
	{
		String separator = (String) condition.getProperty(GRANT_PARAMETER_SEPARATOR);
		if (separator == null)
		{
			separator = DEFAULT_SEPARATOR;
		}
		return separator;
	}

	@Override
	public void validateGrantParameters(final Map<String, String> parameterMap, final ValidationViolations errors)
	{
		super.validateGrantParameters(parameterMap, errors);
		final String directory = parameterMap.get(GRANT_PARAMETER_PATH);
		final String noCase = parameterMap.get(GRANT_PARAMETER_CASE_INSENSITIVE);
		String separator = parameterMap.get(GRANT_PARAMETER_SEPARATOR);
		if (separator == null)
		{
			separator = PathConditionStrategy.DEFAULT_SEPARATOR;
		}
		if (directory != null && endsWith(directory, separator, Boolean.parseBoolean(noCase)))
		{
			errors.add(GRANT_PARAMETER_PATH, directory, "Must not end with separator");
		}
	}

	@Override
	protected List<ConditionParameter> getGrantParameters()
	{
		return Arrays.asList(new ConditionParameter(GRANT_PARAMETER_PATH),
				new ConditionParameter(GRANT_PARAMETER_CASE_INSENSITIVE, false, booleanConverter),
				new ConditionParameter(GRANT_PARAMETER_SEPARATOR, false, null));
	}

	@Override
	protected List<ConditionParameter> getExecutionParameters()
	{
		return Arrays.asList(new ConditionParameter(EXECUTION_PARAMETER_FILE));
	}

	@Override
	protected void fillActionHandlers(final Map<String, ActionHandler> container)
	{
		container.put(Actions.CHECK, new CheckAction());
	}

	private boolean endsWith(final String text, final String sample, final boolean ignoreCase)
	{
		return (ignoreCase && StringUtils.endsWithIgnoreCase(text, sample)) ||
				(!ignoreCase && text.endsWith(sample));
	}

	/**
	 * Check action handler.
	 */
	private class CheckAction extends ActionHandler
	{
		@Override
		public boolean execute(final Condition condition, final Map<String, String> executionParameterMap, final Grant grant)
		{
			final String file = executionParameterMap.get(EXECUTION_PARAMETER_FILE);
			final Object directoryObject = condition.getProperty(GRANT_PARAMETER_PATH);
			Assert.notNull(file, "File name was not found");
			Assert.notNull(directoryObject, "Directory name was not found");
			Assert.isTrue(directoryObject instanceof String, "Directory should be a string");
			final String directory = (String) directoryObject;
			final boolean ignoreCase = booleanConverter.convert(condition.getProperty(GRANT_PARAMETER_CASE_INSENSITIVE));

			if (!startsWith(file, directory, ignoreCase))
			{
				// file does not start with directory
				return false;
			}
			if (file.length() == directory.length())
			{
				// file is the same string as directory
				return true;
			}
			final String separator = getSeparator(condition);
			if (separator.isEmpty())
			{
				// Empty separator means we do not separate path items at all,
				// so the logic degrades to the simple starts-with check
				return true;
			}
			final String remainder = file.substring(directory.length());

			return startsWith(remainder, separator, ignoreCase);
		}
	}
}
