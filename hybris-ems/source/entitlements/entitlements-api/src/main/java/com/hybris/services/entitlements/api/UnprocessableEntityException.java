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
package com.hybris.services.entitlements.api;

/**
 * Exception thrown when operation is not applicable for concrete grant.
 */
public class UnprocessableEntityException extends RuntimeException
{
	/**
	 * Full constructor.
	 *
	 * @param message error description
	 * @param cause parent exception
	 */
	public UnprocessableEntityException(final String message, final Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * Short constructor.
	 *
	 * @param message error description
	 */
	public UnprocessableEntityException(final String message)
	{
		super(message);
	}
}
