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

/**
 * Single validation error message.
 */
public class Violation
{
	private final String field;
	private final Object value;
	private final String message;

	Violation(final String field, final Object value, final String message)
	{
		this.field = field;
		this.value = value;
		this.message = message;
	}

	public String getField()
	{
		return field;
	}

	public Object getValue()
	{
		return value;
	}

	public String getMessage()
	{
		return message;
	}
}
