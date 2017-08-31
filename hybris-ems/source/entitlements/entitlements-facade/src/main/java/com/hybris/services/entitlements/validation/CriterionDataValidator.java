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

import com.hybris.services.entitlements.condition.CriterionData;
import com.hybris.services.entitlements.condition.GrantValidator;
import com.hybris.services.entitlements.conversion.PropertiesToMapConverter;
import com.hybris.services.entitlements.service.ConditionTypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * Validates single instance of {@link CriterionData}.
 */
public class CriterionDataValidator implements Validator
{
	@Autowired
	private ConditionTypeFactory conditionTypeFactory;

	@Override
	public boolean supportsClass(final Class cls)
	{
		Assert.notNull(cls);
		return CriterionData.class.isAssignableFrom(cls);
	}

	@Override
	public void validate(final Object object, final ValidationViolations errors)
	{
		Assert.notNull(object);

		try
		{
			final CriterionData data = (CriterionData) object;
			final GrantValidator validator = conditionTypeFactory.getValidator(data.getType());
			if (validator == null)
			{
				if (data.getType() != null)
				{
					errors.add("type", data.getType(), "Unsupported condition type");
				}
			}
			else
			{
				validator.validateExecutionParameters(PropertiesToMapConverter.convert(data.getProperties()), errors);
			}
		}
		catch (final ClassCastException e)
		{
			Assert.isTrue(false, "Value is not a CriterionData");
		}
	}
}
