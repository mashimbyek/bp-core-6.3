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

import com.hybris.commons.conversion.ConversionException;
import com.hybris.commons.conversion.impl.AbstractPopulator;
import com.hybris.services.entitlements.condition.CriterionData;
import com.hybris.services.entitlements.domain.Criterion;

import java.io.Serializable;
import java.util.Map;

/**
 * Copies {@link CriterionData} values to {@link Criterion} container.
 */
public class CriterionDataToModelPopulator extends AbstractPopulator<CriterionData, Criterion>
{
	@Override
	public void populate(final CriterionData source, final Criterion target) throws ConversionException, IllegalArgumentException
	{
		target.setType(source.getType());
		for (final Map.Entry<String, Serializable> entry : source.getProperties().entrySet())
		{
			target.setProperty(entry.getKey(), (String) entry.getValue());
		}
	}
}
