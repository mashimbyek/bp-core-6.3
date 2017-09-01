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
package de.hybris.platform.notificationfacades.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.notificationfacades.data.NotificationPreferenceData;
import de.hybris.platform.notificationservices.service.strategies.SmsNotificationChannelStrategy;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.springframework.util.Assert;


/**
 * Populator to populate notification preference data
 */
public class NotificationPreferencePopulator implements Populator<CustomerModel, NotificationPreferenceData>
{
	private SmsNotificationChannelStrategy smsNotificationChannelStrategy;

	public SmsNotificationChannelStrategy getSmsNotificationChannelStrategy()
	{
		return smsNotificationChannelStrategy;
	}

	public void setSmsNotificationChannelStrategy(final SmsNotificationChannelStrategy smsNotificationChannelStrategy)
	{
		this.smsNotificationChannelStrategy = smsNotificationChannelStrategy;
	}



	/**
	 * Populate notification preference data into target.CustomerModel contains UID which is the email address
	 * SmsNotificationChannelStrategy will provide the phone number
	 *
	 * @param source
	 *           The UID in CustomerModel will be used to fill emailAddress in target
	 *
	 * @param target
	 *           The target will contain the populated notification preference data about whether email and sms are
	 *           enabled and what their detail informations are
	 */
	@Override
	public void populate(final CustomerModel source, final NotificationPreferenceData target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		final Boolean emailPreference = source.getEmailPreference();
		target.setEmailEnabled(emailPreference == null ? false : emailPreference.booleanValue());

		final Boolean smsPreference = source.getSmsPreference();
		target.setSmsEnabled(smsPreference == null ? false : smsPreference.booleanValue());

		target.setEmailAddress(source.getUid());
		target.setMobileNumber(getSmsNotificationChannelStrategy().getMobilePhoneNumber(source));
	}
}
