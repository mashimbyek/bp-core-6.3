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
package com.hybris.services.entitlements.rest;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * This class configures Jersey.
 */
public class MyAppResourceConfig extends ResourceConfig
{
    public MyAppResourceConfig()
    {
        register(JacksonJaxbJsonProvider.class); // Workaround for https://github.com/FasterXML/jackson-jaxrs-providers/issues/22
        packages("com.hybris.services.entitlements.rest.exception.mappers");
    }
}