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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * The ConditionDataListValidator.
 */
public class ConditionDataListValidator implements Validator
{
	@Override
	/**
	 * This method returns false to prevent binding of this class to anything.
	 * Validation can be invoked explicitly only via {@link com.hybris.services.entitlements.api.Constraint}
	 */
	public boolean supportsClass(final Class cls)
	{
		return false;
	}

	@Override
	public void validate(final Object object, final ValidationViolations errors)
	{
		if (object == null)
		{
			//errors.add("May not be null");  NULL conditions is absolutely OK.
			return;
		}
		final Collection conditions = (Collection) object;
		final Set<String> foundTypes = new HashSet<>();
		Integer index = 0;
		for (final Object conditionCandidate : conditions)
		{
			final String field = String.format("conditions[%d]", index);
			index = index + 1;
			if (conditionCandidate == null)
			{
				errors.add(field, null, "May not be null");
			}
			else
			{
				try
				{
					final ConditionData condition = (ConditionData) conditionCandidate;
					if (foundTypes.contains(condition.getType()))
					{
						errors.add(field +  ".type", condition.getType(), "Duplicate condition type");
					}
					else
					{
						foundTypes.add(condition.getType());
					}
				}
				catch (final ClassCastException e)
				{
					errors.add(field + ".class", conditionCandidate.getClass().getName(), "Can not be cast to ConditionData");
				}
			}
		}
	}
}
