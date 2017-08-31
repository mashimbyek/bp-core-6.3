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
package com.hybris.services.entitlements.rest.exception.mappers;


import com.hybris.services.entitlements.api.ObjectNotFoundException;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Mapper to convert ObjectNotFoundException to HTTP response 404.
 */
@Provider
public class ObjectNotFoundExceptionMapper implements ExceptionMapper<ObjectNotFoundException>
{
	private static final Logger LOG = LoggerFactory.getLogger(ObjectNotFoundExceptionMapper.class);

	@Override
	public Response toResponse(final ObjectNotFoundException exception)
	{
		final StringWriter sw = new StringWriter();
		final PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		LOG.warn(sw.toString());
		return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).type(MediaType.TEXT_PLAIN_TYPE)
				.build();
	}
}
