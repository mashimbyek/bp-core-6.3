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

import static org.fest.assertions.Assertions.assertThat;

import com.hybris.kernel.api.CriteriaQuery;
import com.hybris.kernel.api.ManagedObjectCallback;
import com.hybris.kernel.api.PersistenceManager;
import com.hybris.kernel.api.Restrictions;
import com.hybris.services.entitlements.domain.Condition;
import com.hybris.services.entitlements.domain.Grant;

import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/entitlements-test-spring.xml")
public class EagerLoadingGrantsTest
{
	private final Logger LOG = LoggerFactory.getLogger(EagerLoadingGrantsTest.class);

	/**
	 * Schemaless attrubutes
	 */
	public static final String DYN1 = "dyn1";
	public static final String DYN2 = "dyn2";


	@Autowired
	private PersistenceManager persistenceManager;

	@Autowired
	private InvocationTracer invocationTracer;

	@Autowired
	PlatformTransactionManager transactionManager;

	private String userId;

	@Before
	public void onInit()
	{
		userId = UUID.randomUUID().toString();
		createData();
	}

	@Test
	public void checkSearchBehaviour()
	{
		final TransactionStatus tx = transactionManager.getTransaction(null);

		invocationTracer.clear();

		for (final Grant item : persistenceManager.search(createQuery()))
		{
			readCollectionAndNestedSchemaless(item);
		}
		LOG.debug(" [SEARCH] " + invocationTracer.printStat());
		assertThat(invocationTracer.getCalls()).hasSize(1);
		transactionManager.commit(tx);
	}

	@Test
	public void checkIterationBehaviour()
	{

		final TransactionStatus tx = transactionManager.getTransaction(null);
		invocationTracer.clear();
		persistenceManager.iterate(createQuery(), new ManagedObjectCallback<Grant>()
		{
			@Override
			public void process(final Grant item)
			{
				readCollectionAndNestedSchemaless(item);
			}
		});
		assertThat(invocationTracer.getCalls()).hasSize(1);
		LOG.debug(" [ITERATE] " + invocationTracer.printStat());
		transactionManager.commit(tx);
	}

	private void createData()
	{
		final TransactionStatus tx = transactionManager.getTransaction(null);
		for (int j = 0; j < 10; ++j)
		{
			final Grant grant = persistenceManager.create(Grant.class);
			grant.setGrantId(UUID.randomUUID().toString());
			grant.setUserId(userId);
			grant.setEntitlementType("video");
			grant.setGrantSource("checkIterationBehaviour");
			grant.setGrantSourceId(String.valueOf(j));
			grant.setGrantTime(new Date());
			grant.setProperty(DYN1, "0");
			grant.setProperty(DYN2, "0");
			for (int i = 0; i < 10; ++i)
			{
				final Condition condition = persistenceManager.create(Condition.class);
				condition.setType("metered");
				condition.setProperty(DYN1, "100");
				condition.setProperty(DYN2, "100");
				grant.getConditions().add(condition);
			}
		}
		transactionManager.commit(tx);
	}

	private CriteriaQuery<Grant> createQuery()
	{
		return persistenceManager.createCriteriaQuery(Grant.class)
				.where(Restrictions.eq(Grant.USERID, userId))
				.and(Restrictions.eq(Grant.GRANTSOURCE, "checkIterationBehaviour"));
	}


	private void readCollectionAndNestedSchemaless(final Grant item)
	{
		item.getProperty(DYN1);
		item.getProperty(DYN2);
		for (final Condition c : item.getConditions())
		{
			c.getType();
			c.getProperty(DYN1);
			c.getProperty(DYN2);
		}
	}
}
