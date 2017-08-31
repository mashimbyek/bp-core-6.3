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
package com.hybris.services.entitlements.condition.path;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

import com.hybris.services.entitlements.api.Actions;
import com.hybris.services.entitlements.condition.ActionHandler;
import com.hybris.services.entitlements.domain.Condition;
import com.hybris.services.entitlements.service.GrantService;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-service-test-spring.xml")
public class PathConditionCheckerTest
{
	private static final String CONDITION_TYPE = "path";
	@Autowired
	@Qualifier("pathConditionExecutor")
	private PathConditionStrategy checker;
	@Autowired
	private GrantService grantService;

	private ActionHandler checkHandler;

	@Before
	public void init()
	{
		checkHandler = checker.getActionHandler(null);
	}

	@Test
	public void shouldHaveCorrectName()
	{
		assertEquals(CONDITION_TYPE, checker.getConditionType());
	}

	@Test
	public void shouldReturnActionHandler()
	{
		assertNotNull(checkHandler);
	}

	@Test
	@Transactional
	public void shouldAcceptTheSameStrings()
	{
		final Map<String,String> checkParams = new HashMap<>();
		checkParams.put(PathConditionStrategy.EXECUTION_PARAMETER_FILE, "/tv/serials/breaking-bad");
		assertTrue(checkHandler.execute(getCondition("/tv/serials/breaking-bad", null, null), checkParams, null));
	}

	@Test
	@Transactional
	public void shouldAcceptSubdirectories()
	{
		final Map<String,String> checkParams = new HashMap<>();
		checkParams.put(PathConditionStrategy.EXECUTION_PARAMETER_FILE, "/tv/serials/lost/season01/episode03");
		assertTrue(checkHandler.execute(getCondition("/tv/serials/lost", null, null), checkParams, null));
	}

	@Test
	@Transactional
	public void shouldRejectDifferentBases()
	{
		final Map<String,String> checkParams = new HashMap<>();
		checkParams.put(PathConditionStrategy.EXECUTION_PARAMETER_FILE, "/tv/serials/walking-dead/season01/episode03");
		assertFalse(checkHandler.execute(getCondition("/tv/serials/sherlock", null, null), checkParams, null));
	}

	@Test
	@Transactional
	public void shouldRejectPartialItems()
	{
		final Map<String,String> checkParams = new HashMap<>();
		checkParams.put(PathConditionStrategy.EXECUTION_PARAMETER_FILE, "/tv/serials/friends-of-mine/season01/episode03");
		assertFalse(checkHandler.execute(getCondition("/tv/serials/friends", null, null), checkParams, null));
	}

	@Test
	@Transactional
	public void shouldSupportCustomDelimiters()
	{
		final Map<String,String> checkParams = new HashMap<>();
		checkParams.put(PathConditionStrategy.EXECUTION_PARAMETER_FILE, "tv-serials-it->crowd->hd");
		assertTrue(checkHandler.execute(getCondition("tv-serials-it->crowd", "->", null), checkParams, null));
	}

	@Test
	@Transactional
	public void shouldSupportCaseInsensitive()
	{
		final Map<String,String> checkParams = new HashMap<>();
		checkParams.put(PathConditionStrategy.EXECUTION_PARAMETER_FILE, "/tv/serials/Game-of-Thrones/posters");
		final Condition condition = getCondition("/tv/serials/game-of-thrones", null, null);
		assertFalse(checkHandler.execute(condition, checkParams, null));
		condition.setProperty(PathConditionStrategy.GRANT_PARAMETER_CASE_INSENSITIVE, "true");
		assertTrue(checkHandler.execute(condition, checkParams, null));
	}

	@Test
	@Transactional
	public void shouldSkipUsageAction()
	{
		assertNull(checker.getActionHandler(Actions.USE));
	}

	@Test
	public void shouldRejectEmptyCondition()
	{
		final Map<String, String> checkParams = new HashMap<>();
		checkParams.put(PathConditionStrategy.EXECUTION_PARAMETER_FILE, "tv/serials/lie-to-me");
		assertFalse(checkHandler.applicable(null, checkParams));
	}

	@Test
	@Transactional
	public void shouldRejectEmptyCriteria()
	{
		assertFalse(checkHandler.applicable(getCondition("tv/serials/two and a half men", null, null), null));
	}

	private Condition getCondition(final String directory, final String separator, final Boolean noCase)
	{
		final Condition data = grantService.newCondition();
		data.setType(CONDITION_TYPE);
		data.setProperty(PathConditionStrategy.GRANT_PARAMETER_PATH, directory);
		if (separator != null)
		{
			data.setProperty(PathConditionStrategy.GRANT_PARAMETER_SEPARATOR, separator);
		}
		if (noCase != null)
		{
			data.setProperty(PathConditionStrategy.GRANT_PARAMETER_CASE_INSENSITIVE, noCase.toString());
		}
		return data;
	}
}
