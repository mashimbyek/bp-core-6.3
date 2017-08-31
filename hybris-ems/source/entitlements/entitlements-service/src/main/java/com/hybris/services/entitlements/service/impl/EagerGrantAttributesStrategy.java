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
package com.hybris.services.entitlements.service.impl;

import com.hybris.kernel.services.persistence.LazyLoadAttributesStrategy;
import com.hybris.services.entitlements.domain.Grant;

import java.util.Set;

import org.springframework.beans.factory.annotation.Required;

/**
 * Strategy to turn lazy load off.
 */
public class EagerGrantAttributesStrategy implements LazyLoadAttributesStrategy
{

	private LazyLoadAttributesStrategy fallbackStrategy;

	@Override
	public Set<String> getAttributes(final String typeCode)
	{
		if (typeCode.equals(Grant._TYPECODE))
		{
			return null; // null means all attributes
		}
		return fallbackStrategy.getAttributes(typeCode);
	}

	@Required
	public void setFallbackStrategy(final LazyLoadAttributesStrategy fallbackStrategy)
	{
		this.fallbackStrategy = fallbackStrategy;
	}
}
