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
package de.hybris.platform.notificationfacades.facades;

import de.hybris.platform.notificationfacades.data.NotificationPreferenceData;


/**
 * Facade to get the notification preference and update notification preference
 */
public interface NotificationPreferenceFacade
{
	/**
	 * Update the notification preference to database
	 *
	 * @param notificationPreferenceData
	 *           The new notification prefernece to be updated
	 */
	void updateNotificationPreference(NotificationPreferenceData notificationPreferenceData);

	/**
	 * Get the notification preference
	 *
	 * @return The NotificationPreferenceData contains the enabled channels,and email address and mobile phone number if
	 *         exist
	 */
	NotificationPreferenceData getNotificationPreference();
}
