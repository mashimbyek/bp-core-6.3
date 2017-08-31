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

import com.hybris.services.entitlements.condition.ConditionData;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class represents arguments for re-setting resources.
 */
@XmlRootElement(name = "listOfCondition")
public class ListOfConditionData
{
	@XmlElement(name="conditions")
	private List<ConditionData> conditionDataList;

	/**
	 * the Constructor.
	 */
	public ListOfConditionData()
	{

	}

	/**
	 * the Constructor.
	 *
	 * @param conditionDataList = the list of ConditionData.
	 */
	public ListOfConditionData(final List<ConditionData> conditionDataList)
	{
		this.conditionDataList = conditionDataList;
	}

	public List<ConditionData> getConditionDataList()
	{
		return conditionDataList;
	}
}
