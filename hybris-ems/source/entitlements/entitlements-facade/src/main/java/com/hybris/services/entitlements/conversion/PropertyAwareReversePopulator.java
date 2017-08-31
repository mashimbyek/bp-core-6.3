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
package com.hybris.services.entitlements.conversion;

import com.hybris.commons.conversion.ConversionException;
import com.hybris.commons.conversion.impl.AbstractPopulator;
import com.hybris.commons.dto.PropertyAwareDto;
import com.hybris.kernel.api.PropertyAware;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

/**
 * Populator for schemaless attributes.
 *
 * Similar to {@link com.hybris.commons.conversion.impl.PropertyAwareReversePopulator}, but skips properties
 * having null values.
 */
public class PropertyAwareReversePopulator extends AbstractPopulator<PropertyAwareDto, PropertyAware>
{
	@Override
	public void populate(final PropertyAwareDto source, final PropertyAware target)
			throws ConversionException, IllegalArgumentException
	{
		final Map<String, Serializable> properties = source.getProperties();
		if (properties != null)
		{
			for (final Map.Entry<String, Serializable> value : properties.entrySet())
			{
				final Serializable val = value.getValue();
				if (val != null)
				{
					if (val instanceof String)
					{
						target.setProperty(value.getKey(), val);
					}
					else
					{
						final Map<Locale, String> locProp = (Map<Locale, String>) val;
						for (final Map.Entry<Locale, String> locValue : locProp.entrySet())
						{
							target.setProperty(value.getKey(), locValue.getValue(), locValue.getKey());
						}
					}
				}
			}
		}
	}
}
