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
package de.hybris.platform.chinaaccelerator.services.customer.impl;

import de.hybris.platform.chinaaccelerator.services.customer.CustomerService;
import de.hybris.platform.chinaaccelerator.services.customer.daos.CustomerDao;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;


/**
 * DefaultCustomerDao
 */
public class DefaultCustomerService implements CustomerService
{
	private CustomerDao customerDao;

	@Override
	public List<CustomerModel> getCustomerForMobileNumber(final String mobileNumber)
	{

		return getCustomerDao().findCustomerByMobileNumber(mobileNumber);
	}

	/**
	 * @return the customerDao
	 */
	public CustomerDao getCustomerDao()
	{
		return customerDao;
	}

	/**
	 * @param customerDao
	 *           the customerDao to set
	 */
	public void setCustomerDao(final CustomerDao customerDao)
	{
		this.customerDao = customerDao;
	}


}
