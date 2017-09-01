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
package de.hybris.platform.stocknotificationservices.cronjob;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.customerinterestsservices.model.ProductInterestModel;
import de.hybris.platform.notificationservices.enums.NotificationChannel;
import de.hybris.platform.notificationservices.enums.NotificationType;
import de.hybris.platform.notificationservices.service.NotificationService;
import de.hybris.platform.stocknotificationservices.constants.StocknotificationservicesConstants;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * A task to send notification to customers
 */
public class StockNotificationTask implements Runnable
{
	private final NotificationService notificationService;
	private final Map<String, ItemModel> data;
	private final ProductInterestModel productInterest;

	public StockNotificationTask(final NotificationService notificationService, final Map<String, ItemModel> data)
	{
		this.notificationService = notificationService;
		this.data = data;
		this.productInterest = (ProductInterestModel) data.get(StocknotificationservicesConstants.PRODUCT_INTEREST);
	}

	@Override
	public void run()
	{
		notificationService.notifyCustomer(NotificationType.BACK_IN_STOCK, productInterest.getCustomer(), channels(), data);
	}

	protected Set<NotificationChannel> channels()
	{
		final Set<NotificationChannel> channels = new HashSet<>();

		if (productInterest.getEmailEnabled().booleanValue())
		{
			channels.add(NotificationChannel.EMAIL);
		}

		if (productInterest.getSmsEnabled().booleanValue())
		{
			channels.add(NotificationChannel.SMS);
		}
		return channels;
	}
}
