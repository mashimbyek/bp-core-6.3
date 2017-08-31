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
package com.hybris.services.entitlements.atdd;

/**
 * Key Words Library Exception.
 */
public class KeyWordException extends RuntimeException
{
    /**
     * Constructor.
     * @param message Exception message.
     */
    public KeyWordException(final String message)
    {
        super(message);
    }

    /**
     * Constructor.
     * @param message  Exception message.
     * @param e Throwable.
     */
    public KeyWordException(final String message, final Throwable e)
    {
        super(message, e);
    }
}
