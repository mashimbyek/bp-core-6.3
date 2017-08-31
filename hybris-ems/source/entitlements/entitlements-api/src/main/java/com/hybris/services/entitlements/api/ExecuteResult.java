/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 *
 */
package com.hybris.services.entitlements.api;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * class represent result of execute method.
 */
@XmlRootElement//(name="GetGrantResult")
public class ExecuteResult
{
	private boolean result;
	private Collection<GrantData> grantDataList;

	/**
	 * the Constructor.
	 */
	public ExecuteResult()
	{

	}

	/**
	 * the Constructor.
	 *
	 * @param result the result.
	 */
	public ExecuteResult(final boolean result)
	{
		this.result = result;
	}

	public boolean isResult()
	{
		return result;
	}

	public void setResult(final boolean result)
	{
		this.result = result;
	}

	public Collection<GrantData> getGrantDataList()
	{
		return grantDataList;
	}

	public void setGrantDataList(final Collection<GrantData> grantDataList)
	{
		this.grantDataList = grantDataList;
	}
}
