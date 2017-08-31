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

import com.hybris.commons.client.AbstractExceptionStatusRestResponseHandler;
import com.hybris.commons.client.RestResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract class for Entitlement Rest Client response handler.
 */
public abstract class AbstractEntitlementRestResponseHandler extends AbstractExceptionStatusRestResponseHandler
{
    private final Logger logger = LoggerFactory.getLogger(AbstractEntitlementRestResponseHandler.class);

	/**
	 * The Constructor.
	 *
	 * @param status the status
	 */
    public AbstractEntitlementRestResponseHandler(final int status)
    {
        super(status);
    }

    /**
     * Retrieve exception message as String from response body.
     *
     * @param response the response.
     * @return exception message or null if nothing found or exception occurred while trying
     */
    protected String getExceptionMessage(final RestResponse<?> response)
    {
        String exceptionMessage = null;
        try
        {
            exceptionMessage = response.getResponse().readEntity(String.class);
        }
        catch (final Exception e)
        {
            // it doesn't really matter what happened -
            //   we have nothing to do with it here
            logger.error("Failed to get exception message from response body: " + e.getMessage());
        }
        return exceptionMessage;
    }
}
