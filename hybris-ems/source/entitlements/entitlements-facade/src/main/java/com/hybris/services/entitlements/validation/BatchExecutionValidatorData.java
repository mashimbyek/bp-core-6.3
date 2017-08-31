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
package com.hybris.services.entitlements.validation;

import com.hybris.services.entitlements.api.Constraint;
import com.hybris.services.entitlements.api.ExecuteManyData;
import com.hybris.services.entitlements.api.NotEmpty;

import java.util.List;

import javax.validation.Valid;

/**
 * Validates parameters of batch execution.
 *
 * {@link com.hybris.services.entitlements.facade.DefaultEntitlementFacade#execute(String, String, java.util.List, Boolean)}
 */
public class BatchExecutionValidatorData
{
	/**
	 * Execution action.
	 */
	@Constraint("checkActionTypeValidator")
	private final String action;

	/**
	 * User id.
	 */
	@NotEmpty
	private final String userId;

	/**
	 * Execution data.
	 */
	@Valid
	private final List<ExecuteManyData> executeManyDataList;

	/**
	 * Full constructor.
	 *
	 * @param action execution action
	 * @param userId user id
	 * @param executeManyDataList execution parameters
	 */
	public BatchExecutionValidatorData(final String action, final String userId, final List<ExecuteManyData> executeManyDataList)
	{
		this.action = action;
		this.userId = userId;
		this.executeManyDataList = executeManyDataList;
	}

	public List<ExecuteManyData> getExecuteManyDataList()
	{
		return executeManyDataList;
	}

	public String getAction()
	{
		return action;
	}

	public String getUserId()
	{
		return userId;
	}
}
