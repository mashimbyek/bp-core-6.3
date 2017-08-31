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
package de.hybris.platform.ruleenginebackoffice.listeners;

import static org.assertj.core.api.Assertions.assertThat;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.ruleengine.event.RuleEngineModuleSwapCompletedEvent;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;


@IntegrationTest
public class ModuleSwapCompletedListenerIT extends ServicelayerTest
{

	@Resource
	private EventService eventService;

	private EventResultAccumulator resultAccumulator;

	@Before
	public void setUp()
	{
		resultAccumulator = new EventResultAccumulator();
		eventService.registerEventListener(new AbstractEventListener<RuleEngineModuleSwapCompletedEvent>()
		{
			@Override protected void onEvent(final RuleEngineModuleSwapCompletedEvent event)
			{
				resultAccumulator.setEventString(event.toString());
			}

		});
	}

	@Test
	public void testSendReceive() throws Exception
	{
		final RuleEngineModuleSwapCompletedEvent sourceEvent = RuleEngineModuleSwapCompletedEvent
				.ofSuccess("TEST_MODULE", "0", "1");
		eventService.publishEvent(sourceEvent);
		Thread.sleep(1000);
		assertThat(resultAccumulator.getEventString()).isNotNull().isEqualTo(sourceEvent.toString());
	}

	private static final class EventResultAccumulator
	{
		private String eventString;

		public String getEventString()
		{
			return eventString;
		}

		public void setEventString(final String eventString)
		{
			this.eventString = eventString;
		}
	}



}
