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
package com.hybris.services.entitlements.validation;

import com.hybris.services.entitlements.condition.timeframe.TimeframeConditionStrategy;
import com.hybris.services.entitlements.conversion.DateTimeConverter;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Required;

/**
 * Date time validator.
 */
public class DateTimeConversionStrategy extends AbstractConversionStrategy<Date> implements Validator
{
	private DateTimeConverter converter;

	@Override
	public void validate(final String field, final String value, final ValidationViolations errors)
	{
		try
		{
			converter.convertStringToDate(value);
		}
		catch (final ParseException | IllegalArgumentException e)
		{
			if (TimeframeConditionStrategy.GRANT_PARAMETER_END.equals(field) || "".equals(value))
			{
				return;
			}
			errors.add(field, value, "Expected ISO 8601 date and time format");
		}
	}

	@Override
	public Date convert(final Object value)
	{
		if (value == null)
		{
			return null;
		}
		try
		{
			return converter.convertStringToDate((String) value);
		}
		catch (final ParseException | IllegalArgumentException e)
		{
			return null;
		}
	}

	@Required
	public void setConverter(final DateTimeConverter converter)
	{
		this.converter = converter;
	}

	@Override
	public boolean supportsClass(final Class cls)
	{
		return String.class.isAssignableFrom(cls);
	}

	@Override
	public void validate(final Object object, final ValidationViolations errors)
	{
		if (object != null)  // null is an allowed value
		{
			try
			{
				converter.convertStringToDate((String) object);
			}
			catch (final ParseException | IllegalArgumentException e)
			{
				errors.add("Expected ISO 8601 date and time format");
			}
		}
	}
}
