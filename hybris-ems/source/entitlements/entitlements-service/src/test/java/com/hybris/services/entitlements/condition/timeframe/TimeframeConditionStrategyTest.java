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
package com.hybris.services.entitlements.condition.timeframe;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import com.hybris.services.entitlements.condition.ActionHandler;
import com.hybris.services.entitlements.conversion.DateTimeConverter;
import com.hybris.services.entitlements.conversion.PropertiesToMapConverter;
import com.hybris.services.entitlements.domain.Condition;
import com.hybris.services.entitlements.service.GrantService;
import com.hybris.services.entitlements.validation.ValidationViolations;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
* Testing basic functionality of string condition strategy
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-service-test-spring.xml")
public class TimeframeConditionStrategyTest
{
	private static final String CONDITION_TYPE = "timeframe";
	@Autowired
	@Qualifier("timeframeConditionExecutor")
	private TimeframeConditionStrategy timeframeConditionStrategy;
	@Autowired
	private DateTimeConverter dateTimeConverter;
	@Autowired
	private GrantService grantService;

	private Map<String, String> defaultMap;
	private Map<String, String> correctMap;
	private Map<String, String> wrongMap;

	private ActionHandler checkHandler;

	@Before
	public void onInit()
	{
		checkHandler = timeframeConditionStrategy.getActionHandler(null);
		correctMap = new HashMap<>();
		correctMap.put(TimeframeConditionStrategy.EXECUTION_PARAMETER_TIME,
				dateTimeConverter.convertDateToString(new Date(System.currentTimeMillis())));
		defaultMap = new HashMap<>();
		wrongMap = new HashMap<>();
		wrongMap.put(TimeframeConditionStrategy.EXECUTION_PARAMETER_TIME, "qweqwe");
	}

	@Test
	public void shouldHaveCorrectName()
	{
		assertEquals(CONDITION_TYPE, timeframeConditionStrategy.getConditionType());
	}

	@Test
	@Transactional
	public void shouldValidateCorrectCreationParameters()
	{
		final Map<String, String> map = PropertiesToMapConverter.convert(getCorrectCondition().getAllProperties());
		final ValidationViolations errors = new ValidationViolations();
		timeframeConditionStrategy.validateGrantParameters(map, errors);
		assertFalse(errors.hasErrors());
	}

	@Test
	@Transactional
	public void shouldRejectReversedInterval()
	{
		final Map<String,String> timeMachine = new HashMap<>();
		timeMachine.put(TimeframeConditionStrategy.GRANT_PARAMETER_END,
				dateTimeConverter.convertDateToString(new Date(System.currentTimeMillis() - 10000)));
		timeMachine.put(TimeframeConditionStrategy.GRANT_PARAMETER_START,
				dateTimeConverter.convertDateToString(new Date()));
		final ValidationViolations errors = new ValidationViolations();
		timeframeConditionStrategy.validateGrantParameters(timeMachine, errors);
		assertTrue(errors.hasErrors());
	}

    @Test
    @Transactional
	public void shouldValidateNonEndGrantParameter()
	{
		final Map<String,String> timeMachine = new HashMap<>();
		timeMachine.put(TimeframeConditionStrategy.GRANT_PARAMETER_START,
				dateTimeConverter.convertDateToString(new Date()));
		final ValidationViolations errors = new ValidationViolations();
		timeframeConditionStrategy.validateGrantParameters(timeMachine, errors);
		assertFalse(errors.hasErrors());
	}

	@Test
	@Transactional
	public void shouldValidateGrantParameter()
	{
		final Map<String,String> timeMachine = new HashMap<>();
		timeMachine.put(TimeframeConditionStrategy.GRANT_PARAMETER_START,
				dateTimeConverter.convertDateToString(new Date()));
		timeMachine.put(TimeframeConditionStrategy.GRANT_PARAMETER_END,
				dateTimeConverter.convertDateToString(new Date(System.currentTimeMillis() + Short.MAX_VALUE)));
		final ValidationViolations errors = new ValidationViolations();
		timeframeConditionStrategy.validateGrantParameters(timeMachine, errors);
		assertFalse(errors.hasErrors());
	}

    @Test
    @Transactional
	public void shouldRejectNonStartGrantParameter()
	{
		final Map<String,String> timeMachine = new HashMap<>();
		timeMachine.put(TimeframeConditionStrategy.GRANT_PARAMETER_END,
				dateTimeConverter.convertDateToString(new Date()));
		final ValidationViolations errors = new ValidationViolations();
		timeframeConditionStrategy.validateGrantParameters(timeMachine, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	@Transactional
	public void shouldRejectNonStartNonEndGrantParameter()
	{
		final Map<String,String> timeMachine = new HashMap<>();
		final ValidationViolations errors = new ValidationViolations();
		timeframeConditionStrategy.validateGrantParameters(timeMachine, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	@Transactional
	public void shouldReportNameErrors()
	{
		final ValidationViolations errors = new ValidationViolations();
		timeframeConditionStrategy.validateGrantParameters(wrongMap, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	@Transactional
	public void shouldReportValueErrors()
	{
		final ValidationViolations errors = new ValidationViolations();
		timeframeConditionStrategy.validateGrantParameters(
				PropertiesToMapConverter.convert(getWrongTypeCondition().getAllProperties()), errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	@Transactional
	public void shouldValidateCorrectExecutionParameters()
	{
		final ValidationViolations errors = new ValidationViolations();
		timeframeConditionStrategy.validateExecutionParameters(correctMap, errors);
		assertFalse(errors.hasErrors());
	}

	@Test
	public void shouldRejectEmptyExecutionParameters()
	{
		final ValidationViolations errors = new ValidationViolations();
		timeframeConditionStrategy.validateExecutionParameters(defaultMap, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void shouldRejectWrongExecutionParameters()
	{
		final ValidationViolations errors = new ValidationViolations();
		timeframeConditionStrategy.validateExecutionParameters(wrongMap, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	@Transactional
	public void testExecuteConditionCorrect()
	{
		final Condition correctCondition = getCorrectCondition();
		assertTrue(checkHandler.applicable(correctCondition, correctMap));
		assertTrue(checkHandler.execute(correctCondition, correctMap, null));
	}

	@Test
	@Transactional
	public void shouldUseDefaultTime()
	{
		final Condition correctCondition = getCorrectCondition();
		assertTrue(checkHandler.applicable(correctCondition, null));
		assertTrue(checkHandler.execute(correctCondition, null, null));
	}

	@Test
	public void shouldUseInfiniteTimeframe()
	{
		assertTrue(checkHandler.applicable(null, correctMap));
		assertTrue(checkHandler.execute(null, correctMap, null));
	}

	@Test(expected = IllegalArgumentException.class)
	@Transactional
	public void shouldRejectInvalidConditions()
	{
		checkHandler.execute(getWrongCondition(), correctMap, null);
	}

	private Condition getWrongTypeCondition()
	{
		Condition wrongTypeCondition = grantService.newCondition();
		wrongTypeCondition.setType("unknowntype");
		return wrongTypeCondition;
	}

	private Condition getWrongCondition()
	{
		Condition wrongCondition = grantService.newCondition();
		wrongCondition.setType(CONDITION_TYPE);
		wrongCondition.setProperty("wrongstring", "testvalue");
		wrongCondition.setProperty(TimeframeConditionStrategy.GRANT_PARAMETER_END, "wrongValue");
		return wrongCondition;
	}

	private Condition getCorrectCondition()
	{
		Condition correctCondition = grantService.newCondition();
		correctCondition.setProperty(TimeframeConditionStrategy.GRANT_PARAMETER_START,
				dateTimeConverter.convertDateToString(new Date(System.currentTimeMillis() - 10000)));
		correctCondition.setProperty(TimeframeConditionStrategy.GRANT_PARAMETER_END,
				dateTimeConverter.convertDateToString(new Date(System.currentTimeMillis() + 1000000)));
		return correctCondition;
	}
}
