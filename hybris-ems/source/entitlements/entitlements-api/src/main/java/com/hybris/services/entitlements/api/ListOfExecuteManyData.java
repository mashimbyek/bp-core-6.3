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

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class represents arguments for
 * {@link com.hybris.services.entitlements.api.EntitlementFacade#execute(String, String, java.util.List, Boolean)}
 * method.
 */
@XmlRootElement(name = "listOfExecuteMany")
public class ListOfExecuteManyData
{
	@XmlElementWrapper(name="executeManyDataList")
	@XmlElement(name="executeManyData")
	private List<ExecuteManyData> executeManyDataList;

	/**
	 * the Constructor.
	 */
	public ListOfExecuteManyData()
	{

	}

	/**
	 * the Constructor.
	 *
	 * @param executeManyDataList = the list of executeManyData.
	 */
	public ListOfExecuteManyData(final List<ExecuteManyData> executeManyDataList)
	{
		this.executeManyDataList = executeManyDataList;
	}

	public List<ExecuteManyData> getExecuteManyDataList()
	{
		return executeManyDataList;
	}
}
