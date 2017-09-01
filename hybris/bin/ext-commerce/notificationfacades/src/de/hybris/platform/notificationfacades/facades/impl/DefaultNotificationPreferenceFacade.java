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
package de.hybris.platform.notificationfacades.facades.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.notificationfacades.data.NotificationPreferenceData;
import de.hybris.platform.notificationfacades.facades.NotificationPreferenceFacade;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

import org.springframework.beans.factory.annotation.Required;


/**
 * Facade used to get and update notification preference
 */
public class DefaultNotificationPreferenceFacade implements NotificationPreferenceFacade
{
	private ModelService modelService;

	private UserService userService;

	private Converter<CustomerModel, NotificationPreferenceData> notificationPreferenceConverter;

	public ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	public Converter<CustomerModel, NotificationPreferenceData> getNotificationPreferenceConverter()
	{
		return notificationPreferenceConverter;
	}

	@Required
	public void setNotificationPreferenceConverter(
			final Converter<CustomerModel, NotificationPreferenceData> notificationPreferenceConverter)
	{
		this.notificationPreferenceConverter = notificationPreferenceConverter;
	}

	@Override
	public void updateNotificationPreference(final NotificationPreferenceData notificationPreferenceData)
	{
		final CustomerModel customer = (CustomerModel) getUserService().getCurrentUser();
		customer.setEmailPreference(Boolean.valueOf(notificationPreferenceData.isEmailEnabled()));
		customer.setSmsPreference(Boolean.valueOf(notificationPreferenceData.isSmsEnabled()));
		getModelService().save(customer);
	}

	@Override
	public NotificationPreferenceData getNotificationPreference()
	{
		return getNotificationPreferenceConverter().convert((CustomerModel) getUserService().getCurrentUser());
	}
}
