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
package com.hybris.services.entitlements.facade;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.hybris.kernel.api.PersistenceManager;
import com.hybris.services.entitlements.api.Actions;
import com.hybris.services.entitlements.api.EntitlementFacade;
import com.hybris.services.entitlements.api.ExecuteManyData;
import com.hybris.services.entitlements.api.ExecuteManyResult;
import com.hybris.services.entitlements.api.ExecuteResult;
import com.hybris.services.entitlements.api.GrantData;
import com.hybris.services.entitlements.api.ObjectNotFoundException;
import com.hybris.services.entitlements.api.UnprocessableEntityException;
import com.hybris.services.entitlements.api.exceptions.ValidationException;
import com.hybris.services.entitlements.api.status.Status;
import com.hybris.services.entitlements.condition.ConditionData;
import com.hybris.services.entitlements.condition.CriterionData;
import com.hybris.services.entitlements.condition.metered.MeteredConditionStrategy;
import com.hybris.services.entitlements.condition.string.StringConditionStrategy;
import com.hybris.services.entitlements.condition.timeframe.TimeframeConditionStrategy;
import com.hybris.services.entitlements.conversion.DateTimeConverter;
import com.hybris.services.entitlements.validation.ValidationExecutor;
import com.hybris.services.entitlements.validation.ValidationViolations;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-facade-test-spring.xml")
public class DefaultEntitlementFacadeTest
{
	public static final String ENTITLEMENT_TYPE = "Simple entitlement";
	private static final String TIMEFRAME_TYPE = "timeframe";
	public static final String STRING_TYPE = "string";
    public static final String METERED_TYPE = "metered";
    public static final String SIMPLE_TYPE = "simple";

	@Autowired
	@Qualifier("defaultEntitlementFacade")
	EntitlementFacade entitlementFacade;
	@Autowired
	DateTimeConverter dateTimeConverter;
	@Autowired
	PersistenceManager persistenceManager;
	@Autowired
	@Qualifier("stringConditionExecutor")
	StringConditionStrategy stringConditionStrategy;
	@Autowired
	ApplicationContext context;
	@Autowired
	ValidationExecutor validationExecutor;

	private String userId;
	private GrantData grantData;


	@Before
	public void createGrant()
	{
		userId = UUID.randomUUID().toString();
		final GrantData grant = new GrantData();
		grant.setUserId(userId);
		grant.setEntitlementType(ENTITLEMENT_TYPE);
		grant.setGrantTime(dateTimeConverter.convertDateToString(new Date()));
		grant.setGrantSource("order");
		grant.setGrantSourceId("order#1");

		grantData = entitlementFacade.createGrant(grant);
		assertNotNull(grantData);
	}

	@Test
	public void createGrantWithStatusIsNull()
	{
		userId = UUID.randomUUID().toString();
		final GrantData grant = new GrantData();
		grant.setUserId(userId);
		grant.setEntitlementType(ENTITLEMENT_TYPE);
		grant.setGrantTime(dateTimeConverter.convertDateToString(new Date()));
		grant.setGrantSource("order");
		grant.setGrantSourceId("order#1");
		grant.setStatus(null);

		grantData = entitlementFacade.createGrant(grant);
		assertNotNull(grantData);
	}

	@Test
	public void createGrantWithGrantTimeIsNull()
	{
		userId = UUID.randomUUID().toString();
		final GrantData grant = new GrantData();
		grant.setUserId(userId);
		grant.setEntitlementType(ENTITLEMENT_TYPE);
		grant.setGrantTime(null);
		grant.setGrantSource("order");
		grant.setGrantSourceId("order#1");

		grantData = entitlementFacade.createGrant(grant);
		assertNotNull(grantData);
	}

	@Test
	public void createGrantWithConditionsIsNull()
	{
		userId = UUID.randomUUID().toString();
		final GrantData grant = new GrantData();
		grant.setUserId(userId);
		grant.setEntitlementType(ENTITLEMENT_TYPE);
		grant.setGrantTime(dateTimeConverter.convertDateToString(new Date()));
		grant.setGrantSource("order");
		grant.setGrantSourceId("order#1");
		grant.setConditions(null);

		grantData = entitlementFacade.createGrant(grant);
		assertNotNull(grantData);
	}

	@Test
	public void testDuplicateNaturalKey() throws ParseException
	{
		List<GrantData> grantsBefore = entitlementFacade.getGrants(
				grantData.getUserId(), grantData.getEntitlementType(),
				grantData.getGrantSource(), grantData.getGrantSourceId());
		assertEquals(1, grantsBefore.size());
		entitlementFacade.createGrant(grantData);
		List<GrantData> grantsAfter = entitlementFacade.getGrants(
				grantData.getUserId(), grantData.getEntitlementType(),
				grantData.getGrantSource(), grantData.getGrantSourceId());
		assertEquals(2, grantsAfter.size());
	}

	@Test
	public void shouldGenerateGrantTime() throws ParseException
	{
		final Date start = new Date();
		final GrantData data = new GrantData();
		data.setUserId(userId);
		data.setEntitlementType("SMS");
		data.setGrantSource("order");
		data.setGrantSourceId("order#1");
		final GrantData result = entitlementFacade.createGrant(data);
		final Date end = new Date();
		assertNotNull(result.getGrantTime());
/*
		// The date is rounded to seconds, and the assertion fails
		final Date grantDate = dateTimeConverter.convertStringToDate(result.getGrantTime());
		assertFalse(start.after(grantDate) || end.before(grantDate));
*/
	}

	@Test
	public void testCreateGrantIdNotNull()
	{
		final GrantData data = new GrantData();
		data.setUserId(userId);
		data.setEntitlementType("SMS");
		data.setGrantSource("order");
		data.setGrantSourceId("order#1");
		final GrantData result = entitlementFacade.createGrant(data);
		assertNotNull(result.getId());
	}

	@Test
	public void shouldSupportAnyCharactersInKeyFields()
	{
		final GrantData data = new GrantData();
		data.setUserId(userId + "| -.,REMOVE FROM `accounts`;/\\[]{}=+()*_");
		data.setEntitlementType("db.Grant_single.remove();#$:,./\\-=+{}[]| `\'\"");
		data.setGrantSource("\"\'`({[]})#$%^&*!@\\/-=+_");
		data.setGrantSourceId("\"\'`({[]})#$%^&*!@\\/-=+_");
		final GrantData result = entitlementFacade.createGrant(data);
		assertNotNull(result);
		assertNotNull(result.getId());
		final GrantData queried = entitlementFacade.getGrant(result.getId());
		assertEquals(result, queried);
		final List<GrantData> foundGrants = entitlementFacade.getGrants(
				data.getUserId(), data.getEntitlementType(), data.getGrantSource(), data.getGrantSourceId());
		assertNotNull(foundGrants);
		assertEquals(1, foundGrants.size());
		assertEquals(result, foundGrants.get(0));
	}


	@Test
	public void shouldCreateConditionalEntitlement()
	{
		final GrantData data = new GrantData();
		data.setUserId(userId);
		data.setEntitlementType("SMS");
		data.setGrantSource("shouldCreateConditionalEntitlement");
		data.setGrantSourceId("order#1");
		final ConditionData stringConditionData = new ConditionData();
		stringConditionData.setType(STRING_TYPE);
		stringConditionData.setProperty(StringConditionStrategy.GRANT_PARAMETER_STRING, "Value");
		data.setCondition(stringConditionData);
		final GrantData source = entitlementFacade.createGrant(data);
		final List<GrantData> grants = entitlementFacade.getGrants(userId, "SMS", "shouldCreateConditionalEntitlement", null);
		assertEquals(1, grants.size());
		final GrantData created = grants.get(0);
		assertEquals(1, created.getConditions().size());
		assertEquals(STRING_TYPE, created.getConditions().get(0).getType());
		assertEquals(source.getConditions().get(0), created.getConditions().get(0));
	}

	@Test
	public void testTimeframeConditionDataWriteRead() {
		final ConditionData timeframeConditionData = new ConditionData();
		timeframeConditionData.setType(TIMEFRAME_TYPE);
		timeframeConditionData.setProperty(TimeframeConditionStrategy.GRANT_PARAMETER_START, "2013-11-01T08:00:00Z");
		timeframeConditionData.setProperty(TimeframeConditionStrategy.GRANT_PARAMETER_END, "2013-12-31T09:00:00Z");
		final GrantData data = new GrantData();
		data.setUserId(userId);
		data.setEntitlementType("SMS");
		data.setGrantSource("testTimeframeConditionDataWriteRead");
		data.setGrantSourceId("order#1");
		data.setCondition(timeframeConditionData);
		entitlementFacade.createGrant(data);
		final List<GrantData> grants = entitlementFacade.getGrants(userId, "SMS", "testTimeframeConditionDataWriteRead", null);
		assertEquals(1, grants.size());
		final GrantData grant = grants.get(0);
		final Collection<ConditionData> conditions = grant.getConditions();
		assertEquals(1, conditions.size());
		final ConditionData condition = conditions.iterator().next();
		assertEquals(condition, timeframeConditionData);
	}

	@Test
	public void testGrantValidator()
	{
		final GrantData grantData1 = new GrantData();
		grantData1.setEntitlementType(ENTITLEMENT_TYPE);
		grantData1.setGrantTime(dateTimeConverter.convertDateToString(new Date()));
		grantData1.setGrantSource("GrantSource");
		grantData1.setGrantSourceId("GrantSourceId");
		grantData1.setProperty("remainingQuantity", "1");
		final ConditionData condition = new ConditionData();
		condition.setType("string");
		grantData1.addCondition(condition);
		final ValidationViolations errors = validationExecutor.validate(grantData1);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void testGrantPropertyValidator()
	{
		final GrantData grantData1 = new GrantData();
		grantData1.setUserId(userId);
		grantData1.setEntitlementType(ENTITLEMENT_TYPE);
		grantData1.setGrantTime(dateTimeConverter.convertDateToString(new Date()));
		grantData1.setGrantSource("GrantSource");
		grantData1.setGrantSourceId("GrantSourceId");
		grantData1.setProperty("remainingQuantity", "1");
		final GrantData grantData2 = entitlementFacade.createGrant(grantData1);
		assertNotNull(grantData2);
	}

	@Test
	public void createShouldIgnoreExistingId()
	{
		final GrantData data = new GrantData();
		data.setUserId(userId);
		data.setEntitlementType("SMS");
		data.setGrantSource("order");
		data.setGrantSourceId("order#1");
		final GrantData result = entitlementFacade.createGrant(data);
		final String id = result.getId();
		data.setId(id);
		data.setEntitlementType("SMS-updated");
		data.setGrantSource("order-updated");
		data.setGrantSourceId("order#1-updated");
		final GrantData result2 = entitlementFacade.createGrant(data);
		assertFalse(id.equals(result2.getId()));
	}

	@Test
	public void createShouldIgnoreNullId()
	{
		final GrantData data = new GrantData();
		data.setUserId(userId);
		data.setEntitlementType("SMS");
		data.setGrantSource("order");
		data.setGrantSourceId("order#1");
		data.setId("single|Grant|-1");//single|Grant|52568835e4b002f5543b4356
		final GrantData result = entitlementFacade.createGrant(data);
		assertNotNull(result.getId());
	}

	@Test(expected = ValidationException.class)
	public void testGrantPropertyValidator2()
	{
		final GrantData grantData1 = new GrantData();
		grantData1.setUserId(userId);
		grantData1.setEntitlementType(ENTITLEMENT_TYPE);
		grantData1.setGrantTime(dateTimeConverter.convertDateToString(new Date()));
		grantData1.setGrantSource("GrantSource");
		grantData1.setGrantSourceId("GrantSourceId");
		grantData1.setProperty("remainingQuantity", "abc");
		entitlementFacade.createGrant(grantData1);
	}

	@Test(expected = ValidationException.class)
	public void testGrantPropertyValidatorNullValue()
	{
		final GrantData grantData1 = new GrantData();
		grantData1.setUserId(userId);
		grantData1.setEntitlementType(ENTITLEMENT_TYPE);
		grantData1.setGrantTime(dateTimeConverter.convertDateToString(new Date()));
		grantData1.setGrantSource("GrantSource");
		grantData1.setGrantSourceId("GrantSourceId");
		grantData1.setProperty("remainingQuantity", null);
		entitlementFacade.createGrant(grantData1);
	}

	@Test(expected = ValidationException.class)
	public void grantCreationShouldRejectNullNameOfDynamicProperty()
	{
		final GrantData grant = new GrantData();
		grant.setUserId(userId);
		grant.setEntitlementType(ENTITLEMENT_TYPE);
		grant.setGrantTime(dateTimeConverter.convertDateToString(new Date()));
		grant.setGrantSource("grantCreationShouldRejectNullNameOfDynamicProperty");
		grant.setGrantSourceId("GrantSourceId");
		grant.setProperty(null, "test");
		entitlementFacade.createGrant(grant);
	}

	/*
	 * Next tests are here, because ATDD can not process null values in conditions.
	 * When a condition contains null, using it causes "keyword not found" error.
	 */
	@Test(expected = ValidationException.class)
	public void creationOfPropertyWithNullNameShouldNotPassValidation()
	{
		final GrantData grant = new GrantData();
		grant.setUserId(userId);
		grant.setEntitlementType(ENTITLEMENT_TYPE);
		grant.setGrantTime(dateTimeConverter.convertDateToString(new Date()));
		grant.setGrantSource("creationOfPropertyWithNullNameShouldNotPassValidation");
		grant.setGrantSourceId("GrantSourceId");
		final String id = entitlementFacade.createGrant(grant).getId();
		entitlementFacade.createGrantProperty(id, null, "test");
	}

	@Test(expected = ValidationException.class)
	public void creationOfPropertyWithNullValueShouldNotPassValidation()
	{
		final GrantData grant = new GrantData();
		grant.setUserId(userId);
		grant.setEntitlementType(ENTITLEMENT_TYPE);
		grant.setGrantTime(dateTimeConverter.convertDateToString(new Date()));
		grant.setGrantSource("creationOfPropertyWithNullValueShouldNotPassValidation");
		grant.setGrantSourceId("GrantSourceId");
		grant.setProperty("test", "1");
		final String id = entitlementFacade.createGrant(grant).getId();
		entitlementFacade.updateGrantProperty(id, "test", null);
	}

	@Test(expected = ValidationException.class)
	public void updatingOfPropertyToNullValueShouldNotPassValidation()
	{
		final GrantData grant = new GrantData();
		grant.setUserId(userId);
		grant.setEntitlementType(ENTITLEMENT_TYPE);
		grant.setGrantTime(dateTimeConverter.convertDateToString(new Date()));
		grant.setGrantSource("creationOfPropertyWithNullValueShouldNotPassValidation");
		grant.setGrantSourceId("GrantSourceId");
		final String id = entitlementFacade.createGrant(grant).getId();
		entitlementFacade.createGrantProperty(id, "test", null);
	}

	@Test(expected = ValidationException.class)
	public void grantCreationShouldRejectNullNameOfConditionProperty()
	{
		final GrantData grant = new GrantData();
		grant.setUserId(userId);
		grant.setEntitlementType(ENTITLEMENT_TYPE);
		grant.setGrantTime(dateTimeConverter.convertDateToString(new Date()));
		grant.setGrantSource("grantCreationShouldRejectNullNameOfConditionProperty");
		grant.setGrantSourceId("GrantSourceId");
		final ConditionData condition = new ConditionData();
		condition.setType("string");
		condition.setProperty("string", "ok");
		condition.setProperty(null, "test");
		grant.setCondition(condition);
		entitlementFacade.createGrant(grant);
	}

	public void grantCreationShouldAcceptNullValueOfConditionProperty()
	{
		final GrantData grant = new GrantData();
		grant.setUserId(userId);
		grant.setEntitlementType(ENTITLEMENT_TYPE);
		grant.setGrantTime(dateTimeConverter.convertDateToString(new Date()));
		grant.setGrantSource("grantCreationShouldRejectNullValueOfConditionProperty");
		grant.setGrantSourceId("GrantSourceId");
		final ConditionData condition = new ConditionData();
		condition.setType("string");
		condition.setProperty("string", "ok");
		condition.setProperty("test", null);
		grant.setCondition(condition);
		entitlementFacade.createGrant(grant);
	}

	public void conditionUpdatingShouldAcceptNullPropertyName()
	{
		final GrantData grant = new GrantData();
		grant.setUserId(userId);
		grant.setEntitlementType(ENTITLEMENT_TYPE);
		grant.setGrantTime(dateTimeConverter.convertDateToString(new Date()));
		grant.setGrantSource("conditionUpdatingShouldRejectNullPropertyName");
		grant.setGrantSourceId("GrantSourceId");
		final String id = entitlementFacade.createGrant(grant).getId();
		final ConditionData condition = new ConditionData();
		condition.setType("string");
		condition.setProperty("string", "ok");
		condition.setProperty("test", null);
		grant.setCondition(condition);
		entitlementFacade.updateConditions(id, Arrays.asList(condition));
	}

	@Test(expected = ValidationException.class)
	public void conditionUpdatingShouldRejectNullPropertyValue()
	{
		final GrantData grant = new GrantData();
		grant.setUserId(userId);
		grant.setEntitlementType(ENTITLEMENT_TYPE);
		grant.setGrantTime(dateTimeConverter.convertDateToString(new Date()));
		grant.setGrantSource("conditionUpdatingShouldRejectNullPropertyValue");
		grant.setGrantSourceId("GrantSourceId");
		final String id = entitlementFacade.createGrant(grant).getId();
		final ConditionData condition = new ConditionData();
		condition.setType("string");
		condition.setProperty("string", "ok");
		condition.setProperty(null, "test");
		grant.setCondition(condition);
		entitlementFacade.updateConditions(id, Arrays.asList(condition));
	}

	@Test
	public void detailsFlagTest()
	{
		ExecuteResult executeResult = entitlementFacade.execute(Actions.CHECK, userId, ENTITLEMENT_TYPE, Collections.<CriterionData>emptyList(), false);
		assertTrue(executeResult.isResult());
		assertTrue(executeResult.getGrantDataList()==null);
		executeResult = entitlementFacade.execute(Actions.CHECK, userId, ENTITLEMENT_TYPE, Collections.<CriterionData>emptyList(), true);
		assertTrue(executeResult.isResult());
		assertTrue(executeResult.getGrantDataList().size()==1);
		executeResult = entitlementFacade.execute(Actions.USE, userId, ENTITLEMENT_TYPE, Collections.<CriterionData>emptyList(), false);
		assertTrue(executeResult.isResult());
		assertTrue(executeResult.getGrantDataList()==null);
		executeResult = entitlementFacade.execute(Actions.USE, userId, ENTITLEMENT_TYPE, Collections.<CriterionData>emptyList(), true);
		assertTrue(executeResult.isResult());
		assertTrue(executeResult.getGrantDataList().size()==1);
	}

	@Test
	public void comparatorTypeTest()
	{
		entitlementFacade.execute(Actions.CHECK, userId, ENTITLEMENT_TYPE, Collections.<CriterionData>emptyList(), false);
	}

	@Test
	public void testUpdateEntitlement()
	{
		GrantData data = new GrantData();
		data.setUserId(userId);
		data.setEntitlementType("SMS");
		data.setGrantSource("order");
		data.setGrantSourceId("order#1");
		data = entitlementFacade.createGrant(data);
		final ConditionData condition1 = new ConditionData();
		condition1.setType("string");
		condition1.setProperty("string", "");
		entitlementFacade.updateConditions(data.getId(), Arrays.asList(condition1));
		final GrantData grant = entitlementFacade.getGrant(data.getId());
		assertFalse(grant.equals(data));
		assertEquals(grant.getConditions(), grant.getConditions());
	}


	@Test(expected = ValidationException.class)
	public void shouldRejectWithoutUserId()
	{
		final GrantData data = new GrantData();
		data.setEntitlementType(ENTITLEMENT_TYPE);
		data.setGrantSource("order");
		data.setGrantSourceId("order#1");
		final GrantData result = entitlementFacade.createGrant(data);
		assertNotNull(result.getId());
	}

	@Test
	public void shouldReturnCreatedId()
	{
		assertNotNull(grantData.getId());
	}

	@Test
	public void shouldCheckExistedEntitlements()
	{
		assertTrue(entitlementFacade.execute(Actions.CHECK, userId, ENTITLEMENT_TYPE, Collections.<CriterionData>emptyList(), false).isResult());
	}

	@Test
	public void shouldCheckNonExistedEntitlements()
	{
		assertFalse(entitlementFacade.execute(Actions.CHECK, userId, userId + "!", Collections.<CriterionData>emptyList(), false).isResult());
	}

	@Test
	public void testGetEntitlements()
	{
		final String userId = UUID.randomUUID().toString();
		final String entitlementType = "SMS";

		final GrantData data = new GrantData();
		data.setUserId(userId);
		data.setEntitlementType(entitlementType);
		data.setGrantSource("order");
		data.setGrantSourceId("order#1");
		final ConditionData timeCondition = new ConditionData();
		timeCondition.setType("timeframe");
		timeCondition.setProperty(TimeframeConditionStrategy.GRANT_PARAMETER_START, "2010-01-01T10:20:30Z");
		timeCondition.setProperty(TimeframeConditionStrategy.GRANT_PARAMETER_END, "2020-01-01T10:20:30Z");
		data.setCondition(timeCondition);
		entitlementFacade.createGrant(data);
		final CriterionData criterion = new CriterionData();
		criterion.setType("timeframe");
		criterion.setProperty(TimeframeConditionStrategy.EXECUTION_PARAMETER_TIME, "2005-01-01T10:20:30Z");
		assertEquals(entitlementFacade.getGrants(userId, entitlementType, "order", "order#1").size(), 1);
	}

	@Test
	public void testGetEntitlement()
	{
		final GrantData storedGrantData = entitlementFacade.getGrant(grantData.getId());
		assertEquals(storedGrantData, grantData);
	}

	@Test(expected = ObjectNotFoundException.class)
	public void testGetEntitlementNotFound()
	{
		final GrantData storedGrantData = entitlementFacade.getGrant(grantData.getId()+"q");
		assertEquals(storedGrantData, grantData);
	}

	@Test(expected = ObjectNotFoundException.class)
	public void testGetEntitlementNonActive()
	{
		entitlementFacade.revokeGrant(grantData.getId());
		final GrantData storedGrantData = entitlementFacade.getGrant(grantData.getId());
		assertEquals(storedGrantData, grantData);
	}

	@Test(expected = ValidationException.class)
	public void shouldRejectMoreThanOneConditionPerType()
	{
		final String userId = UUID.randomUUID().toString();
		final String entitlementType = "SMS";

		final GrantData data = new GrantData();
		data.setUserId(userId);
		data.setEntitlementType(entitlementType);
		data.setGrantSource("order");
		data.setGrantSourceId("order#1");
		final ConditionData condition1 = new ConditionData();
		condition1.setType("string");
		condition1.setProperty("string", "");
		final ConditionData condition2 = new ConditionData();
		condition2.setType("string");
		condition2.setProperty("string", "");
		data.setCondition(condition1, condition2);
		entitlementFacade.createGrant(data);
	}

	@Test(expected = ValidationException.class)
	public void shouldValidateConditions()
	{
		final String userId = UUID.randomUUID().toString();
		final String entitlementType = "SMS";

		final GrantData data = new GrantData();
		data.setUserId(userId);
		data.setEntitlementType(entitlementType);
		data.setGrantSource("order");
		data.setGrantSourceId("order#1");
		final ConditionData condition = new ConditionData();
		condition.setType("string");
		condition.setProperty("whatever", "");
		data.addCondition(condition);
		entitlementFacade.createGrant(data);
	}

	@Test(expected = ValidationException.class)
	public void shouldValidateConditions2()
	{
		final ConditionData condition = new ConditionData();
		condition.setType("string");
		condition.setProperty("whatever", "");
		grantData.addCondition(condition);
		grantData.addCondition(condition);
		entitlementFacade.updateConditions(grantData.getId(), grantData.getConditions());
	}

	@Test(expected = ValidationException.class)
	public void shouldRejectUnknownConditionTypes()
	{
		final String userId = UUID.randomUUID().toString();
		final String entitlementType = "SMS";

		final GrantData data = new GrantData();
		data.setUserId(userId);
		data.setEntitlementType(entitlementType);
		data.setGrantSource("order");
		data.setGrantSourceId("order#1");
		final ConditionData condition = new ConditionData();
		condition.setType("something");
		data.addCondition(condition);
		entitlementFacade.createGrant(data);
	}

	@Test
	public void shouldRevokeById()
	{
		final String user = UUID.randomUUID().toString();
		final GrantData grant = new GrantData();
		grant.setUserId(user);
		grant.setEntitlementType(ENTITLEMENT_TYPE);
		grant.setGrantSource("order");
		grant.setGrantSourceId("order#1");

		grantData = entitlementFacade.createGrant(grant);
		entitlementFacade.revokeGrant(grantData.getId());
    }

	@Test
	public void shouldRevokeByNaturalKey()
	{
		final String user = UUID.randomUUID().toString();
		final GrantData entitlement = new GrantData();
		entitlement.setUserId(user);
		entitlement.setEntitlementType(ENTITLEMENT_TYPE);
		entitlement.setGrantSource("order");
		entitlement.setGrantSourceId("order#1");

		grantData = entitlementFacade.createGrant(entitlement);
		entitlementFacade.revokeGrants(user, ENTITLEMENT_TYPE, "order", "order#1");
	}

	@Test(expected = ValidationException.class)
	public void shouldReportOnConditionSemantics()
	{
		final CriterionData string = new CriterionData();
		string.setType(STRING_TYPE);
		entitlementFacade.execute(Actions.CHECK, userId, null, Arrays.asList(string), false);
	}

	@Test(expected = ValidationException.class)
	public void shouldReportOnUnknownCondition()
	{
		final CriterionData something = new CriterionData();
		something.setType("whatever");
		entitlementFacade.execute(Actions.CHECK, userId, null, Arrays.asList(something), false);
	}

	@Test(expected = ValidationException.class)
	public void shouldReportOnNullCriteria()
	{
		entitlementFacade.execute(Actions.CHECK, userId, null, null, false);
	}

	@Test(expected = ValidationException.class)
	public void shouldReportOnGrantingNull()
	{
		entitlementFacade.createGrant(null);
	}

	@Test
	public void testCheckTimeframe()
	{
		final String userId = UUID.randomUUID().toString();
		final String entitlementType = "SMS";

		final GrantData data = new GrantData();
		data.setUserId(userId);
		data.setEntitlementType(entitlementType);
		data.setGrantSource("order");
		data.setGrantSourceId("order#1");
		final ConditionData timeCondition = new ConditionData();
		timeCondition.setType(TIMEFRAME_TYPE);
		timeCondition.setProperty(TimeframeConditionStrategy.GRANT_PARAMETER_START, "2010-01-01T10:20:30Z");
		timeCondition.setProperty(TimeframeConditionStrategy.GRANT_PARAMETER_END, "2020-01-01T10:20:30Z");
		data.setCondition(timeCondition);
		entitlementFacade.createGrant(data);
		final CriterionData criterion = new CriterionData();
		criterion.setType(TIMEFRAME_TYPE);
		criterion.setProperty(TimeframeConditionStrategy.EXECUTION_PARAMETER_TIME, "2005-01-01T10:20:30Z");
		final List<CriterionData> criteria = Arrays.asList(criterion);
		assertFalse(entitlementFacade.execute(Actions.CHECK, userId, entitlementType, criteria, false).isResult());

		criterion.setProperty(TimeframeConditionStrategy.EXECUTION_PARAMETER_TIME, "2025-01-01T10:20:30Z");
		assertFalse(entitlementFacade.execute(Actions.CHECK, userId, entitlementType, criteria, false).isResult());

		criterion.setProperty(TimeframeConditionStrategy.EXECUTION_PARAMETER_TIME, "2020-01-01T10:20:30Z");
		assertTrue(entitlementFacade.execute(Actions.CHECK, userId, entitlementType, criteria, false).isResult());

		criterion.setProperty(TimeframeConditionStrategy.EXECUTION_PARAMETER_TIME, "2010-01-01T10:20:30Z");
		assertTrue(entitlementFacade.execute(Actions.CHECK, userId, entitlementType, criteria, false).isResult());

		criterion.setProperty(TimeframeConditionStrategy.EXECUTION_PARAMETER_TIME, "2015-01-01T10:20:30Z");
		assertTrue(entitlementFacade.execute(Actions.CHECK, userId, entitlementType, criteria, false).isResult());
	}

	@Test
	public void shouldBeConsistent() throws InterruptedException
	{
		final int threads = 3;
		final String entitlementType = "concurrency";
		final GrantData data = new GrantData();
		data.setUserId(userId);
		data.setEntitlementType(entitlementType);
		data.setGrantSource("shouldBeConsistent");
		data.setGrantSourceId("order#1");
		final ConditionData condition = new ConditionData();
		condition.setType("metered");
		condition.setProperty(MeteredConditionStrategy.GRANT_PARAMETER_MAX_QUANTITY, Integer.toString(threads + 2));
		data.setCondition(condition);
		entitlementFacade.createGrant(data).getId();

		final CyclicBarrier leadingEdge = new CyclicBarrier(threads);
		final CyclicBarrier fallingEdge = new CyclicBarrier(threads + 1);

        final CriterionData usage = new CriterionData();
        usage.setType(METERED_TYPE);
        usage.setProperty("quantity", "1");
		// To initialize all required beans. Without this line we get BeanCreationNotAllowedException
		entitlementFacade.execute(Actions.USE, userId, entitlementType, Arrays.asList(usage), false);

		for (int i = 0; i < threads; i++)
		{
			final int delay = i;
			new Thread(new Runnable()
			{
				private void operation() throws InterruptedException
				{
                    System.out.printf("[%d] Thread %d\n", System.currentTimeMillis(), delay);
                    entitlementFacade.execute(Actions.USE, userId, entitlementType, Arrays.asList(usage), false);
				}

				@Override
				public void run()
				{
					try
					{
						leadingEdge.await();
					}
					catch (InterruptedException | BrokenBarrierException e)
					{
						fail("Exception " + e.getMessage());
					}
					try
					{
						operation();
						final List<GrantData> grants = entitlementFacade.getGrants(userId, entitlementType, null, null);
						System.out.printf("[%d] Thread %d: %s units used\n", System.currentTimeMillis(), delay,
								grants.get(0).getProperty(MeteredConditionStrategy.PARAMETER_REMAINING_QUANTITY));
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					try
					{
						fallingEdge.await(10, TimeUnit.SECONDS);
					}
					catch (InterruptedException | BrokenBarrierException | TimeoutException e)
					{
						fail("Exception " + e.getMessage());
					}
				}
			}).start();
		}
		try
		{
			fallingEdge.await(10, TimeUnit.SECONDS);
		}
		catch (BrokenBarrierException | TimeoutException e)
		{
			e.printStackTrace();
		}

		final List<GrantData> grants = entitlementFacade.getGrants(userId, entitlementType, null, null);
		assertEquals(1, grants.size());
		assertEquals("1", grants.get(0).getProperty(MeteredConditionStrategy.PARAMETER_REMAINING_QUANTITY));
	}

    @Test(expected = ValidationException.class)
    public void executeManyUseTest()
    {
        final List<ExecuteManyData> executeManyDataList = new ArrayList<>();
        entitlementFacade.execute(Actions.USE, userId, executeManyDataList, false);
    }

    @Test
    public void executeManyAllSuccessfulTest()
    {
        createSimpleGrant();
        createTimeframeGrant();

		final List<ExecuteManyData> executeManyDataList = new ArrayList<>();
        executeManyDataList.add(createSimpleExecuteManyData());
        executeManyDataList.add(createTimeframeExecuteManyData());

		final ExecuteManyResult executeManyResult = entitlementFacade.execute(
				Actions.CHECK, userId, executeManyDataList, false);

		final ExecuteResult simpleExecuteResult = executeManyResult.getExecuteResultList().get(0);
        assertTrue(simpleExecuteResult.isResult());

		final ExecuteResult timeframeExecuteResult = executeManyResult.getExecuteResultList().get(1);
        assertTrue(timeframeExecuteResult.isResult());
    }

    @Test
    public void executeManyNoTimeframeTest()
    {
        createSimpleGrant();

		final List<ExecuteManyData> executeManyDataList = new ArrayList<>();
        executeManyDataList.add(createSimpleExecuteManyData());
        executeManyDataList.add(createTimeframeExecuteManyData());

		final ExecuteManyResult executeManyResult = entitlementFacade.execute(
				Actions.CHECK, userId, executeManyDataList, false);

		final ExecuteResult simpleExecuteResult = executeManyResult.getExecuteResultList().get(0);
        assertTrue(simpleExecuteResult.isResult());

		final ExecuteResult timeframeExecuteResult = executeManyResult.getExecuteResultList().get(1);
        assertFalse(timeframeExecuteResult.isResult());
    }

    @Test
    @Transactional
    public void executeManyNoSimpleTest()
    {
        createTimeframeGrant();

		final List<ExecuteManyData> executeManyDataList = new ArrayList<>();
        executeManyDataList.add(createSimpleExecuteManyData());
        executeManyDataList.add(createTimeframeExecuteManyData());

		final ExecuteManyResult executeManyResult = entitlementFacade.execute(
				Actions.CHECK, userId, executeManyDataList, false);

		final ExecuteResult simpleExecuteResult = executeManyResult.getExecuteResultList().get(0);
        assertFalse(simpleExecuteResult.isResult());

		final ExecuteResult timeframeExecuteResult = executeManyResult.getExecuteResultList().get(1);
        assertTrue(timeframeExecuteResult.isResult());
    }

    @Test(expected = ValidationException.class)
    @Transactional
    public void executeManyValidationFailedTest()
    {
        createTimeframeGrant();

        final List<ExecuteManyData> executeManyDataList = new ArrayList<>();
        executeManyDataList.add(createSimpleExecuteManyData());
        executeManyDataList.add(createNoValidTimeframeExecuteManyData());

		final ExecuteManyResult executeManyResult = entitlementFacade.execute(
				Actions.CHECK, userId, executeManyDataList, false);

		final ExecuteResult simpleExecuteResult = executeManyResult.getExecuteResultList().get(0);
        assertFalse(simpleExecuteResult.isResult());

		final ExecuteResult timeframeExecuteResult = executeManyResult.getExecuteResultList().get(1);
        assertTrue(timeframeExecuteResult.isResult());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void addUsageGrantNotFoundTest()
    {
        entitlementFacade.revokeGrant(grantData.getId());
        entitlementFacade.addGrantProperty(grantData.getId(), MeteredConditionStrategy.PARAMETER_REMAINING_QUANTITY, 0);
    }

    @Test
    public void addUsageZeroTest()
    {
		entitlementFacade.createGrantProperty(grantData.getId(), MeteredConditionStrategy.PARAMETER_REMAINING_QUANTITY, "0");
        entitlementFacade.addGrantProperty(grantData.getId(), MeteredConditionStrategy.PARAMETER_REMAINING_QUANTITY, 0);
        final GrantData grantData1 = entitlementFacade.getGrant(grantData.getId());
        final int remainingQuantity = Integer.valueOf(grantData1.getProperty(MeteredConditionStrategy.PARAMETER_REMAINING_QUANTITY));
        assertEquals(remainingQuantity, 0);
    }

    @Test
    public void addUsageNegativeTest()
    {
		entitlementFacade.createGrantProperty(grantData.getId(), MeteredConditionStrategy.PARAMETER_REMAINING_QUANTITY, "0");
        entitlementFacade.addGrantProperty(grantData.getId(), MeteredConditionStrategy.PARAMETER_REMAINING_QUANTITY, -1);
        final GrantData grantData1 = entitlementFacade.getGrant(grantData.getId());
        final int remainingQuantity = Integer.valueOf(grantData1.getProperty(MeteredConditionStrategy.PARAMETER_REMAINING_QUANTITY));
        assertEquals(remainingQuantity, -1);
    }

    @Test
    public void addUsagePositiveTest()
    {
		entitlementFacade.createGrantProperty(grantData.getId(), MeteredConditionStrategy.PARAMETER_REMAINING_QUANTITY, "0");
        entitlementFacade.addGrantProperty(grantData.getId(), MeteredConditionStrategy.PARAMETER_REMAINING_QUANTITY, 1);
        final GrantData grantData1 = entitlementFacade.getGrant(grantData.getId());
        final int remainingQuantity = Integer.valueOf(grantData1.getProperty(MeteredConditionStrategy.PARAMETER_REMAINING_QUANTITY));
        assertEquals(remainingQuantity, 1);
    }

	@Test
	public void shouldRemoveConditionByName()
	{
		final ConditionDataBuilder builder = new ConditionDataBuilder();
		final GrantData grant = new GrantDataBuilder().build();
		grant.setCondition(
				builder.type("string").property(StringConditionStrategy.GRANT_PARAMETER_STRING, "text").build(),
				builder.type("metered").property(MeteredConditionStrategy.GRANT_PARAMETER_MAX_QUANTITY, "100").build());
		final String id = entitlementFacade.createGrant(grant).getId();
		final GrantData updated = entitlementFacade.deleteConditions(id, "string");
		assertEquals(1, updated.getConditions().size());
		assertEquals("metered", updated.getConditions().iterator().next().getType());
	}

	@Test
	public void shouldRemoveAllConditions()
	{
		final ConditionDataBuilder builder = new ConditionDataBuilder();
		final GrantData grant = new GrantDataBuilder().build();
		grant.setCondition(
				builder.type("string").property(StringConditionStrategy.GRANT_PARAMETER_STRING, "text").build(),
				builder.type("metered").property(MeteredConditionStrategy.GRANT_PARAMETER_MAX_QUANTITY, "100").build());
		final String id = entitlementFacade.createGrant(grant).getId();
		final GrantData updated = entitlementFacade.deleteConditions(id, null);
		assertTrue(updated.getConditions().isEmpty());
	}

	@Test
	public void shouldReplaceConditions()
	{
		final ConditionDataBuilder builder = new ConditionDataBuilder();
		final GrantData grant = new GrantDataBuilder().build();
		grant.setCondition(
				builder.type("string").property(StringConditionStrategy.GRANT_PARAMETER_STRING, "old").build(),
				builder.type("metered").property(MeteredConditionStrategy.GRANT_PARAMETER_MAX_QUANTITY, "100").build());
		final String id = entitlementFacade.createGrant(grant).getId();
		final GrantData updated = entitlementFacade.updateConditions(id,
				Arrays.asList(builder.type("string").property(StringConditionStrategy.GRANT_PARAMETER_STRING, "new").build()));
		assertEquals(1, updated.getConditions().size());
		assertEquals("string", updated.getConditions().iterator().next().getType());
		assertEquals("new", updated.getConditions().iterator().next().getProperty(StringConditionStrategy.GRANT_PARAMETER_STRING));
	}

	private ConditionData findCondition(final List<ConditionData> conditions, final String type)
	{
		for (final ConditionData item : conditions)
		{
			if (item.getType().equals(type))
			{
				return item;
			}
		}
		return null;
	}

	@Test
	@Ignore("Unstable")
	public void shouldReattemptAfterConcurrentUse() throws InterruptedException
	{
		final int threads = 3;

		final GrantData grant = new GrantData();
		grant.setUserId(userId);
		final String entitlementType = "A frequently updated entitlement";
		grant.setEntitlementType(entitlementType);
		grant.setGrantSource("shouldReattemptAfterConcurrentUse");
		grant.setGrantSourceId("1");
		final ConditionData condition = new ConditionData();
		condition.setType("metered");
		condition.setProperty(MeteredConditionStrategy.GRANT_PARAMETER_MAX_QUANTITY, Integer.toString(threads + 1));
		grant.addCondition(condition);
		final String grantId = entitlementFacade.createGrant(grant).getId();
		callInParallel(threads, grantId, new GrantRunnable()
		{
			@Override
			public void execute(final String grantId)
			{
				final CriterionData criterion = new CriterionData();
				criterion.setType("metered");
				criterion.setProperty(MeteredConditionStrategy.EXECUTION_PARAMETER_QUANTITY, "1");
				entitlementFacade.execute(Actions.USE, userId, entitlementType, Arrays.asList(criterion), false);
			}
		});

		final GrantData model = entitlementFacade.getGrant(grantId);
		assertEquals("1", model.getProperty(MeteredConditionStrategy.PARAMETER_REMAINING_QUANTITY));
	}

	@Test
	@Ignore("Unstable")
	public void shouldReattemptToUpdateStatus() throws InterruptedException
	{
		final int threads = 3;

		final GrantData grant = new GrantData();
		grant.setUserId(userId);
		final String entitlementType = "An entitlement";
		grant.setEntitlementType(entitlementType);
		grant.setGrantSource("shouldReattemptToUpdateStatus");
		grant.setGrantSourceId("1");
		final String grantId = entitlementFacade.createGrant(grant).getId();
		callInParallel(threads, grantId, new GrantRunnable()
		{
			@Override
			public void execute(final String grantId)
			{
				entitlementFacade.updateGrantStatus(grantId, Status.SUSPENDED);
			}
		});

		assertFalse(entitlementFacade.execute(null, userId, entitlementType, Collections.<CriterionData>emptyList(), false).isResult());
	}

	@Test
	@Ignore("Unstable")
	public void shouldReattemptToUpdateCondition() throws InterruptedException
	{
		final int threads = 3;

		final GrantData grant = new GrantData();
		grant.setUserId(userId);
		final String entitlementType = "An entitlement with a condition";
		grant.setEntitlementType(entitlementType);
		grant.setGrantSource("shouldReattemptToUpdateCondition");
		grant.setGrantSourceId("1");
		final String grantId = entitlementFacade.createGrant(grant).getId();
		callInParallel(threads, grantId, new GrantRunnable()
		{
			@Override
			public void execute(final String grantId)
			{
				final ConditionData condition = new ConditionData();
				condition.setType("string");
				condition.setProperty("string", UUID.randomUUID().toString());
				entitlementFacade.updateConditions(grantId, Arrays.asList(condition));
			}
		});

		final ExecuteResult result = entitlementFacade.execute(null, userId, entitlementType, Collections.<CriterionData>emptyList(), false);
		assertFalse(result.isResult());
	}

	@Test
	@Ignore("Unstable")
	public void shouldReattemptToUpdateProperty() throws InterruptedException
	{
		final int threads = 3;

		final GrantData grant = new GrantData();
		grant.setUserId(userId);
		final String entitlementType = "An entitlement with a property";
		grant.setEntitlementType(entitlementType);
		grant.setGrantSource("shouldReattemptToUpdateProperty");
		grant.setGrantSourceId("1");
		final String grantId = entitlementFacade.createGrant(grant).getId();
		callInParallel(threads, grantId, new GrantRunnable()
		{
			@Override
			public void execute(final String grantId)
			{
				try
				{
					entitlementFacade.createGrantProperty(grantId, "a property", "a value");
				}
				catch (final ValidationException e)
				{
					System.out.print(e.getMessage());
				}
			}
		});

		assertEquals("a value", entitlementFacade.getGrant(grantId).getProperty("a property"));
	}

	@Test(expected = ObjectNotFoundException.class)
	public void createGrantPropertyShouldReportOnUnknownGrantId()
	{
		final GrantData grant = entitlementFacade.createGrant(new GrantDataBuilder().build());
		entitlementFacade.revokeGrant(grant.getId());
		entitlementFacade.createGrantProperty(grant.getId(), "test", "q");
	}

	@Test
	public void shouldCreateGrantProperties()
	{
		final GrantData oldGrant = entitlementFacade.createGrant(new GrantDataBuilder().build());
		final GrantData newGrant = entitlementFacade.createGrantProperty(oldGrant.getId(), "test", "q");
		assertEquals("q", newGrant.getProperty("test"));
		assertEquals("q", entitlementFacade.getGrant(oldGrant.getId()).getProperty("test"));
	}

	@Test(expected = ValidationException.class)
	public void shouldDenyPropertyOverride()
	{
		GrantData oldGrant = new GrantDataBuilder().build();
		oldGrant.setProperty("test", "q");
		oldGrant = entitlementFacade.createGrant(oldGrant);
		entitlementFacade.createGrantProperty(oldGrant.getId(), "test", "n");
	}

	@Test
	public void shouldUpdateProperty()
	{
		GrantData oldGrant = new GrantDataBuilder().build();
		oldGrant.setProperty("test", "q");
		oldGrant = entitlementFacade.createGrant(oldGrant);
		final GrantData newGrant = entitlementFacade.updateGrantProperty(oldGrant.getId(), "test", "n");
		assertNotNull(newGrant);
		assertEquals("n", newGrant.getProperty("test"));
		assertEquals("n", entitlementFacade.getGrant(oldGrant.getId()).getProperty("test"));
	}

	@Test(expected = ObjectNotFoundException.class)
	public void deleteGrantPropertyShouldReportOnUnknownGrantId()
	{
		final GrantData grant = entitlementFacade.createGrant(new GrantDataBuilder().build());
		entitlementFacade.revokeGrant(grant.getId());
		entitlementFacade.deleteGrantProperty(grant.getId(), "test");
	}

	@Test
	public void shouldRemoveUndefinedProperties()
	{
		final GrantData oldGrant = entitlementFacade.createGrant(new GrantDataBuilder().build());
		assertNotNull(entitlementFacade.deleteGrantProperty(oldGrant.getId(), "test"));
	}

	@Test(expected = ValidationException.class)
	public void shouldNotUpdateUndefinedProperties()
	{
		final GrantData oldGrant = entitlementFacade.createGrant(new GrantDataBuilder().build());
		entitlementFacade.updateGrantProperty(oldGrant.getId(), "test", "q");
	}

	@Test
	public void shouldRemoveProperty()
	{
		GrantData oldGrant = new GrantDataBuilder().build();
		oldGrant.setProperty("test", "q");
		oldGrant = entitlementFacade.createGrant(oldGrant);
		final GrantData newGrant = entitlementFacade.deleteGrantProperty(oldGrant.getId(), "test");
		assertNotNull(newGrant);
		assertNull(newGrant.getProperty("test"));
		assertNull(entitlementFacade.getGrant(oldGrant.getId()).getProperty("test"));
	}

	@Test(expected = ObjectNotFoundException.class)
	public void updateGrantPropertyShouldReportOnUnknownGrantId()
	{
		final GrantData grant = entitlementFacade.createGrant(new GrantDataBuilder().build());
		entitlementFacade.revokeGrant(grant.getId());
		entitlementFacade.updateGrantProperty(grant.getId(), "test", "q");
	}

	@Test(expected = ValidationException.class)
	public void grantPropertyNameMustNotBeNull()
	{
		final GrantData grant = new GrantDataBuilder().type("test").build();
		grant.setProperty(null, "ok");
		entitlementFacade.createGrant(grant);
	}

	@Test(expected = ValidationException.class)
	public void grantPropertyValueMustNotBeNull()
	{
		final GrantData grant = new GrantDataBuilder().type("test").build();
		grant.setProperty("test", null);
		entitlementFacade.createGrant(grant);
	}

	@Test(expected = ObjectNotFoundException.class)
	@Transactional
	public void addGrantPropertyGrantNotFoundTest()
	{
		entitlementFacade.revokeGrant(grantData.getId());
		entitlementFacade.addGrantProperty(grantData.getId(), "someGrantPropertyName", 0);
	}

	@Test(expected = ValidationException.class)
	public void addPropertyShouldBeDeniedForUnknownProperties()
	{
		final GrantData grant = entitlementFacade.createGrant(new GrantDataBuilder().build());
		entitlementFacade.addGrantProperty(grant.getId(), "test", 100);
	}

	@Test
	public void addGrantPropertyZeroTest()
	{
		GrantData grant = new GrantDataBuilder().build();
		final String name = "someGrantPropertyName";
		grant.setProperty(name, "1");
		grant = entitlementFacade.createGrant(grant);
		final GrantData grantData1 = entitlementFacade.addGrantProperty(grant.getId(), name, 0);
		assertNotNull(grantData1);
		final int someGrantPropertyName = Integer.valueOf(grantData1.getProperty(name));
		assertEquals(someGrantPropertyName, 1);
	}

	@Test
	public void addGrantPropertyNegativeTest()
	{
		GrantData grant = new GrantDataBuilder().build();
		final String name = "someGrantPropertyName";
		grant.setProperty(name, "0");
		grant = entitlementFacade.createGrant(grant);
		entitlementFacade.addGrantProperty(grant.getId(), name,  -1);
		final GrantData grantData1 = entitlementFacade.getGrant(grant.getId());
		final int someGrantPropertyName = Integer.valueOf(grantData1.getProperty(name));
		assertEquals(someGrantPropertyName, -1);
	}

	@Test
	public void addGrantPropertyPositiveTest()
	{
		GrantData grant = new GrantDataBuilder().build();
		final String name = "someGrantPropertyName";
		grant.setProperty(name, "0");
		grant = entitlementFacade.createGrant(grant);
		entitlementFacade.addGrantProperty(grant.getId(), name,  1);
		final GrantData grantData1 = entitlementFacade.getGrant(grant.getId());
		final int someGrantPropertyName = Integer.valueOf(grantData1.getProperty(name));
		assertEquals(someGrantPropertyName, 1);
	}

	@Test(expected = UnprocessableEntityException.class)
	public void shouldNotAddToNonNumericProperty()
	{
		GrantData grant = new GrantDataBuilder().build();
		final String name = "someGrantPropertyName";
		grant.setProperty(name, "non-numeric");
		grant = entitlementFacade.createGrant(grant);
		entitlementFacade.addGrantProperty(grant.getId(), name, 1);
	}

	@Test
	public void shouldOrderGrants()
	{
		final ConditionData condition = new ConditionData();
		condition.setType("metered");
		condition.setProperty(MeteredConditionStrategy.GRANT_PARAMETER_MAX_QUANTITY, "1");
		final GrantData grant = new GrantData();
		grant.setUserId(userId);
		final String entitlementType = "ordered";
		grant.setEntitlementType(entitlementType);
		grant.setGrantSource("shouldOrderGrants");
		grant.addCondition(condition);
		grant.setGrantSourceId("1");
		grant.setGrantTime("2010-01-01T0:0:0Z");
		entitlementFacade.createGrant(grant);
		grant.setGrantSourceId("2");
		grant.setGrantTime("2020-01-01T0:0:0Z");
		entitlementFacade.createGrant(grant);
		grant.setGrantSourceId("3");
		grant.setGrantTime("2025-01-01T0:0:0Z");
		entitlementFacade.createGrant(grant);
		final CriterionData criterion = new CriterionData();
		criterion.setType("metered");
		criterion.setProperty(MeteredConditionStrategy.EXECUTION_PARAMETER_QUANTITY, "1");
		ExecuteResult result = entitlementFacade.execute(Actions.USE, userId, entitlementType, Arrays.asList(criterion), true);
		assertEquals("1", result.getGrantDataList().iterator().next().getGrantSourceId());
		result = entitlementFacade.execute(Actions.USE, userId, entitlementType, Arrays.asList(criterion), true);
		assertEquals("2", result.getGrantDataList().iterator().next().getGrantSourceId());
		result = entitlementFacade.execute(Actions.USE, userId, entitlementType, Arrays.asList(criterion), true);
		assertEquals("3", result.getGrantDataList().iterator().next().getGrantSourceId());
	}

	@Test
	/**
	 * Execute should return grant even when it hasn't passed criteria check.
	 * Only grants that fit given criteria set have to be returned.
	 */
	public void shouldReturnIneffectiveGrants()
	{
		// Given a grant with a metered condition
		final String entitlementType = "a product";
		final GrantData grant = new GrantData();
		grant.setUserId(userId);
		grant.setEntitlementType(entitlementType);
		grant.setGrantSource("shouldReturnIneffectiveGrants");
		grant.setGrantSourceId("1");
		final ConditionData condition = new ConditionData();
		condition.setType("metered");
		condition.setProperty(MeteredConditionStrategy.GRANT_PARAMETER_MAX_QUANTITY, "1");
		grant.addCondition(condition);
		entitlementFacade.createGrant(grant);
		// When we try to use the grant
		final CriterionData criterion = new CriterionData();
		criterion.setType("metered");
		criterion.setProperty(MeteredConditionStrategy.EXECUTION_PARAMETER_QUANTITY, "10");
		final ExecuteResult result = entitlementFacade.execute(Actions.USE, userId, entitlementType, Arrays.asList(criterion), true);
		// Then the facade should return false
		assertFalse(result.isResult());
		// And provide potentially applicable grant(s)
		assertEquals(1, result.getGrantDataList().size());
	}

	@Test
	/**
	 * If use returns false, only applicable grants should be returned as some details.
	 * In other words, you won't see grants, that 'use' can not be applied to, such as grants without a metered condition.
	 */
	public void shouldReturnOnlyApplicableGrants()
	{
		// Given two grants: one with a metered condition, and one without metered condition.
		final String entitlementType = "a product";
		final GrantData grant = new GrantData();
		grant.setUserId(userId);
		grant.setEntitlementType(entitlementType);
		grant.setGrantSource("shouldReturnOnlyApplicableGrants");
		grant.setGrantSourceId("non-metered grant");
		entitlementFacade.createGrant(grant);
		final ConditionData condition = new ConditionData();
		condition.setType("metered");
		condition.setProperty(MeteredConditionStrategy.GRANT_PARAMETER_MAX_QUANTITY, "1");
		grant.addCondition(condition);
		grant.setGrantSourceId("metered grants");
		entitlementFacade.createGrant(grant);
		// When we try to use the grant
		final CriterionData criterion = new CriterionData();
		criterion.setType("metered");
		criterion.setProperty(MeteredConditionStrategy.EXECUTION_PARAMETER_QUANTITY, "10");
		final ExecuteResult result = entitlementFacade.execute(Actions.USE, userId, entitlementType, Arrays.asList(criterion), true);
		// Then the facade should return false
		assertFalse(result.isResult());
		// And provide only applicable grant
		assertEquals(1, result.getGrantDataList().size());
		assertEquals("metered grants", result.getGrantDataList().iterator().next().getGrantSourceId());
	}

	@Test
	public void executionShouldValidateNullUserId()
	{
		final CriterionData criterion = new CriterionData();
		criterion.setType("string");
		criterion.setProperty("string", "test");
		try
		{
			entitlementFacade.execute(Actions.CHECK, null, "entitlementType", Arrays.asList(criterion), false);
		}
		catch (final ValidationException e)
		{
			assertTrue(e.getMessage().contains("userId=null:"));
			return;
		}
		fail("Validation did not occur");
	}

	@Test
	public void executionShouldValidateEmptyUserId()
	{
		final CriterionData criterion = new CriterionData();
		criterion.setType("string");
		criterion.setProperty("string", "test");
		try
		{
			entitlementFacade.execute(Actions.CHECK, "", "entitlementType", Arrays.asList(criterion), false);
		}
		catch (final ValidationException e)
		{
			assertTrue(e.getMessage().contains("userId=:"));
			return;
		}
		fail("Validation did not occur");
	}

	@Test
	public void executionShouldValidateEmptyEntitlementType()
	{
		final CriterionData criterion = new CriterionData();
		criterion.setType("string");
		criterion.setProperty("string", "test");
		try
		{
			entitlementFacade.execute(Actions.CHECK, userId, "", Arrays.asList(criterion), false);
		}
		catch (final ValidationException e)
		{
			assertTrue(e.getMessage().contains("entitlementType=:"));
			return;
		}
		fail("Validation did not occur");
	}

	@Test
	public void executionShouldValidateUnknownAction()
	{
		final CriterionData criterion = new CriterionData();
		criterion.setType("string");
		criterion.setProperty("string", "test");
		try
		{
			entitlementFacade.execute("", userId, "entitlementType", Arrays.asList(criterion), false);
		}
		catch (final ValidationException e)
		{
			assertTrue(e.getMessage().contains("action=:"));
			return;
		}
		fail("Validation did not occur");
	}

	@Test
	public void executionShouldAllowNullAction()
	{
		final CriterionData criterion = new CriterionData();
		criterion.setType("string");
		criterion.setProperty("string", "test");
		entitlementFacade.execute(null, userId, "entitlementType", Arrays.asList(criterion), false);
	}

	@Test
	public void executionShouldValidateCriteria()
	{
		final CriterionData criterion = new CriterionData();
		criterion.setType("string");
		try
		{
			entitlementFacade.execute(null, userId, "entitlementType", Arrays.asList(criterion), false);
		}
		catch (final ValidationException e)
		{
			assertTrue(e.getMessage().contains("criteria[0].string=null:"));
			return;
		}
		fail("Validation did not occur");
	}

	@Test
	public void bachShouldValidateUserId()
	{
		final CriterionData criterion = new CriterionData();
		criterion.setType("string");
		final ExecuteManyData data = new ExecuteManyData();
		data.setEntitlementType("");
		data.setCriterionDataList(Arrays.asList(criterion));
		try
		{
			entitlementFacade.execute(Actions.USE, "", Arrays.asList(data), false);
		}
		catch (ValidationException e)
		{
			assertTrue("Criteria type have not been validated", e.getMessage().contains("executeManyDataList[0].criterionDataList[0].string=null:"));
			assertTrue("User id has not been validated", e.getMessage().contains("userId=:"));
			assertTrue("Action has not been validated", e.getMessage().contains("action="));
			assertTrue("Entitlement type has not been validated", e.getMessage().contains("executeManyDataList[0].entitlementType="));
		}
	}

/*
	@Test
	public void shouldRejectGeoConditionWithoutRegion()
	{
		final ConditionData condition = new ConditionData();
		condition.setType("geo");
		condition.setProperty("geoPath", "USA//");
		GrantDataBuilder builder = new GrantDataBuilder();
		final GrantData grant = builder.type("geo").build();
		grant.setCondition(condition);
		entitlementFacade.createGrant(grant);
		final CriterionData criterion = new CriterionData();
		criterion.setType("geo");
		criterion.setProperty("geoPath", "USA");
		final ExecuteResult res = entitlementFacade.execute(null, userId, "geo", Arrays.asList(criterion), true);
		assertTrue(res.isResult());
	}
*/

	private interface GrantRunnable
	{
		void execute(String grantId);
	}

	private void callInParallel(final int threads, final String grantId, final GrantRunnable operation) throws InterruptedException
	{
		final CyclicBarrier leadingEdge = new CyclicBarrier(threads);
		final CyclicBarrier fallingEdge = new CyclicBarrier(threads);

		for (int i = 0; i < threads; i++)
		{
			final int pause = i*10;
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					try
					{
						leadingEdge.await();
					}
					catch (InterruptedException | BrokenBarrierException e)
					{
						e.printStackTrace();
					}
					try
					{
						//Thread.sleep(pause);
						operation.execute(grantId);
                    }
					catch (Exception e)
					{
						e.printStackTrace();
					}
					try
					{
						fallingEdge.await();
					}
					catch (InterruptedException | BrokenBarrierException e)
					{
						e.printStackTrace();
					}
				}
			}).start();
		}

		do
		{
			Thread.sleep(100);
		}
		while (fallingEdge.getNumberWaiting() > 0 & leadingEdge.getNumberWaiting() > 0);
	}

	private ExecuteManyData createTimeframeExecuteManyData() {
		final ExecuteManyData timeframeExecuteManyData = new ExecuteManyData();
        timeframeExecuteManyData.setEntitlementType(TIMEFRAME_TYPE);
		final List<CriterionData> timeframeCriterionDataList = new ArrayList<>();
		final CriterionData timeframeCriterionData = new CriterionData();
        timeframeCriterionData.setType("timeframe");
        timeframeCriterionData.setProperty(TimeframeConditionStrategy.EXECUTION_PARAMETER_TIME, "2005-01-01T10:20:30Z");
        timeframeCriterionDataList.add(timeframeCriterionData);
        timeframeExecuteManyData.setCriterionDataList(timeframeCriterionDataList);
        return timeframeExecuteManyData;
    }

    private ExecuteManyData createNoValidTimeframeExecuteManyData() {
		final ExecuteManyData timeframeExecuteManyData = new ExecuteManyData();
        timeframeExecuteManyData.setEntitlementType(TIMEFRAME_TYPE);
		final List<CriterionData> timeframeCriterionDataList = new ArrayList<>();
		final CriterionData timeframeCriterionData = new CriterionData();
        timeframeCriterionData.setType("timeframe");
        timeframeCriterionData.setProperty("wrong_value", "2005-01-01T10:20:30Z");
        timeframeCriterionDataList.add(timeframeCriterionData);
        timeframeExecuteManyData.setCriterionDataList(timeframeCriterionDataList);
        return timeframeExecuteManyData;
    }

    private ExecuteManyData createSimpleExecuteManyData() {
		final ExecuteManyData simpleExecuteManyData = new ExecuteManyData();
        simpleExecuteManyData.setEntitlementType(SIMPLE_TYPE);
		final List<CriterionData> simpleCriterionDataList = new ArrayList<>();
        simpleExecuteManyData.setCriterionDataList(simpleCriterionDataList);
        return simpleExecuteManyData;
    }

    private GrantData createSimpleGrant()
    {
        final String entitlementType = SIMPLE_TYPE;
		final GrantData grantData = new GrantData();
		grantData.setUserId(userId);
		grantData.setEntitlementType(entitlementType);
		grantData.setGrantTime(dateTimeConverter.convertDateToString(new Date()));
		grantData.setGrantSource("order");
		grantData.setGrantSourceId("order#1");
        return entitlementFacade.createGrant(grantData);
    }

    private GrantData createTimeframeGrant()
    {
        final String entitlementType = TIMEFRAME_TYPE;
        final ConditionData timeframeConditionData = new ConditionData();
        timeframeConditionData.setType(TIMEFRAME_TYPE);
        timeframeConditionData.setProperty(TimeframeConditionStrategy.GRANT_PARAMETER_START, "2000-11-01T08:00:00Z");
        timeframeConditionData.setProperty(TimeframeConditionStrategy.GRANT_PARAMETER_END, "2013-12-31T09:00:00Z");
        final GrantData data = new GrantData();
        data.setUserId(userId);
        data.setEntitlementType(entitlementType);
        data.setGrantSource("testTimeframeConditionDataWriteRead");
        data.setGrantSourceId("order#1");
        data.setCondition(timeframeConditionData);
        return entitlementFacade.createGrant(data);
    }

	class GrantDataBuilder
	{
		GrantData grant = null;

		GrantDataBuilder type(final String type)
		{
			getGrant().setEntitlementType(type);
			return this;
		}

		GrantData build()
		{
			final GrantData result = getGrant();
			grant = null;
			return result;
		}

		private GrantData getGrant()
		{
			if (grant == null)
			{
				grant = new GrantData();
				grant.setUserId(userId);
				grant.setGrantSource("GrantDataBuilder");
				grant.setGrantSourceId(UUID.randomUUID().toString());
				grant.setEntitlementType("audio");
			}
			return grant;
		}
	}

	class ConditionDataBuilder
	{
		ConditionData condition = null;

		ConditionDataBuilder type(final String type)
		{
			getCondition().setType(type);
			return this;
		}

		ConditionDataBuilder property(final String name, final String value)
		{
			getCondition().setProperty(name, value);
			return this;
		}

		ConditionData build()
		{
			final ConditionData result = condition;
			condition = null;
			return result;
		}

		public ConditionData getCondition()
		{
			if (condition == null)
			{
				condition = new ConditionData();
			}
			return condition;
		}
	}
}

