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
package com.hybris.services.entitlements.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvocationTracer
{
	private final Logger LOG = LoggerFactory.getLogger(InvocationTracer.class);
	private List<ProceedingJoinPoint> calls = new ArrayList<>();

	public void clear()
	{
		calls.clear();
	}
	public List<ProceedingJoinPoint> getCalls()
	{
		return calls;
	}

	public Object intercept(final ProceedingJoinPoint call) throws Throwable
	{
		calls.add(call);
		return call.proceed();
	}

	public String printStat()
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Number of calls: "+calls.size());
		for (ProceedingJoinPoint call: calls)
		{
			stringBuilder.append("  ").append(call.toLongString());
		}
		return stringBuilder.toString();
	}
}
