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

import com.hybris.services.entitlements.condition.ConditionData;
import com.hybris.services.entitlements.condition.GrantValidator;
import com.hybris.services.entitlements.conversion.PropertiesToMapConverter;
import com.hybris.services.entitlements.service.ConditionTypeFactory;

import java.io.Serializable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class extends Spring validator for {@link com.hybris.services.entitlements.condition.CriterionData}.
 */
public class ConditionDataValidator implements Validator
{
	@Autowired
	private ConditionTypeFactory conditionTypeFactory;

	@Override
	public boolean supportsClass(final Class cls)
	{
		return ConditionData.class.isAssignableFrom(cls);
	}

	@Override
	public void validate(final Object object, final ValidationViolations errors)
	{
		if (object != null)
		{
			final ConditionData conditionData = (ConditionData) object;
			validateProperties(conditionData.getProperties(), errors);
			final GrantValidator validator = conditionTypeFactory.getValidator(conditionData.getType());
			if (validator == null)
			{
				errors.add("type", conditionData.getType(), "Unsupported condition type");
			}
			else
			{
				errors.pushNestedPath("properties");
				validator.validateGrantParameters(PropertiesToMapConverter.convert(conditionData.getProperties()), errors);
				errors.popPath();
			}
		}
	}

	private void validateProperties(final Map<String, Serializable> properties, final ValidationViolations errors)
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
	private void validateProperty(final String key, final String value, final ValidationViolations errors)
	{
		if (key == null)
		{
			errors.add(null, value, "Property name must not be null");
		}
	}
}
