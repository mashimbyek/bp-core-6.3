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
package de.hybris.platform.chineseprofileservices.sms.impl;

import de.hybris.platform.chineseprofileservices.sms.ChineseSmsService;

import org.apache.log4j.Logger;


/**
 * Default implementation.
 */
public class DefaultChineseSmsService implements ChineseSmsService
{

	private static final Logger logger = Logger.getLogger(DefaultChineseSmsService.class);

	/**
	 * Here is an empty implementation.
	 */
	@Override
	public void sendMsg(final String mobileNumber, final String msg)
	{
		if (logger.isInfoEnabled())
		{
			logger.info("Mobile Number : " + mobileNumber);
			logger.info("Message : " + msg);
			logger.info("Message send success!");
		}
	}

}
