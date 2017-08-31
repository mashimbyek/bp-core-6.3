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
package com.hybris.services.entitlements.condition.metered;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import com.hybris.services.entitlements.api.Actions;
import com.hybris.services.entitlements.condition.ActionHandler;
import com.hybris.services.entitlements.domain.Condition;
import com.hybris.services.entitlements.domain.Grant;
import com.hybris.services.entitlements.service.GrantService;
import com.hybris.services.entitlements.validation.ValidationViolations;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Testing basic functionality of metered condition strategy.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-service-test-spring.xml")
public class MeteredConditionStrategyTest
{

	private static final String CONDITION_TYPE = "metered";
	@Autowired
	@Qualifier("meteredConditionExecutor")
	private MeteredConditionStrategy meteredConditionStrategy;
	@Autowired
	private GrantService grantService;

	private ActionHandler checkHandler;
	private ActionHandler usageHandler;

	@Before
	public void init()
	{
		checkHandler = meteredConditionStrategy.getActionHandler(Actions.CHECK);
		usageHandler = meteredConditionStrategy.getActionHandler(Actions.USE);
	}

	@Test
	public void shouldHaveCorrectName()
	{
		assertEquals(CONDITION_TYPE, meteredConditionStrategy.getConditionType());
	}

	@Test
	public void shouldImplementCheckHandler()
	{
		assertNotNull(checkHandler);
	}

	@Test
	public void shouldImplementUsageHandler()
	{
		assertNotNull(usageHandler);
	}

	@Test
    public void testValidateCreateParametersCorrect()
    {
        final Map<String, String> correctMap = new HashMap<>();
        correctMap.put(MeteredConditionStrategy.GRANT_PARAMETER_MAX_QUANTITY, "2");

        final ValidationViolations errors = new ValidationViolations();
        meteredConditionStrategy.validateGrantParameters(correctMap, errors);

        assertFalse(errors.hasErrors());
    }

    @Test
    public void testValidateCreateParametersWrong()
    {
        final Map<String, String> wrongMapKey = new HashMap<>();
        wrongMapKey.put("wrong", "2");

        final ValidationViolations errorsKey = new ValidationViolations();
        meteredConditionStrategy.validateGrantParameters(wrongMapKey, errorsKey);
        assertTrue(errorsKey.hasErrors());

        final Map<String, String> wrongMapValue = new HashMap<>();
        wrongMapValue.put(MeteredConditionStrategy.GRANT_PARAMETER_MAX_QUANTITY, "A");

        final ValidationViolations errorsValue = new ValidationViolations();
        meteredConditionStrategy.validateGrantParameters(wrongMapValue, errorsValue);
        assertTrue(errorsValue.hasErrors());

        wrongMapValue.put(MeteredConditionStrategy.GRANT_PARAMETER_MAX_QUANTITY, "-1");

        final ValidationViolations errorsValueNeg = new ValidationViolations();
        meteredConditionStrategy.validateGrantParameters(wrongMapValue, errorsValueNeg);
        assertTrue(errorsValueNeg.hasErrors());
    }

    @Test
    public void testValidateExecuteParametersCorrect()
    {
        final Map<String, String> correctMap = new HashMap<>();
        correctMap.put(MeteredConditionStrategy.EXECUTION_PARAMETER_QUANTITY, "1");

        final ValidationViolations errors = new ValidationViolations();
        meteredConditionStrategy.validateExecutionParameters(correctMap, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void testValidateExecuteParametersWrong()
    {
        final Map<String, String> wrongMapKey = new HashMap<>();
        wrongMapKey.put("wrong", "2");

        final ValidationViolations errorsKey = new ValidationViolations();
        meteredConditionStrategy.validateExecutionParameters(wrongMapKey, errorsKey);
        assertTrue(errorsKey.hasErrors());

        final Map<String, String> wrongMapValue = new HashMap<>();
        wrongMapValue.put(MeteredConditionStrategy.EXECUTION_PARAMETER_QUANTITY, "A");

        final ValidationViolations errorsValue = new ValidationViolations();
        meteredConditionStrategy.validateExecutionParameters(wrongMapValue, errorsValue);
        assertTrue(errorsValue.hasErrors());

        wrongMapValue.put(MeteredConditionStrategy.EXECUTION_PARAMETER_QUANTITY, "-1");

        final ValidationViolations errorsValueNeg = new ValidationViolations();
        meteredConditionStrategy.validateExecutionParameters(wrongMapValue, errorsValueNeg);
        assertTrue(errorsValueNeg.hasErrors());
    }

    @Test
	@Transactional
    public void testExecuteConditionCorrect()
    {
        final Map<String, String> correctMapExec = new HashMap<>();
        correctMapExec.put(MeteredConditionStrategy.EXECUTION_PARAMETER_QUANTITY, "1");

		final Condition condition = getCondition(10);
		final Grant grant = getGrant(condition);
		assertFalse(checkHandler.applicable(null, correctMapExec));
		assertTrue(checkHandler.applicable(condition, correctMapExec));
        assertTrue(checkHandler.execute(condition, correctMapExec, grant));
    }

	@Test
	@Transactional
	public void shouldAcceptAnyPositiveUnitNumber()
	{
		final Map<String, String> correctMapExec = new HashMap<>();
		correctMapExec.put(MeteredConditionStrategy.EXECUTION_PARAMETER_QUANTITY, "4");

		final Condition condition = getCondition(10);
		final Grant grant = getGrant(condition);
		assertTrue(checkHandler.execute(condition, correctMapExec, grant));
		assertTrue(checkHandler.execute(condition, correctMapExec, grant));
		assertFalse(checkHandler.applicable(null, correctMapExec));
	}

	@Test
	@Transactional
	public void shouldRejectOverage()
	{
		final Map<String, String> correctMapExec = new HashMap<>();
		correctMapExec.put(MeteredConditionStrategy.EXECUTION_PARAMETER_QUANTITY, "8");

		final Condition condition = getCondition(7);
		final Grant grant = getGrant(condition);
		assertFalse(checkHandler.execute(condition, correctMapExec, grant));
	}

	@Test
	@Transactional
    public void testExecuteConditionDefault()
    {
        assertFalse(checkHandler.applicable(getCondition(10), null));
    }

	@Test
	@Transactional
	public void shouldDecrementCondition()
	{
		final Condition condition = getCondition(1);
		final Map<String, String> executionParameters = new HashMap<>();
		final Grant grant = getGrant(condition);
		executionParameters.put(MeteredConditionStrategy.EXECUTION_PARAMETER_QUANTITY, "1");
		assertTrue(checkHandler.execute(condition, executionParameters, grant));
		assertTrue(usageHandler.execute(condition, executionParameters, grant));
		assertFalse(checkHandler.execute(condition, executionParameters, grant));
	}

	@Test(expected = IllegalArgumentException.class)
	@Transactional
	public void shouldCheckMapAtRuntime()
	{
		final Condition condition = getCondition(10);
		final Grant grant = getGrant(condition);
		checkHandler.execute(condition, new HashMap<String, String>(), grant);
	}

	@Test(expected = IllegalArgumentException.class)
	@Transactional
	public void shouldCheckIntAtRuntime()
	{
		final HashMap<String, String> map = new HashMap<>();
		map.put(MeteredConditionStrategy.EXECUTION_PARAMETER_QUANTITY, "1a");
		final Condition condition = getCondition(10);
		final Grant grant = getGrant(condition);
		checkHandler.execute(getCondition(10), map, grant);
	}

	@Test
	@Transactional
	public void testNotAllowOverage()
	{
		final Condition condition = getCondition(1);
		final Map<String, String> executionParameters = new HashMap<>();
		final Grant grant = getGrant(condition);
		executionParameters.put(MeteredConditionStrategy.EXECUTION_PARAMETER_QUANTITY, "1");
		assertFalse(checkHandler.applicable(condition, null));
		assertFalse(usageHandler.applicable(condition, null));
		assertFalse(checkHandler.applicable(null, executionParameters));
		assertFalse(usageHandler.applicable(null, executionParameters));
		assertTrue(usageHandler.execute(condition, executionParameters, grant));
		assertFalse(usageHandler.execute(condition, executionParameters, grant));
		assertFalse(usageHandler.execute(condition, executionParameters, grant));

		ValidationViolations validationViolations = new ValidationViolations();
		meteredConditionStrategy.validateProperty("remainingQuantity", "0", validationViolations);
		assertFalse(validationViolations.hasErrors());
		meteredConditionStrategy.validateProperty("remainingQuantity", "1", validationViolations);
		assertFalse(validationViolations.hasErrors());
		meteredConditionStrategy.validateProperty("remainingQuantity", "-1", validationViolations);
		assertFalse(validationViolations.hasErrors());
		ValidationViolations validationViolations2 = new ValidationViolations();
		meteredConditionStrategy.validateProperty("remainingQuantity", "wrong value", validationViolations2);
		assertTrue(validationViolations2.hasErrors());
	}

	@Test
	@Transactional
	public void testAllowOverage()
	{
		final Condition condition = getCondition(1);
		final Map<String, String> executionParameters = new HashMap<>();
		final Grant grant = getGrant(condition);
		condition.setProperty(MeteredConditionStrategy.GRANT_PARAMETER_OVERAGE, "True");
		executionParameters.put(MeteredConditionStrategy.EXECUTION_PARAMETER_QUANTITY, "1");
		assertFalse(usageHandler.applicable(condition, null));
		assertFalse(usageHandler.applicable(null, executionParameters));
		assertTrue(usageHandler.applicable(condition, executionParameters));
		assertTrue(usageHandler.execute(condition, executionParameters, grant));
		assertTrue(usageHandler.execute(condition, executionParameters, grant));
		assertTrue(usageHandler.execute(condition, executionParameters, grant));

		ValidationViolations validationViolations = new ValidationViolations();
		meteredConditionStrategy.validateProperty("remainingQuantity", "0", validationViolations);
		assertFalse(validationViolations.hasErrors());
		meteredConditionStrategy.validateProperty("remainingQuantity", "1", validationViolations);
		assertFalse(validationViolations.hasErrors());
		meteredConditionStrategy.validateProperty("remainingQuantity", "-1", validationViolations);
		assertFalse(validationViolations.hasErrors());
		ValidationViolations validationViolations2 = new ValidationViolations();
		meteredConditionStrategy.validateProperty("remainingQuantity", "wrong value", validationViolations2);
		assertTrue(validationViolations2.hasErrors());
	}

	@Test
	@Transactional
	public void shouldAssignRemainingQuantityDuringConditionUpdate()
	{
		final int quantity = 5;
		final Condition condition = getCondition(quantity);
		final Grant grant = getGrant(condition);
		final String id = grant.getGrantId();
		grantService.updateConditions(id, Arrays.asList(condition));
		final Grant updatedGrant = grantService.getGrant(id);
		assertEquals(Integer.toString(quantity), updatedGrant.getProperty(MeteredConditionStrategy.PARAMETER_REMAINING_QUANTITY));
	}

	@Test
	@Transactional
	public void shouldNotChangeTheExistingRemainingQuantity()
	{
		final int quantity = 5;
		final Condition condition = getCondition(quantity);
		final Grant grant = getGrant(condition);
		final String id = grant.getGrantId();
		grantService.updateConditions(id, Arrays.asList(condition));
		final Grant updatedGrant = grantService.getGrant(id);
		grantService.updateConditions(id, Arrays.asList(getCondition(1)));
		assertEquals(Integer.toString(quantity), updatedGrant.getProperty(MeteredConditionStrategy.PARAMETER_REMAINING_QUANTITY));
	}

	private Condition getCondition(final int quantity)
	{
		final Condition meteredCondition = grantService.newCondition();
		meteredCondition.setType(CONDITION_TYPE);
		meteredCondition.setProperty(MeteredConditionStrategy.GRANT_PARAMETER_MAX_QUANTITY, Integer.toString(quantity));
		return meteredCondition;
	}

	private Grant getGrant(Condition ... conditions)
	{
		final Grant model = grantService.newGrant();
		model.setUserId(UUID.randomUUID().toString());
		model.setGrantId(UUID.randomUUID().toString());
		model.setEntitlementType("test");
		model.setGrantSource("getGrant");
		model.setGrantSourceId("1");
		model.setGrantTime(new Date());
		model.setConditions(Arrays.asList(conditions));
		return model;
	}
}
