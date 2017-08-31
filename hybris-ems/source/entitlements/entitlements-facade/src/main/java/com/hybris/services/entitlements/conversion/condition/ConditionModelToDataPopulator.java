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

import com.hybris.commons.conversion.ConversionException;
import com.hybris.commons.conversion.Populator;
import com.hybris.commons.conversion.impl.AbstractPopulator;
import com.hybris.services.entitlements.condition.ConditionData;
import com.hybris.services.entitlements.domain.Condition;

import org.springframework.beans.factory.annotation.Required;

/**
 * Copy properties from condition model to condition DTO.
 */
public class ConditionModelToDataPopulator extends AbstractPopulator<Condition, ConditionData>
{
	private Populator<Condition, ConditionData> propertyPopulator;

    @Override
    public void populate(final Condition source, final ConditionData target)
			throws ConversionException
	{
		target.setType(source.getType());
		propertyPopulator.populate(source, target);
    }

	@Required
	public void setPropertyPopulator(final Populator<Condition, ConditionData> propertyPopulator)
	{
		this.propertyPopulator = propertyPopulator;
	}
}
