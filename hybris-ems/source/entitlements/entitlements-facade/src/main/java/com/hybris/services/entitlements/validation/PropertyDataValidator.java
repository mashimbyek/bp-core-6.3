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

import com.hybris.services.entitlements.api.PropertyData;

import org.springframework.util.Assert;
import org.springframework.beans.factory.annotation.Required;

/**
 * PropertyData validator.
 */
public class PropertyDataValidator implements Validator
{
	private AbstractConversionStrategy integerConversionStrategy;
	private static final String VALUE_MEMBER = "value";

	@Override
	public boolean supportsClass(final Class cls)
	{
		return PropertyData.class.isAssignableFrom(cls);
	}

	@Override
	public void validate(final Object object, final ValidationViolations errors)
	{
		Assert.notNull(object);
		try
		{
			final PropertyData propertyData = (PropertyData) object;

			if (propertyData.isRelative())
			{
				integerConversionStrategy.validate(VALUE_MEMBER, propertyData.getValue(), errors);
			}
		}
		catch (final ClassCastException e)
		{
            Assert.isTrue(false, "Value is not a PropertyData");
		}
	}

	@Required
	public void setConversionStrategy(final AbstractConversionStrategy<Integer> conversionStrategy)
	{
		this.integerConversionStrategy = conversionStrategy;
	}



}
