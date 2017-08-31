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
package com.hybris.services.entitlements.api.exceptions;

/**
 * Validation exception.
 */
public class ValidationException extends java.lang.RuntimeException
{
	public ValidationException(final String message)
	{
		super(message);
	}

	/**
	 * Full constructor.
	 *
	 * @param message description of circumstances caused the exception
	 * @param cause original exception
	 */
	public ValidationException(final String message, final Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * Exception-wrapping constructor.
	 *
	 * @param e original exception
	 */
	public ValidationException(final Throwable e)
	{
		super(e);
	}
}
