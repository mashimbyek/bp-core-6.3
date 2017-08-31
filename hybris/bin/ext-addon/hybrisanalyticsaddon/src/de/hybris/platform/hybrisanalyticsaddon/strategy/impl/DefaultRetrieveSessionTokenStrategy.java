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
package de.hybris.platform.hybrisanalyticsaddon.strategy.impl;

import de.hybris.platform.hybrisanalyticsaddon.strategy.RetrieveSessionTokenStrategy;
import de.hybris.platform.servicelayer.session.SessionService;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Required;


public class DefaultRetrieveSessionTokenStrategy implements RetrieveSessionTokenStrategy
{
	public static final String SESSION_TOKEN_KEY = "SESSION_TOKEN_KEY";
	private SessionService sessionService;

	@Override
	public synchronized String getSessionToken()
	{
		String token = getSessionService().getAttribute(SESSION_TOKEN_KEY);
		if (token == null)
		{
			token = UUID.randomUUID().toString();
			getSessionService().setAttribute(SESSION_TOKEN_KEY, token);
		}
		return token;
	}

	protected SessionService getSessionService()
	{
		return sessionService;
	}

	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

}
