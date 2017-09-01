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

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.notificationservices.enums.NotificationChannel;
import de.hybris.platform.notificationservices.enums.NotificationType;
import de.hybris.platform.notificationservices.mapping.ProcessorMappingRegistry;
import de.hybris.platform.notificationservices.service.NotificationService;


/**
 *
 * default implementation of NotificationService
 *
 */
public class DefaultNotificationService implements NotificationService
{
	private ProcessorMappingRegistry processorRegistry;

	private static final Logger LOG = Logger.getLogger(DefaultNotificationService.class.getName());

	@Override
	public void notifyCustomer(final NotificationType notificationType, final CustomerModel customer,
			final Map<String, ? extends ItemModel> dataMap)
	{
		LOG.info("Send " + notificationType + " notification");

		final Set<NotificationChannel> channelSet = getSupportedChannel(customer);

		notifyCustomerByChannelSet(notificationType, customer, dataMap, channelSet);
	}

	@Override
	public void notifyCustomer(final NotificationType notificationType, final CustomerModel customer,
			final Set<NotificationChannel> channelSet, final Map<String, ? extends ItemModel> dataMap)
	{
		LOG.info("Send " + notificationType + " notification");

		notifyCustomerByChannelSet(notificationType, customer, dataMap, channelSet);
	}

	protected void notifyCustomerByChannelSet(final NotificationType notificationType, final CustomerModel customer,
			final Map<String, ? extends ItemModel> dataMap, final Set<NotificationChannel> channelSet)
	{
		if (channelSet.size() == 0)
		{
			LOG.info("No channel preference is set");
			return;
		}

		channelSet.stream().forEach(ch -> Optional.ofNullable(processorRegistry.getMappings().get(ch))
				.map(x -> x.get(notificationType)).ifPresent(x -> x.process(customer, dataMap)));
	}

	protected Set<NotificationChannel> getSupportedChannel(final CustomerModel customer)
	{
		return processorRegistry.getMappings().keySet().stream().filter(ch -> isSupportedByCustomer(customer, ch))
				.collect(Collectors.toSet());
	}


	protected boolean isSupportedByCustomer(final CustomerModel customer, final NotificationChannel ch)
	{
		final String preference = ch.getCode();

		boolean result = false;

		if (NotificationChannel.EMAIL.getCode().equals(preference))
		{

			result = customer.getEmailPreference().booleanValue();
		}
		else if (NotificationChannel.SMS.getCode().equals(preference))
		{
			result = customer.getSmsPreference().booleanValue();
		}
		return result;
	}

	@Required
	public void setProcessorRegistry(final ProcessorMappingRegistry processorRegistry)
	{
		this.processorRegistry = processorRegistry;
	}
}
