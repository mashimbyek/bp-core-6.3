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
package com.hybris.services.entitlements.conversion.criterion;

import com.hybris.commons.conversion.impl.AbstractPopulatingConverter;
import com.hybris.services.entitlements.condition.CriterionData;
import com.hybris.services.entitlements.domain.Criterion;

/**
 * Convert {@link CriterionData} DTO to service layer object {@link Criterion}.
 */
public class CriterionDataToModelConverter extends AbstractPopulatingConverter<CriterionData, Criterion>
{
	@Override
	protected Criterion createTarget()
	{
		return new Criterion();
	}
}
