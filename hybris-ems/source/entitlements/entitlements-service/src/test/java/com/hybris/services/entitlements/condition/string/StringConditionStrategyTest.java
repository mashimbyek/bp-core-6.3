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

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import com.hybris.services.entitlements.condition.ActionHandler;
import com.hybris.services.entitlements.domain.Condition;
import com.hybris.services.entitlements.domain.Grant;
import com.hybris.services.entitlements.service.GrantService;

import java.util.Arrays;
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
public class StringConditionStrategyTest
{
	private static final String CONDTION_TYPE = "string";

	@Autowired
	private GrantService grantService;

	@Autowired
	@Qualifier("stringConditionExecutor")
	StringConditionStrategy stringConditionStrategy;

	private ActionHandler checkHandler;

	@Before
	public void init()
	{
		checkHandler = stringConditionStrategy.getActionHandler(null);
	}

	@Test
	public void shouldImplementCheckHandler()
	{
		assertNotNull(checkHandler);
	}

	@Test
	@Transactional
	public void test5()
	{
		final Condition condition = getCondition("1");
		final Map<String, String> executionParameters = new HashMap<>();
		final Grant grant = getGrant(condition);
		assertFalse(checkHandler.applicable(condition, null));
		assertFalse(checkHandler.applicable(null, executionParameters));
		assertTrue(checkHandler.applicable(condition, executionParameters));
		assertFalse(checkHandler.execute(condition, executionParameters, grant));
		executionParameters.put(StringConditionStrategy.EXECUTION_PARAMETER_STRING, "1");
		assertTrue(checkHandler.execute(condition, executionParameters, grant));
	}

	private Condition getCondition(final String text)
	{
		final Condition condition = grantService.newCondition();
		condition.setType(CONDTION_TYPE);
		condition.setProperty(StringConditionStrategy.GRANT_PARAMETER_STRING, text);
		return condition;
	}

	private Grant getGrant(Condition ... conditions)
	{
		final Grant model = grantService.newGrant();
		model.setUserId(UUID.randomUUID().toString());
		model.setEntitlementType("test");
		model.setGrantSource("getGrant");
		model.setGrantSourceId("1");
		model.setConditions(Arrays.asList(conditions));
		return model;
	}
}
