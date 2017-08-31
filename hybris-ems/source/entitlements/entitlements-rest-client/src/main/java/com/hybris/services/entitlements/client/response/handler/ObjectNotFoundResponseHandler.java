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
package com.hybris.services.entitlements.client.response.handler;

import com.hybris.commons.client.RestResponse;
import com.hybris.services.entitlements.api.ObjectNotFoundException;
import com.hybris.services.entitlements.api.codes.ResourceCode;

/**
 * Response handler for 404 response code - Object Not Found.
 */
public class ObjectNotFoundResponseHandler extends AbstractEntitlementRestResponseHandler
{
	/**
	 * The constructor.
	 */
    public ObjectNotFoundResponseHandler()
    {
        super(ResourceCode.CODE_404);
    }

    @Override
    protected Exception buildException(final RestResponse<?> response)
	{
        return new ObjectNotFoundException(getExceptionMessage(response));
    }
}
