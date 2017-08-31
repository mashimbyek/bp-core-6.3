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

import java.util.Locale;
import java.util.Map;

/**
 * Populator for schemaless attributes.
 *
 * Similar to {@link com.hybris.commons.conversion.impl.PropertyAwarePopulator}, but skips properties having null values.
 */
public class PropertyAwarePopulator extends AbstractPopulator<PropertyAware, PropertyAwareDto>
{
	@Override
	public void populate(final PropertyAware source, final PropertyAwareDto target) throws ConversionException,
			IllegalArgumentException
	{
		for (final Map.Entry<String, Object> entry : source.getAllProperties().entrySet())
		{
			final Object value = entry.getValue();
			if (value instanceof Map<?, ?>)
			{
				final Map<Locale, Object> locProp = (Map<Locale, Object>) value;
				for (final Map.Entry<Locale, Object> locValue : locProp.entrySet())
				{
					if (locValue.getValue() != null)
					{
						target.setProperty(entry.getKey(), locValue.getValue().toString(), locValue.getKey());
					}
				}
			}
			else if (value != null)
			{
				target.setProperty(entry.getKey(), value.toString());
			}
		}
	}
}
