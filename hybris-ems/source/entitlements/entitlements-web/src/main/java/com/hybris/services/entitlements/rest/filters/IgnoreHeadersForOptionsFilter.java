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
package com.hybris.services.entitlements.rest.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.Filter;

/**
 * Filter that allow to skip tenant header for <code>OPTIONS</code> method.
 */
public class IgnoreHeadersForOptionsFilter implements Filter
{
	public static final String ALLOWED_HEADERS = "allowedHeaders";
	private String allowedHeaders = "";

	protected void handle(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain)
			throws ServletException, IOException
	{
		if ("options".equalsIgnoreCase(request.getMethod()))
		{
			response.setHeader("Allow", allowedHeaders);
		}
		else
		{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException
	{
		allowedHeaders = filterConfig.getInitParameter(ALLOWED_HEADERS);
		if (allowedHeaders == null)
		{
			allowedHeaders = "GET,POST,PUT,DELETE,PATCH,OPTIONS,HEAD,CONNECT,TRACE";
		}
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException
	{
		handle((HttpServletRequest) request, (HttpServletResponse) response, chain);
	}

	@Override
	public void destroy()
	{
	}
}
