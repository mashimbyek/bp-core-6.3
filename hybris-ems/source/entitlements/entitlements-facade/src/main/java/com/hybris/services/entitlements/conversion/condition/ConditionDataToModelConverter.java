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
import com.hybris.services.entitlements.service.GrantService;

import org.springframework.beans.factory.annotation.Required;

/**
 * Convert condition DTO to condition model.
 */
public class ConditionDataToModelConverter extends AbstractPopulatingConverter<ConditionData, Condition>
{

    private GrantService grantService;

    @Override
    protected Condition createTarget()
	{
        return grantService.newCondition();
    }

    @Required
    public void setGrantService(final GrantService grantService)
	{
        this.grantService = grantService;
    }
}
