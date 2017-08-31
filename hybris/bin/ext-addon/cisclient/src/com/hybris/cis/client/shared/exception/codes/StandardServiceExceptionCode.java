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
 * Provides for mapping exceptions to specific codes (so that the client can handle it accordingly).
 * 
 */
public interface StandardServiceExceptionCode
{
	int getCode();

	String getMessage();
}
