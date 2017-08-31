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
package com.hybris.services.entitlements.conversion.condition;

import com.hybris.commons.conversion.impl.AbstractPopulatingConverter;
import com.hybris.services.entitlements.condition.ConditionData;
import com.hybris.services.entitlements.domain.Condition;

/**
 * Convert condition model to condition DTO.
 */
public class ConditionModelToDataConverter extends AbstractPopulatingConverter<Condition, ConditionData>
{
	@Override
	protected ConditionData createTarget()
	{
		return new ConditionData();
	}
}
