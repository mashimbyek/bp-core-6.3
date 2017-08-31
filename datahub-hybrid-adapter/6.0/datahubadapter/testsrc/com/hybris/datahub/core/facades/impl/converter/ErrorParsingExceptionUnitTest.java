/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */

package com.hybris.datahub.core.facades.impl.converter;

import de.hybris.bootstrap.annotations.UnitTest;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;


@UnitTest
@SuppressWarnings("javadoc")
public class ErrorParsingExceptionUnitTest
{
	@Test
	public void testInstantiatedExceptionContainsTheRootCause()
	{
		final Exception rootCause = new IOException();
		final ErrorParsingException ex = new ErrorParsingException(rootCause);

		Assert.assertSame(rootCause, ex.getCause());
	}
}
