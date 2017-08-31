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
package com.hybris.services.entitlements.validation;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-service-test-spring.xml")
public class DateTimeConversionStrategyTest
{
	@Autowired
	private DateTimeConversionStrategy dateTimeConversionStrategy;

	@Test
	public void testWrongValue()
	{
		ValidationViolations validationViolations = new ValidationViolations();
		dateTimeConversionStrategy.validate("WrongValue", validationViolations);
		assertTrue(validationViolations.hasErrors());
	}

	@Test
	public void testNull()
	{
		ValidationViolations validationViolations = new ValidationViolations();
		dateTimeConversionStrategy.validate(null, validationViolations);
		assertFalse(validationViolations.hasErrors());
	}

	@Test
	public void testSupportsClassString()
	{
		assertTrue(dateTimeConversionStrategy.supportsClass(String.class));
	}

	@Test
	public void testSupportsClassLong()
	{
		assertFalse(dateTimeConversionStrategy.supportsClass(Long.class));
	}
}
