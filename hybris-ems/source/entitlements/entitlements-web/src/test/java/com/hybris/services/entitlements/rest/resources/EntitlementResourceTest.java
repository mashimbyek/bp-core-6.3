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
package com.hybris.services.entitlements.rest.resources;

import com.hybris.services.entitlements.api.exceptions.ValidationException;
import com.hybris.services.entitlements.api.Actions;
import com.hybris.services.entitlements.api.EntitlementFacade;
import com.hybris.services.entitlements.api.ListOfCriterionData;
import com.hybris.services.entitlements.api.PropertyData;
import com.hybris.services.entitlements.condition.CriterionData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit test for PlayerResource.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlements-web-test.xml")
public class EntitlementResourceTest
{
	@Autowired
	private EntitlementsResource entitlementsResource;

	private EntitlementFacade entitlementFacade;

	@Before
	public void setUp()
	{
		entitlementFacade = Mockito.mock(EntitlementFacade.class);
		entitlementsResource.setEntitlementFacade(entitlementFacade);
	}

	@Test
	public void testCreateEntitlement()
	{
		entitlementsResource.createEntitlement(null);
		Mockito.verify(entitlementFacade).createGrant(null);
	}

	@Test
	public void testRevoke()
	{
		final String userId = "userId";
		final String grantSource = "grantSource";
		final String grantType = "grantType";
		final String grantSourceId = "grantSourceId";

		entitlementsResource.revokeGrants(userId, grantSource, grantType, grantSourceId);
		Mockito.verify(entitlementFacade).revokeGrants(userId, grantSource, grantType, grantSourceId);
	}

	@Test
	public void testRevokeById()
	{
		final String grantId = UUID.randomUUID().toString();
		entitlementsResource.revoke(grantId);
		Mockito.verify(entitlementFacade).revokeGrant(grantId);
	}

	@Test
	public void shouldPassParametersToExecute()
	{
		final String userId = "userId";
		final String entitlementType = "type";
		final List<CriterionData> criteria = new ArrayList<>();
		ListOfCriterionData listOfCriterionData = new ListOfCriterionData(criteria);
		entitlementsResource.execute(Actions.CHECK, userId, entitlementType, false, listOfCriterionData);
		Mockito.verify(entitlementFacade).execute(Actions.CHECK, userId, entitlementType, criteria, false);
	}

	@Test
	public void shouldPassNullActionToExecute()
	{
		final String userId = "userId";
		final String entitlementType = "type";
		final List<CriterionData> criteria = new ArrayList<>();
		ListOfCriterionData listOfCriterionData = new ListOfCriterionData(criteria);
		entitlementsResource.execute(null, userId, entitlementType, false, listOfCriterionData);
		Mockito.verify(entitlementFacade).execute(null, userId, entitlementType, criteria, false);
	}

	@Test
	public void shouldIncrementGrantPropertyByGivenNumber()
	{
		final String userId = "userId";
		final String key = "name";
		final int value = 11;
		final PropertyData data = new PropertyData();
		data.setRelative(true);
		data.setValue(Integer.toString(value));
		entitlementsResource.putGrantProperty(userId, key, data);
		Mockito.verify(entitlementFacade).addGrantProperty(userId, key, value);
	}

	@Test(expected = ValidationException.class)
	public void shouldRejectIncrementPropertyByANonNumericValue()
	{
		final String userId = "userId";
		final String key = "name";
		final String s = "bla-bla-bla";

		final PropertyData incorrectData = new PropertyData();
        incorrectData.setRelative(true);
        incorrectData.setValue(s);

		entitlementsResource.putGrantProperty(userId, key, incorrectData);
	}
 }
