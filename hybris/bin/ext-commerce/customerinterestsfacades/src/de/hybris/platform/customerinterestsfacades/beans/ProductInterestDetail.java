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
package de.hybris.platform.customerinterestsfacades.beans;

import java.util.Date;


public class ProductInterestDetail
{
	private String interestType;
	private Date dateAdded;
	private boolean enabled = false;


	public Date getDateAdded()
	{
		return dateAdded;
	}

	public void setDateAdded(final Date dateAdded)
	{
		this.dateAdded = dateAdded;
	}

	public Boolean getEnabled()
	{
		return enabled;
	}

	public void setEnabled(final Boolean enabled)
	{
		this.enabled = enabled;
	}

	public String getInterestType()
	{
		return interestType;
	}

	public void setInterestType(final String interestType)
	{
		this.interestType = interestType;
	}

}
