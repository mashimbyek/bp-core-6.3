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
package com.hybris.services.entitlements.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Fake model for {@link com.hybris.services.entitlements.condition.CriterionData} DTO.
 * <p>Used to pass criteria from facade to service layer.</p>
 */
public class Criterion
{
	/**
	 * Condition type.
	 */
	private String type;

	/**
	 * Criterion dynamic properties.
	 */
	private final Map<String, String> properties = new HashMap<>();

	public String getType()
	{
		return type;
	}

	public void setType(final String type)
	{
		this.type = type;
	}

	public Map<String, String> getProperties()
	{
		return properties;
	}

	/**
	 * Get property by name.
	 *
	 * @param name property name
	 * @return existing value of null if object has no such property
	 */
	public String getProperty(final String name)
	{
		return properties.get(name);
	}

	/**
	 * Change of create property.
	 *
	 * @param name property name
	 * @param value new value
	 */
	public void setProperty(final String name, final String value)
	{
		properties.put(name, value);
	}
}
