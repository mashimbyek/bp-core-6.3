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
package de.hybris.platform.notificationservices.service.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.notificationservices.enums.NotificationChannel;
import de.hybris.platform.notificationservices.enums.NotificationType;
import de.hybris.platform.notificationservices.mapping.ProcessorMappingRegistry;
import de.hybris.platform.notificationservices.processor.Processor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


@UnitTest
public class DefaultNotificationServiceUnitTest
{
	private ProcessorMappingRegistry processorMappingRegistry = new ProcessorMappingRegistry();

	private DefaultNotificationService notificationService;
	private Map<String, ? extends ItemModel> dataMap;
	private CustomerModel customer;
	@Mock
	private Processor emailProcessor;
	@Mock
	private Processor smsProcessor;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		notificationService = new DefaultNotificationService();
		notificationService.setProcessorRegistry(processorMappingRegistry);
		processorMappingRegistry.addMapping(NotificationChannel.EMAIL, NotificationType.NOTIFICATION, emailProcessor);
		processorMappingRegistry.addMapping(NotificationChannel.SMS, NotificationType.NOTIFICATION, smsProcessor);

		dataMap = new HashMap<>();

		customer = new CustomerModel();
		customer.setEmailPreference(Boolean.TRUE);
		customer.setSmsPreference(Boolean.TRUE);
	}

	@Test
	public void testNotifyCustomerByNotAssignedChannelSet()
	{
		notificationService.notifyCustomer(NotificationType.NOTIFICATION, customer, dataMap);

		verify(emailProcessor, times(1)).process(customer, dataMap);
		verify(smsProcessor, times(1)).process(customer, dataMap);

	}

	@Test
	public void testNotifyCustomerByAssignedChannelSet()
	{
		final Set<NotificationChannel> channelSet = new HashSet<NotificationChannel>();
		channelSet.add(NotificationChannel.EMAIL);
		notificationService.notifyCustomer(NotificationType.NOTIFICATION, customer, channelSet, dataMap);
		verify(emailProcessor, times(1)).process(customer, dataMap);
	}

}
