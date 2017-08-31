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
package com.hybris.services.entitlements.sorting;

import com.hybris.services.entitlements.validation.ValidationViolations;
import com.hybris.services.entitlements.validation.Validator;

import org.springframework.beans.factory.annotation.Required;

/**
 * Facade input data validation.
 */
public class ComparatorValidator implements Validator
{
	private GrantComparatorFactory grantComparatorFactory;

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
		final String comparatorType = (String) object;
		if (comparatorType != null && !grantComparatorFactory.getKnownTypes().contains(comparatorType))
		{
			errors.add("comparatorType", comparatorType, "Can't find compator");
		}
	}

	@Required
	public void setGrantComparatorFactory(final GrantComparatorFactory grantComparatorFactory)
	{
		this.grantComparatorFactory = grantComparatorFactory;
	}
}
