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

import com.hybris.commons.dto.PropertyAwareDto;
import com.hybris.services.entitlements.api.GrantData;
import com.hybris.services.entitlements.condition.GrantValidator;
import com.hybris.services.entitlements.service.ConditionTypeFactory;

import java.io.Serializable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;

/**
 * Facade input data validation.
 */
public class GrantPropertyValidator implements Validator
{
	private ConditionTypeFactory conditionTypeFactory;

	void validateProperties(final Map<String, Serializable> properties, final ValidationViolations errors)
	{
		if (properties != null)
		{
			for (Map.Entry<String, Serializable> entry : properties.entrySet())
			{
				final String value = entry.getValue() == null ? null : entry.getValue().toString();
				validateProperty(entry.getKey(), value, errors);
			}
		}
	}

	/**
	 * Validate grant's property.
	 *
	 * @param key property name
	 * @param value value to check
	 * @param errors container that keeps found violations
	 */
	public void validateProperty(final String key, final String value, final ValidationViolations errors)
	{
		if (key == null)
		{
			errors.add(key, value, "Property name must not be null");
		}
		if (value == null)
		{
			errors.add(key, null, "Must not be null. If you intend to remove property, use corresponding DELETE method");
		}
		for (String type : conditionTypeFactory.getKnownTypes())
		{
			final GrantValidator grantValidator = conditionTypeFactory.getValidator(type);
			grantValidator.validateProperty(key, value, errors);
		}
	}

	@Override
	public boolean supportsClass(final Class cls)
	{
		// GrantData.properties is inherited from parent class, so we can not just annotate
		// properties member with Constraint. Instead we need to bind to the whole class.
		return GrantData.class.isAssignableFrom(cls);
	}

	@Override
	public void validate(final Object object, final ValidationViolations errors)
	{
		final PropertyAwareDto grantData = (PropertyAwareDto) object;
		errors.pushNestedPath("properties");
		validateProperties(grantData.getProperties(), errors);
		errors.popPath();
	}

	@Required
	public void setConditionTypeFactory(final ConditionTypeFactory conditionTypeFactory)
	{
		this.conditionTypeFactory = conditionTypeFactory;
	}
}
