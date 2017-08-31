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

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * DTO for batch execution.
 * {@link EntitlementFacade#execute(String, String, java.util.List, Boolean)}
 */
@XmlRootElement(name="executeManyData")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExecuteManyData
{
	@NotEmpty
	private String entitlementType;

    @XmlElementWrapper
    @Valid
	private List<CriterionData> criterionDataList;

	public String getEntitlementType()
	{
		return entitlementType;
	}

	public void setEntitlementType(final String entitlementType)
	{
		this.entitlementType = entitlementType;
	}

	public List<CriterionData> getCriterionDataList()
	{
		return criterionDataList;
	}

	public void setCriterionDataList(final List<CriterionData> criterionDataList)
	{
		this.criterionDataList = criterionDataList;
	}
}
