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

import org.springframework.util.Assert;

/**
 * Boolean type validator.
 *
 * <p>
 * Check that given value is 'true' or 'false'. Case does not matter. No other values accepted.
 * </p>
 */
public class BooleanConversionStrategy extends AbstractConversionStrategy<Boolean>
{
	@Override
	public void validate(final String field, final String value, final ValidationViolations errors)
	{
		if (!Boolean.toString(true).equalsIgnoreCase(value) && !Boolean.toString(false).equalsIgnoreCase(value))
		{
			rejectValue(field, value, "boolean", errors);
		}
	}

	@Override
	public Boolean convert(final Object value)
	{
		if (value == null)
		{
			return false;
		}
		try
		{
			return Boolean.parseBoolean((String) value);
		}
		catch (final ClassCastException e)
		{
			Assert.isTrue(false, value + " is not a string");
		}

		return false;
	}
}
