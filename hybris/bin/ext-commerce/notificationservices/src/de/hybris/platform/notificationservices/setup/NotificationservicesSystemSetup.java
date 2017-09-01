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
package de.hybris.platform.notificationservices.setup;

import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.notificationservices.constants.NotificationservicesConstants;
import de.hybris.platform.notificationservices.dao.NotifyCustomerDao;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;


@SystemSetup(extension = NotificationservicesConstants.EXTENSIONNAME)
public class NotificationservicesSystemSetup
{

	private NotifyCustomerDao notifyCustomerDao;
	private ModelService modelService;

	@SystemSetup(process = SystemSetup.Process.ALL, type = SystemSetup.Type.ESSENTIAL)
	public void updateOldCustomers()
	{
		final List<CustomerModel> oldCustomers = notifyCustomerDao.findOldCustomer();

		if (oldCustomers.isEmpty())
		{
			return;
		}

		oldCustomers.stream().forEach(customer -> {
			customer.setEmailPreference(Boolean.TRUE);
			customer.setSmsPreference(Boolean.FALSE);
		});

		modelService.saveAll(oldCustomers);

	}


	public NotifyCustomerDao getNotifyCustomerDao()
	{
		return notifyCustomerDao;
	}

	@Required
	public void setNotifyCustomerDao(final NotifyCustomerDao notifyCustomerDao)
	{
		this.notifyCustomerDao = notifyCustomerDao;
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}


}
