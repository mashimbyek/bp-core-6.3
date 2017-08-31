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
import com.hybris.services.entitlements.api.NotEmpty;
import com.hybris.services.entitlements.condition.CriterionData;

import java.util.List;

import javax.validation.Valid;

/**
 * Helper class to provide validation rules for grant execution.
 *
 * {@link com.hybris.services.entitlements.facade.DefaultEntitlementFacade#execute(String, String, String, java.util.List, boolean)}
 */
public class ExecutionValidatorData
{
	/**
	 * Entitlement type.
	 */
	@NotEmpty
	private final String entitlementType;

	/**
	 * User id.
	 */
	@NotEmpty
	private final String userId;

	/**
	 * Execution criteria.
	 */
	@Valid
	private final List<CriterionData> criteria;

	/**
	 * Execution action.
	 */
	@Constraint("actionTypeValidator")
	private final String action;

	/**
	 * Full constructor.
	 *
	 * @param entitlementType entitlement type
	 * @param userId user id
	 * @param criteria execution criteria
	 * @param action execution action
	 */
	public ExecutionValidatorData(final String entitlementType, final String userId, final List<CriterionData> criteria,
			final String action)
	{
		this.entitlementType = entitlementType;
		this.userId = userId;
		this.action = action;
		this.criteria = criteria;
	}

	public String getEntitlementType()
	{
		return entitlementType;
	}

	public String getUserId()
	{
		return userId;
	}

	public List<CriterionData> getCriteria()
	{
		return criteria;
	}

	public String getAction()
	{
		return action;
	}
}
