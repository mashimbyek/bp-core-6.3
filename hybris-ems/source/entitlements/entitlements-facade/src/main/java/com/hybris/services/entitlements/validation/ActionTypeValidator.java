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

import com.hybris.services.entitlements.api.Actions;

/**
 * Validates execution actions.
 */
public class ActionTypeValidator implements Validator
{
	@Override
	public boolean supportsClass(final Class cls)
	{
		// This way we exclude the validator from default list,
		// otherwise it will validate every string.
		return false;
	}

	@Override
	public void validate(final Object object, final ValidationViolations errors)
	{
		if (object != null)
		{
			if (!Actions.CHECK.equals(object.toString()) && !Actions.USE.equals(object.toString()))
			{
				errors.add("", object, "Unknown action");
			}
		}
	}
}
