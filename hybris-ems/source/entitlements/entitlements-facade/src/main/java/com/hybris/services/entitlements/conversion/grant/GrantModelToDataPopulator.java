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

import org.springframework.beans.factory.annotation.Required;

/**
 * Copies entitlement model fields to entitlement DTO.
 */
public class GrantModelToDataPopulator extends AbstractPopulator<Grant, GrantData>
{
	private AbstractPopulatingConverter<Condition, ConditionData> conditionModelToDataConverter;
	private DateTimeConverter dateTimeConverter;
	private Populator<Grant, GrantData> propertyPopulator;

	@Override
	public void populate(final Grant source, final GrantData destination)
			throws ConversionException
	{
		destination.setId(source.getGrantId());
		destination.setUserId(source.getUserId());
		destination.setGrantSource(source.getGrantSource());
		destination.setGrantSourceId(source.getGrantSourceId());
		destination.setGrantTime(dateTimeConverter.convertDateToString(source.getGrantTime()));
		destination.setEntitlementType(source.getEntitlementType());
		destination.setStatus(Status.valueOf(source.getStatus()));
		for (final Condition condition : source.getConditions())
		{
			destination.addCondition(conditionModelToDataConverter.convert(condition));
		}
		propertyPopulator.populate(source, destination);
	}

	@Required
	public void setConditionModelToDataConverter(
			final AbstractPopulatingConverter<Condition, ConditionData> conditionModelToDataConverter)
	{
		this.conditionModelToDataConverter = conditionModelToDataConverter;
	}

	@Required
	public void setDateTimeConverter(final DateTimeConverter dateTimeConverter)
	{
		this.dateTimeConverter = dateTimeConverter;
	}

	@Required
	public void setPropertyPopulator(final Populator<Grant, GrantData> propertyPopulator)
	{
		this.propertyPopulator = propertyPopulator;
	}
}
