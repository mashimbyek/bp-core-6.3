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
import com.hybris.services.entitlements.api.UnprocessableEntityException;
import com.hybris.services.entitlements.condition.ConditionData;
import com.hybris.services.entitlements.condition.CriterionData;
import com.hybris.services.entitlements.conversion.condition.ConditionDataToModelConverter;
import com.hybris.services.entitlements.conversion.criterion.CriterionDataToModelConverter;
import com.hybris.services.entitlements.conversion.grant.GrantDataToModelConverter;
import com.hybris.services.entitlements.conversion.grant.GrantModelToDataConverter;
import com.hybris.services.entitlements.domain.Condition;
import com.hybris.services.entitlements.domain.Criterion;
import com.hybris.services.entitlements.domain.Grant;
import com.hybris.services.entitlements.validation.AbstractConversionStrategy;

import org.springframework.beans.factory.annotation.Required;

/**
 * Default implementation of {@link EntitlementFacadeConverter}.
 *
 * @see com.hybris.services.entitlements.api.EntitlementFacade
 * @see com.hybris.services.entitlements.facade.DefaultEntitlementFacade
 */
public class DefaultEntitlementFacadeConverter implements EntitlementFacadeConverter
{
	private GrantDataToModelConverter grantDataToModelConverter;
	private GrantModelToDataConverter grantModelToDataConverter;
	private CriterionDataToModelConverter criterionDataToModelConverter;
	private ConditionDataToModelConverter conditionDataToModelConverter;
	private AbstractConversionStrategy<Integer> stringToIntConverter;

	@Override
	public Grant convert(final GrantData source)
	{
		return grantDataToModelConverter.convert(source);
	}

	@Override
	public GrantData convert(final Grant model)
	{
		return grantModelToDataConverter.convert(model);
	}

	@Override
	public Condition convert(final ConditionData dto)
	{
		return conditionDataToModelConverter.convert(dto);
	}

	@Override
	public Criterion convert(final CriterionData dto)
	{
		return criterionDataToModelConverter.convert(dto);
	}

	@Override
	public int convertToInt(final Object value)
	{
		try
		{
			return stringToIntConverter.convert(value);
		}
		catch (final IllegalArgumentException e)
		{
			throw new UnprocessableEntityException(e.getMessage(), e);
		}
	}

	@Required
	public void setGrantDataToModelConverter(final GrantDataToModelConverter grantDataToModelConverter)
	{
		this.grantDataToModelConverter = grantDataToModelConverter;
	}

	@Required
	public void setGrantModelToDataConverter(final GrantModelToDataConverter grantModelToDataConverter)
	{
		this.grantModelToDataConverter = grantModelToDataConverter;
	}

	@Required
	public void setCriterionDataToModelConverter(final CriterionDataToModelConverter criterionDataToModelConverter)
	{
		this.criterionDataToModelConverter = criterionDataToModelConverter;
	}

	@Required
	public void setConditionDataToModelConverter(final ConditionDataToModelConverter conditionDataToModelConverter)
	{
		this.conditionDataToModelConverter = conditionDataToModelConverter;
	}

	@Required
	public void setStringToIntConverter(final AbstractConversionStrategy<Integer> stringToIntConverter)
	{
		this.stringToIntConverter = stringToIntConverter;
	}
}
