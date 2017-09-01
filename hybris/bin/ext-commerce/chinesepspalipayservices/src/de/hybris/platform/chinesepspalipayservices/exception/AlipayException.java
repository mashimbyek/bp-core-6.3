/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.platform.chinesepspalipayservices.exception;

/**
 * Custom exception class to handle exceptions while executing HTTP request
 */
public class AlipayException extends Exception
{
	private static final long serialVersionUID = 1L;

	public AlipayException()
	{
		super();
	}

	public AlipayException(String s)
	{
		super(s);
	}

	public AlipayException(Throwable throwable)
	{
		super(throwable);
	}

	public AlipayException(String s, Throwable throwable)
	{
		super(s, throwable);
	}

}
