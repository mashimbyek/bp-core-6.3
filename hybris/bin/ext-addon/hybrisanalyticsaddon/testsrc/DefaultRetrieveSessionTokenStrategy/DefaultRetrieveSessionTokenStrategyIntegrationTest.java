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
package DefaultRetrieveSessionTokenStrategy;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.hybrisanalyticsaddon.strategy.impl.DefaultRetrieveSessionTokenStrategy;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;


@IntegrationTest
public class DefaultRetrieveSessionTokenStrategyIntegrationTest extends ServicelayerTest
{

	@Resource
	private DefaultRetrieveSessionTokenStrategy defaultRetrieveSessionTokenStrategy;

	@Resource
	private SessionService sessionService;

	@Test
	public void shouldRetrieveTokenForSession()
	{
		final String token = defaultRetrieveSessionTokenStrategy.getSessionToken();
		Assert.assertNotNull("token was null", token);
		//verify that token stays the same in the current session
		final String sameToken = defaultRetrieveSessionTokenStrategy.getSessionToken();
		Assert.assertEquals(token, sameToken);
	}

	@Test
	public void shouldGenerateNewTokenForNewSession()
	{
		final String token = sessionService.executeInLocalView(new SessionExecutionBody()
		{

			@Override
			public Object execute()
			{
				return defaultRetrieveSessionTokenStrategy.getSessionToken();
			}
		});
		Assert.assertNotNull("token was null", token);

		//verify that token is different for a new session
		final String newToken = sessionService.executeInLocalView(new SessionExecutionBody()
		{

			@Override
			public Object execute()
			{
				return defaultRetrieveSessionTokenStrategy.getSessionToken();
			}
		});
		Assert.assertNotEquals(token, newToken);
	}

}
