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
 * Copies fields from {@link ConditionData} DTO to a {@link Condition} model.
 */
public class ConditionDataToModelPopulator extends AbstractPopulator<ConditionData, Condition>
{
	private Populator<ConditionData, Condition> propertyPopulator;

    @Override
    public void populate(final ConditionData source, final Condition target) throws ConversionException
	{
		target.setType(source.getType());
		propertyPopulator.populate(source, target);
    }

	@Required
	public void setPropertyPopulator(final Populator<ConditionData, Condition> propertyPopulator)
	{
		this.propertyPopulator = propertyPopulator;
	}
}
