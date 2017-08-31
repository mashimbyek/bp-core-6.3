/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.hybris.cis.client.shared.exception.codes;

/**
 * A {@link ServiceExceptionDetail} with a status code of {@link StandardServiceExceptionCodes#UNKNOWN}.
 * 
 */
public class UnknownServiceExceptionDetail extends ServiceExceptionDetail
{
	/**
	 * An Unknown service exception detail with a given message.
	 * 
	 * @param message an exception message
	 */
	public UnknownServiceExceptionDetail(final String message)
	{
		super(StandardServiceExceptionCodes.UNKNOWN, message);
	}
}
