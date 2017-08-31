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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class represent result of {@link EntitlementFacade#execute(String, String, java.util.List, Boolean)}.
 */
@XmlRootElement//(name="GetGrantResult")
public class ExecuteManyResult
{

    @XmlElementWrapper(name="executeResultList")
    @XmlElement(name="executeResult")
	private final List<ExecuteResult> executeResultList = new ArrayList<>();

	/**
	 * the Constructor.
	 */
	public ExecuteManyResult()
	{
	}

	public List<ExecuteResult> getExecuteResultList()
	{
		return executeResultList;
	}

	/**
	 * Add a result to the list.
	 *
	 * @param executeResult result to add
	 */
	public void addExecuteResult(final ExecuteResult executeResult)
	{
		executeResultList.add(executeResult);
	}
}
