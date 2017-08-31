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
package com.hybris.services.entitlements.condition.string;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import com.hybris.services.entitlements.validation.ValidationViolations;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Testing basic functionality of string condition strategy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-service-test-spring.xml")
public class StringConditionCheckerTest
{
	private static final String CONDITION_TYPE = "string";
	@Autowired
	@Qualifier("stringConditionExecutor")
	StringConditionStrategy stringConditionStrategy;

	private Map<String, String> correctMap;
	private Map<String, String> wrongMap;

	@Before
	public void onInit()
	{
		correctMap = new HashMap<>();
		correctMap.put(StringConditionStrategy.GRANT_PARAMETER_STRING, "testvalue");
		wrongMap = new HashMap<>();
		wrongMap.put("wrongstring", "testvalue");
	}

	@Test
	public void shouldHaveCorrectName()
	{
		assertEquals(CONDITION_TYPE, stringConditionStrategy.getConditionType());
	}

	@Test
	public void testValidateCreateParametersCorrect()
	{
		final ValidationViolations errors = new ValidationViolations();
		stringConditionStrategy.validateGrantParameters(correctMap, errors);
		assertFalse(errors.hasErrors());
	}

	@Test
	public void testValidateCreateParametersWrong()
	{
		final ValidationViolations errors = new ValidationViolations();
		stringConditionStrategy.validateGrantParameters(wrongMap, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void testValidateExecuteParametersCorrect()
	{
		final ValidationViolations errors = new ValidationViolations();
		stringConditionStrategy.validateExecutionParameters(correctMap, errors);
		assertFalse(errors.hasErrors());
	}

	@Test
	public void testValidateExecuteParametersWrong()
	{
		final ValidationViolations errors = new ValidationViolations();
		stringConditionStrategy.validateExecutionParameters(wrongMap, errors);
		assertTrue(errors.hasErrors());
	}
}
