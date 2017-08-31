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


import static junit.framework.Assert.assertEquals;
import static org.jgroups.util.Util.assertFalse;
import static org.jgroups.util.Util.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-service-test-spring.xml")
public class PositiveIntegerConversionStrategyTest
{
	@Autowired
	PositiveIntegerConversionStrategy strategy;

	@Test
	public void validateWrongValue()
	{
		ValidationViolations violations = new ValidationViolations();
		strategy.validate("field", "wrong", violations);
		assertTrue(violations.hasErrors());
	}

	@Test
	public void validatePositiveValue()
	{
		ValidationViolations violations = new ValidationViolations();
		strategy.validate("field", "12", violations);
		assertFalse(violations.hasErrors());
	}

	@Test
	public void validateZeroValue()
	{
		ValidationViolations violations = new ValidationViolations();
		strategy.validate("field", "0", violations);
		assertFalse(violations.hasErrors());
	}

	@Test
	public void validateNegativeValue()
	{
		ValidationViolations violations = new ValidationViolations();
		strategy.validate("field", "-3", violations);
		assertTrue(violations.hasErrors());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWrongValue()
	{
		strategy.convert("wrong value");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonString()
	{
		strategy.convert(new Integer(1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNull()
	{
		strategy.convert(null);
	}

	@Test//(expected = IllegalArgumentException.class)
	public void testOne()
	{
		assertEquals(1, (int) strategy.convert("1"));
	}

	@Test//(expected = IllegalArgumentException.class)
	public void testMoreThanOne()
	{
		assertEquals(2, (int) strategy.convert("2"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNegative()
	{
		strategy.convert("-1");
	}
}
