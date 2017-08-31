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
package com.hybris.services.entitlements.conversion.grant;

import com.hybris.commons.conversion.ConversionException;
import com.hybris.commons.conversion.Populator;
import com.hybris.commons.conversion.impl.AbstractPopulatingConverter;
import com.hybris.commons.conversion.impl.AbstractPopulator;
import com.hybris.services.entitlements.api.GrantData;
import com.hybris.services.entitlements.api.status.Status;
import com.hybris.services.entitlements.condition.ConditionData;
import com.hybris.services.entitlements.conversion.DateTimeConverter;
import com.hybris.services.entitlements.domain.Condition;
import com.hybris.services.entitlements.domain.Grant;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

/**
 * Copies content from grant DTO to grant model.
 */
public class GrantDataToModelPopulator extends AbstractPopulator<GrantData, Grant>
{
    private AbstractPopulatingConverter<ConditionData, Condition> conditionDataToModelConverter;
	private DateTimeConverter dateTimeConverter;
	private Populator<GrantData, Grant> propertyPopulator;

    @Override
    public void populate(final GrantData source, final Grant destination) throws ConversionException
	{
		destination.setGrantId(source.getId());
        destination.setUserId(source.getUserId());
        destination.setGrantSource(source.getGrantSource());
        destination.setGrantSourceId(source.getGrantSourceId());
		destination.setStatus(source.getStatus() != null ? source.getStatus().name() : Status.ACTIVE.name());
		try
		{
			destination.setGrantTime(dateTimeConverter.convertStringToDate(source.getGrantTime()));
		}
		catch (final ParseException | IllegalArgumentException e)
		{
			throw new ConversionException("Wrong Date time format", e);
		}
        destination.setEntitlementType(source.getEntitlementType());
        final List<Condition> conditions = new ArrayList<>();
        if (source.getConditions() != null)
        {
            for (final ConditionData condition : source.getConditions())
            {
                conditions.add(conditionDataToModelConverter.convert(condition));
            }
        }
        destination.setConditions(conditions);
		propertyPopulator.populate(source, destination);
    }

	@Required
	public void setConditionDataToModelConverter(
			final AbstractPopulatingConverter<ConditionData, Condition> conditionDataToModelConverter)
	{
		this.conditionDataToModelConverter = conditionDataToModelConverter;
	}

	@Required
	public void setDateTimeConverter(final DateTimeConverter dateTimeConverter)
	{
		this.dateTimeConverter = dateTimeConverter;
	}

	@Required
	public void setPropertyPopulator(final Populator<GrantData, Grant> propertyPopulator)
	{
		this.propertyPopulator = propertyPopulator;
	}
}
