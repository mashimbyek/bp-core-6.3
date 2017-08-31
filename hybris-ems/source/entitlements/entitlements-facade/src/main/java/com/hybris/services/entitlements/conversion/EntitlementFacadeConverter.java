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
package com.hybris.services.entitlements.conversion;

import com.hybris.services.entitlements.api.GrantData;
import com.hybris.services.entitlements.condition.ConditionData;
import com.hybris.services.entitlements.condition.CriterionData;
import com.hybris.services.entitlements.domain.Condition;
import com.hybris.services.entitlements.domain.Criterion;
import com.hybris.services.entitlements.domain.Grant;

/**
 * Encapsulates conversion methods for {@link com.hybris.services.entitlements.facade.DefaultEntitlementFacade}.
 */
public interface EntitlementFacadeConverter
{
	/**
	 * Convert grant record.
	 *
	 * @param source source DTO
	 * @return model
	 */
	Grant convert(GrantData source);

	/**
	 * Creates Grant DTO by model.
	 *
	 * @param model source model
	 * @return DTO
	 */
	GrantData convert(Grant model);

	/**
	 * Creates Condition model by DTO.
	 *
	 * @param dto source DTO
	 * @return model
	 */
	Condition convert(ConditionData dto);

	/**
	 * Creates Criterion model by DTO.
	 *
	 * @param dto source DTO
	 * @return model
	 */
	Criterion convert(CriterionData dto);

	/**
	 * Converts Object to int.
	 *
	 * @param value Object
	 * @return int
	 */
	int convertToInt(Object value);
}
