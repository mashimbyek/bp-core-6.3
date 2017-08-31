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

/**
 * This class validates that parameters passed as strings can be converted to particular type.
 */
public abstract class AbstractConversionStrategy<T>
{
	/**
	 * Validate that a string value can be converted to specific type.
	 *
	 * @param field field name for error reference
	 * @param value value to convert
	 * @param errors error messages collector
	 */
	public abstract void validate(final String field, final String value, final ValidationViolations errors);

	/**
	 * Convert given string to T. Do runtime assertion of the string format.
	 * @param value value to convert
	 * @return converted value
	 */
	public abstract T convert(final Object value);

	protected void rejectValue(final String field, final String value, final String className, final ValidationViolations errors)
	{
		errors.add(field, value, "Value must be a " + className);
	}
}
