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
 * General runtime exception.
 */
public class ServiceException extends java.lang.RuntimeException
{
	public ServiceException(final String message)
	{
		super(message);
	}

	/**
	 * Full constructor.
	 *
	 * @param message description of circumstances caused the exception
	 * @param cause original exception
	 */
	public ServiceException(final String message, final Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * Exception-wrapping constructor.
	 *
	 * @param cause original exception
	 */
	public ServiceException(final Throwable cause)
	{
		super(cause);
	}
}
