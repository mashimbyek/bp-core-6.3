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

import static org.junit.Assert.assertEquals;

import com.hybris.services.entitlements.domain.Condition;
import com.hybris.services.entitlements.domain.Grant;
import com.hybris.services.entitlements.service.GrantService;
import com.hybris.services.entitlements.sorting.DefaultGrantComparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-service-test-spring.xml")
public class DefaultGrantComparatorTest
{
	private static final String METERED_CONDITION_TYPE = "metered";

	@Autowired
	private DefaultGrantComparator defaultGrantComparator;

	@Autowired
	GrantService grantService;

	@Test
	@Transactional
	public void testDefaultGrantComparator()
	{
		final String userId = UUID.randomUUID().toString();
		final Grant grantNonMetered = grantService.newGrant();
		long currentTime = System.currentTimeMillis();
		grantNonMetered.setGrantTime(new Date(currentTime));
		grantNonMetered.setEntitlementType("storage");
		grantNonMetered.setUserId(userId);
		grantNonMetered.setGrantSource("test");
		grantNonMetered.setGrantSourceId("grantDataNonMetered");

		final Grant grantMetered1 = grantService.newGrant();
		grantMetered1.setEntitlementType("storage");
		grantMetered1.setUserId(userId);
		grantMetered1.setGrantSource("test");
		grantMetered1.setGrantSourceId("grantDataMetered1");
		grantMetered1.setGrantTime(new Date(currentTime + 1000));
		final Condition meteredCondition1 = grantService.newCondition();
		meteredCondition1.setType(METERED_CONDITION_TYPE);
		grantMetered1.setConditions(Arrays.asList(meteredCondition1));

		final Grant grantMetered2 = grantService.newGrant();
		grantMetered2.setEntitlementType("storage");
		grantMetered2.setUserId(userId);
		grantMetered2.setGrantSource("test");
		grantMetered2.setGrantSourceId("grantDataMetered2");
		grantMetered2.setGrantTime(new Date(currentTime + 5000));
		final Condition meteredCondition2 = grantService.newCondition();
		meteredCondition2.setType(METERED_CONDITION_TYPE);
		grantMetered2.setConditions(Arrays.asList(meteredCondition2));

		List<Grant> grants = Arrays.asList(grantMetered2, grantNonMetered, grantMetered1);
		Collections.sort(grants, defaultGrantComparator);
		assertEquals(grantNonMetered.getGrantSourceId(), grants.get(0).getGrantSourceId());
		assertEquals(grantMetered1.getGrantSourceId(), grants.get(1).getGrantSourceId());
		assertEquals(grantMetered2.getGrantSourceId(), grants.get(2).getGrantSourceId());
	}

	@Test
	@Transactional
	public void shouldHandleSimilarDates()
	{
		final String userId = UUID.randomUUID().toString();
		long currentTime = System.currentTimeMillis();

		final Grant grant1 = grantService.newGrant();
		grant1.setGrantTime(new Date(currentTime));
		grant1.setEntitlementType("storage");
		grant1.setUserId(userId);
		grant1.setGrantSource("shouldHandleSimilarDates");
		grant1.setGrantSourceId("1");
		final Grant grant2 = grantService.newGrant();
		grant2.setGrantTime(new Date(currentTime));
		grant2.setEntitlementType("storage");
		grant2.setUserId(userId);
		grant2.setGrantSource("shouldHandleSimilarDates");
		grant2.setGrantSourceId("2");

		assertEquals(0, defaultGrantComparator.compare(grant1, grant2));
		assertEquals(0, defaultGrantComparator.compare(null, null));
	}

	@Test
	@Transactional
	public void shouldHandleNullDates()
	{
		final String userId = UUID.randomUUID().toString();
		final Grant grant = grantService.newGrant();
		grant.setGrantTime(new Date());
		grant.setEntitlementType("storage");
		grant.setUserId(userId);
		grant.setGrantSource("shouldHandleSimilarDates");
		grant.setGrantSourceId("1");

		assertEquals(-1, defaultGrantComparator.compare(null, grant));
		assertEquals(1, defaultGrantComparator.compare(grant, null));
	}
}
