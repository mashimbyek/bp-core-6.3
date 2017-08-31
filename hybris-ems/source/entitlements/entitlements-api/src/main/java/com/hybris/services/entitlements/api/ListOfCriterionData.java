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

import com.hybris.services.entitlements.condition.CriterionData;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class represents arguments for
 * {@link com.hybris.services.entitlements.api.EntitlementFacade#execute(String, String, String, java.util.List, boolean)}
 * method.
 */
@XmlRootElement(name = "listOfCriterion")
public class ListOfCriterionData
{
	@XmlElement(name="criterions")
	private List<CriterionData> criterionDataList;

	/**
	 * the Constructor.
	 */
	public ListOfCriterionData()
	{

	}

	/**
	 * the Constructor.
	 *
	 * @param criterionDataList = the list of CriterionData.
	 */
	public ListOfCriterionData(final List<CriterionData> criterionDataList)
	{
		this.criterionDataList = criterionDataList;
	}

	public List<CriterionData> getCriterionDataList()
	{
		return criterionDataList;
	}
}
