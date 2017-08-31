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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.hybris.kernel.api.CriteriaQuery;
import com.hybris.kernel.api.LiveDataRestrictions;
import com.hybris.kernel.api.ManagedObjectCallback;
import com.hybris.kernel.api.PersistenceManager;
import com.hybris.kernel.api.Restrictions;
import com.hybris.services.entitlements.api.exceptions.ValidationException;
import com.hybris.services.entitlements.api.ObjectNotFoundException;
import com.hybris.services.entitlements.api.status.Status;
import com.hybris.services.entitlements.condition.metered.MeteredConditionStrategy;
import com.hybris.services.entitlements.domain.Condition;
import com.hybris.services.entitlements.domain.Grant;
import com.hybris.services.entitlements.service.GrantService;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-service-test-spring.xml")
public class DefaultGrantServiceTest
{
	@Autowired
	GrantService grantService;

	@Autowired
	PersistenceManager persistenceManager;

	@Autowired
	PlatformTransactionManager transactionManager;

	private String userId;
    private GrantBuilder grantBuilder;

	String entitlementType1 = "entitlementType1";
	String entitlementType2 = "entitlementType2";
	String grantSource1 = "grantSource1";
	String grantSource2 = "grantSource2";
	String grantId1 = "grantId1";
	String grantId2 = "grantId2";

	@Before
	public void onInit()
    {
		userId = UUID.randomUUID().toString();
        grantBuilder = new GrantBuilder();
	}

	@Test
	/**
	 * We have a compound document in mongodb:
	 grant: {
		 "_id" : "USER_1_AUD_ID_1--order--order #4849bdf8\\-\\943d\\-\\4db3\\-\\bb3f\\-\\09e5626e480c--SMS",
		 "conditions" : [
			 {
				 "yproperty" : {
					 "maxQuantity" : "1000000"
				 },
				 "type" : "metered",
				 "version" : null,
				 "typeCode" : "Condition"
			 }
		 ],
		 "creationTime" : ISODate("2014-01-17T03:42:39.870Z"),
		 "entitlementType" : "SMS",
		 "grantSourceId" : "order #4849bdf8-943d-4db3-bb3f-09e5626e480c",
		 "grantSource" : "order",
		 "grantTime" : ISODate("2014-01-17T03:42:39.677Z"),
		 "modifiedTime" : ISODate("2014-01-17T03:43:57.028Z"),
		 "status" : "ACTIVE",
		 "typeCode" : "Grant",
		 "userId" : "USER_1_AUD_ID_1",
		 "version" : NumberLong(3),
		 "yproperty" : {
			 "remainingQuantity" : "999998"
	 	 }
	 }

	 When we iterate through N documents of this kind, 1+4*N queries to mongodb are performed, despite of the documents
	 are solid and can be fully fetched by single query.

	 We tried CriteriaQuery.resultList and PersistenceManager.iterate. They both produce the same number of queries.

	 Do we have any options to switch lazy-load off? It's very important for our case to reduce the number of requests to mongodb.
	 */
	public void checkIterationBehaviour()
	{
		// The issue appears only when access is made from within separate transaction then creation.
		TransactionStatus tx = transactionManager.getTransaction(null);
		// Given a grant with a metered condition
		final Grant grant = grantService.newGrant();
		grant.setGrantId(UUID.randomUUID().toString());
		grant.setUserId(userId);
		grant.setEntitlementType("video");
		grant.setGrantSource("checkIterationBehaviour");
		grant.setGrantSourceId("1");
		grant.setGrantTime(new Date());
		grant.setProperty(MeteredConditionStrategy.PARAMETER_REMAINING_QUANTITY, "0");
		final Condition condition = grantService.newCondition();
		condition.setType("metered");
		condition.setProperty(MeteredConditionStrategy.GRANT_PARAMETER_MAX_QUANTITY, "100");
		grant.setConditions(Arrays.asList(condition));
		transactionManager.commit(tx);

		// When we access the grant
		tx = transactionManager.getTransaction(null);
		CriteriaQuery<Grant> query = persistenceManager.createCriteriaQuery(Grant.class)
				.where(Restrictions.eq(Grant.USERID, userId))
				.and(Restrictions.eq(Grant.GRANTSOURCE, "checkIterationBehaviour"));
		// Then 1 query happens here (fetch items by criteria)
		persistenceManager.iterate(query, new ManagedObjectCallback<Grant>()
		{
			@Override
			public void process(final Grant item)
			{
				// And 2 queries happen here (lazy-load of Grant properties)
				final Object remainingQuantity = item.getProperty(MeteredConditionStrategy.PARAMETER_REMAINING_QUANTITY);
				assertEquals("0", remainingQuantity.toString());
				// And 2 queries happen here (lazy-load of embedded collection of conditions)
				final Condition condition = item.getConditions().iterator().next();
				assertEquals("metered", condition.getType());
				final Object maxQuantity = condition.getProperty(MeteredConditionStrategy.GRANT_PARAMETER_MAX_QUANTITY);
				assertEquals("100", maxQuantity);
			}
		});
		transactionManager.commit(tx);
	}

    @Test
    @Transactional
    public void testUpdateConditions()
    {
        final Grant grant = grantBuilder.createDefaultTestingGrant();
        persistenceManager.flush();
        Condition testCondition = grantService.newCondition();
        testCondition.setType("test");
        Condition testCondition2 = grantService.newCondition();
        testCondition2.setType("test2");


        grantService.updateConditions(grant.getGrantId(), Arrays.asList(testCondition, testCondition2));

        assertTrue(grant.getConditions().contains(testCondition));
        assertTrue(grant.getConditions().contains(testCondition2));
        assertEquals(grant.getConditions().size(), 2);
    }

	@Test(expected = ObjectNotFoundException.class)
	@Transactional
	public void testUpdateUnknownEntitlement()
	{
		grantService.updateConditions(UUID.randomUUID().toString(), Collections.<Condition>emptyList());
	}

    @Test(expected = IllegalArgumentException.class)
    @Transactional
    public void testUpdateNullIdEntitlement()
    {
        grantService.updateConditions(null, Collections.<Condition>emptyList());
    }

	@Test(expected = com.hybris.kernel.api.exceptions.ValidationException.class)
	@Transactional
	public void testCreateEntitlementWithTheSameNatKey()
	{
		final Grant grant1 = grantService.newGrant();
		final Grant grant2 = grantService.newGrant();
		grant1.setUserId(userId);
		grant1.setEntitlementType("SMS");
		grant1.setGrantSource("order");
		grant1.setGrantSourceId("order#1");
		grant2.setUserId(userId);
		grant2.setEntitlementType("SMS");
		grant2.setGrantSource("order");
		grant2.setGrantSourceId("order#1");
		persistenceManager.flush();
	}

    @Test
	@Transactional
    public void shouldCreateGrant()
	{
        final Grant result = grantService.newGrant();
		assertNotNull(result);
		result.setGrantId(UUID.randomUUID().toString());
		result.setUserId(userId);
		result.setEntitlementType("test");
		result.setGrantSource("shouldCreateGrant");
		result.setGrantSourceId("1");
		result.setGrantTime(new Date());
		persistenceManager.flush();
    }

	@Test
	@Transactional
	public void testRevokeEntitlements1()
	{
		final String userId1 = UUID.randomUUID().toString();
		final String userId2 = UUID.randomUUID().toString();
		grant(userId1, userId2);
		final int revoked = grantService.revokeGrants(userId1, null, null, null);
		assertEquals(8, revoked);
		assertFalse(check(userId1, null, null, null));
		assertTrue(check(userId2, null, null, null));
	}

	@Test
	@Transactional
	public void shouldFilterByStatus()
	{
		final GrantBuilder builder = new GrantBuilder();
		final String type = "SMS";
		final String grantSource = "shouldFilterByStatus";
		builder.source(grantSource).grant(type, type, type);
		final List<Grant> grants = grantService.getGrants(userId, type, grantSource, null, null);
		grantService.updateGrantStatus(grants.get(1).getGrantId(), Status.SUSPENDED);
		grantService.updateGrantStatus(grants.get(2).getGrantId(), Status.REVOKED);
		assertEquals(Arrays.asList(grants.get(0)), grantService.getGrants(userId, type, grantSource, null, Status.ACTIVE));
		assertEquals(Arrays.asList(grants.get(1)), grantService.getGrants(userId, type, grantSource, null, Status.SUSPENDED));
		assertEquals(Arrays.asList(grants.get(2)), grantService.getGrants(userId, type, grantSource, null, Status.REVOKED));
		assertEquals(Arrays.asList(grants.get(0), grants.get(1)), grantService.getGrants(userId, type, grantSource, null, null));
	}

	@Test
	@Transactional
	@Ignore("Why mutually exclusive restrictions return anything?")
	public void shouldFilterOut()
	{
		// Given: at least one active grant record
		new GrantBuilder().source("shouldFilterOut").grant("video");
		// When we select records using mutually exclusive criteria
		final CriteriaQuery<Grant> query = persistenceManager.createCriteriaQuery(Grant.class)
				.where(Restrictions.eq(Grant.STATUS, Status.ACTIVE.toString()))
				.and(Restrictions.neq(Grant.STATUS, Status.ACTIVE.toString()));
		// Then no records should be selected
		assertTrue(query.resultList().isEmpty());
	}

	@Test
	@Ignore("EQ and NEQ issue")
	@Transactional
	public void testEqAndNeq() throws InterruptedException
	{
		final CriteriaQuery<Grant> query = persistenceManager.createCriteriaQuery(Grant.class)
				.where(LiveDataRestrictions.neq(Grant.STATUS.name(), Status.REVOKED.name()))
				.and(LiveDataRestrictions.eq(Grant.STATUS.name(), Status.SUSPENDED));
		List<Grant> grants = query.resultList();
		System.out.println(grants.size());
	}

	@Test
	@Transactional
	public void testRevokeEntitlements2()
	{
		final String userId1 = UUID.randomUUID().toString();
		final String userId2 = UUID.randomUUID().toString();
		grant(userId1, userId2);
		final int revoked = grantService.revokeGrants(userId1, entitlementType1, null, null);
		assertEquals(4, revoked);
		assertFalse(check(userId1, entitlementType1, null, null));
		assertTrue(check(userId2, entitlementType1, null, null));
	}

	@Test
	@Transactional
	public void testRevokeEntitlements3()
	{
		final String userId1 = UUID.randomUUID().toString();
		final String userId2 = UUID.randomUUID().toString();
		grant(userId1, userId2);
		final int revoked = grantService.revokeGrants(userId1, entitlementType1, grantSource1, null);
		assertEquals(2, revoked);
		assertFalse(check(userId1, entitlementType1, grantSource1, null));
		assertTrue(check(userId2, entitlementType1, grantSource1, null));
	}

	@Test
	@Transactional
	public void testRevokeEntitlements4()
	{
		final String userId1 = UUID.randomUUID().toString();
		final String userId2 = UUID.randomUUID().toString();
		grant(userId1, userId2);
		final int revoked = grantService.revokeGrants(userId1, entitlementType1, grantSource1, grantId1);
		assertEquals(1, revoked);
		assertFalse(check(userId1, entitlementType1, grantSource1, grantId1));
		assertTrue(check(userId2, entitlementType1, grantSource1, grantId1));
	}

	@Test(expected = ValidationException.class)
	@Transactional
	public void testRevokeEntitlementsUserIdIsNull()
	{
		grantService.revokeGrants(null, null, null, null);
	}

	@Test
	@Transactional
	public void testRevokeEntitlements5()
	{
		final String userId1 = UUID.randomUUID().toString();
		grantService.revokeGrants(userId1, null, grantSource1, null);
	}

	@Test
	@Transactional
	public void testRevokeEntitlements6()
	{
		final String userId1 = UUID.randomUUID().toString();
		grantService.revokeGrants(userId1, null, null, grantId1);
	}

	@Test
	@Transactional
	public void testRevokeEntitlements7()
	{
		final String userId1 = UUID.randomUUID().toString();
		grantService.revokeGrants(userId1, entitlementType1, null, grantId1);
	}

	private boolean check(final String userId, final String entitlementType, final String grantSource, final String grantSourceId)
	{
	 	// TODO replace with entitlementService.check?
		return !grantService.getGrants(userId, entitlementType, grantSource, grantSourceId, null).isEmpty();
	}

	private void grant(final String userId1, final String userId2)
	{
        grantBuilder.userId(userId1).source(grantSource1)
				.id(grantId1).grant(entitlementType1, entitlementType2)
				.id(grantId2).grant(entitlementType1, entitlementType2)
				.source(grantSource2)
				.id(grantId1).grant(entitlementType1, entitlementType2)
				.id(grantId2).grant(entitlementType1, entitlementType2)
				.userId(userId2).source(grantSource1)
				.id(grantId1).grant(entitlementType1, entitlementType2)
				.id(grantId2).grant(entitlementType1, entitlementType2)
				.source(grantSource2)
				.id(grantId1).grant(entitlementType1, entitlementType2)
				.id(grantId2).grant(entitlementType1, entitlementType2);
	}

	@Test
	@Transactional
	public void shouldRevokeEntitlement()
	{
		// given
        grantBuilder.grant("SMS", "Minutes", "3G");
		// when
		final int revoked = grantService.revokeGrants(userId, null, null, null);
		// then
		assertEquals(3, revoked);
		assertTrue(grantService.getGrants(userId, null, null, null, null).isEmpty());
	}

	private class GrantBuilder
	{
		private String grantSource = "grantSource";
        private int grantId = 0;
        private String grantSourceId = null;
		private String user = userId;
		private Date date;

		@Transactional
		GrantBuilder grant(final String... names)
		{
			for (final String name : names)
			{
				createDefaultTestingGrant().setEntitlementType(name);
			}
			return this;
		}

        Grant createDefaultTestingGrant() {
            final Grant grant = grantService.newGrant();
			grant.setGrantId(UUID.randomUUID().toString());
            grant.setUserId(user);
            grant.setGrantSource(grantSource);
            grant.setGrantSourceId(grantSourceId == null ? Integer.toString(grantId++) : grantSourceId);
            grant.setEntitlementType(entitlementType1);
            grant.setGrantTime(date == null ? new Date() : date);
            return grant;
        }

		GrantBuilder source(final String grantSource)
		{
			this.grantSource = grantSource;
			return this;
		}

		/**
		 * Start grantId sequence
		 *
		 * @param grantSourceId start value
		 * @return this builder
		 */
		GrantBuilder id(final int grantSourceId)
		{
			this.grantId = grantSourceId;
			this.grantSourceId = null;
			return this;
		}

		GrantBuilder id(final String grantId) {
			grantSourceId = grantId;
			return this;
		}

		GrantBuilder timestamp(final int offset)
		{
			date = new Date(System.currentTimeMillis() + offset);
			return this;
		}

		GrantBuilder userId(final String userId)
		{
			this.user = userId;
			return this;
		}

	}

    @Test
    @Transactional
    public void testRevokeGrantById()
    {
        final Grant grant = grantBuilder.createDefaultTestingGrant();
        persistenceManager.flush();
        grantService.revokeGrant(grant.getGrantId());
	}

	@Test(expected = ObjectNotFoundException.class)
	@Transactional
	public void revokeUnknownGrant()
	{
		grantService.revokeGrant(UUID.randomUUID().toString());
    }

    @Test(expected = IllegalArgumentException.class)
    @Transactional
    public void testRevokeGrantByNullId()
    {
        grantService.revokeGrant(null); // not found to revoked
    }

    @Test
    @Transactional
    public void testGetGrantById()
    {
        final Grant grant = grantBuilder.createDefaultTestingGrant();
        persistenceManager.flush();

        assertEquals(grantService.getGrant(grant.getGrantId()).getId(), grant.getId()); // it is the same grant and we didn't get an exception
        grantService.revokeGrant(grant.getGrantId()); // revoke grant
        try
        {
            grantService.getGrant(grant.getGrantId()); // there should be no such grant after revoke ..
            fail();
        }
        catch (ObjectNotFoundException e)  // .. so we must get an exception
        {
            // it is ok, nothing to worry about )
        }
        catch (Exception e)
        {
            fail(); // that's bad and unexpected
        }
    }

    @Test(expected = IllegalArgumentException.class)
    @Transactional
    public void testGetGrantByNullId()
    {
        grantService.getGrant(null);
    }
}
