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
package com.hybris.services.entitlements.api.status;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Grant status DTO.
 */
@XmlRootElement
public class StatusData
{
    private Status status;

    /**
     * Default constructor.
     */
    public StatusData()
    {

    }

    /**
     * Constructor with arg.
     * @param statusArg Status
     */
    public StatusData(final Status statusArg)
    {
        this.status = statusArg;
    }

    /**
     * Returns Status.
     * @return Status
     */
    public Status getStatus()
    {
        return status;
    }

    /**
     * Sets status.
     * @param statusArg Status
     */
    public void setStatus(final Status statusArg)
    {
        this.status = statusArg;
    }
}
