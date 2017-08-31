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

import com.hybris.commons.conversion.impl.AbstractPopulatingConverter;
import com.hybris.services.entitlements.api.GrantData;
import com.hybris.services.entitlements.domain.Grant;
import com.hybris.services.entitlements.service.GrantService;

import org.springframework.beans.factory.annotation.Required;

/**
 * Converts grant DTO to grant model.
 */
public class GrantDataToModelConverter extends AbstractPopulatingConverter<GrantData, Grant>
{
	private GrantService grantService;

	@Override
	protected Grant createTarget()
	{
		return grantService.newGrant();
	}

	@Required
	public void setGrantService(final GrantService grantService)
	{
		this.grantService = grantService;
	}
}
