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
package com.hybris.services.entitlements.atdd.keywords;

import com.hybris.services.entitlements.api.ExecuteResult;
import com.hybris.services.entitlements.api.GrantData;
import com.hybris.services.entitlements.condition.CriterionData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * Grant execution ATDD keywords.
 */
@Configurable
public class EntitlementsExecuteKeywordLibrary extends AbstractEntitlementsKeywordLibrary
{
    public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

    /**
     * Execute entitlement without condition.
     *
     * @param action action to perform (see {@link com.hybris.services.entitlements.api.Actions})
     * @param entitlementType Type of entitlement.
     * @param userId User id.
     * @param exists condition
     */
    public void executeEntitlement(final String action, final String entitlementType, final String userId, final boolean exists)
    {
        Assert.assertTrue(
                getEntitlementFacade().execute(action, userId, entitlementType,
                        Collections.<CriterionData>emptyList(), false).isResult() == exists
        );
    }

    /**
     * Execute entitlement with conditions.
     *
     * @param action action to perform (see {@link com.hybris.services.entitlements.api.Actions})
     * @param entitlementType Type of entitlement
     * @param userId User id
     * @param conditions conditions list
     * @param exists condition
	 * @return execution result
     */
    public ExecuteResult executeEntitlementWithCondition(final String action,
                                                         final String entitlementType,
                                                         final String userId,
                                                         final String conditions,
                                                         final boolean exists)
    {
        return executeEntitlementWithCondition(action, entitlementType, userId, conditions, exists, null, null);
    }

    /**
     * Execute entitlement with conditions.
     *
     * @param action action to perform (see {@link com.hybris.services.entitlements.api.Actions})
     * @param entitlementType Type of entitlement
     * @param userId User id
     * @param conditions conditions list
     * @param expectedExceptionType type of expected exception, may be null if no exception is expected
     * @param expectedExceptionMessage message of expected exception, may be null if no exception is expected
     * @param exists condition
	 * @return execution result
     */
    public ExecuteResult executeEntitlementWithCondition(final String action,
                                                         final String entitlementType,
                                                         final String userId,
                                                         final String conditions,
                                                         final boolean exists,
                                                         final String expectedExceptionType,
                                                         final String expectedExceptionMessage)
    {
        return executeAndExpectException(new ExpectExceptionCallback<ExecuteResult>()
		{
            @Override
            public ExecuteResult execute() throws Exception
            {
                final List<CriterionData> criteriaList = new ArrayList<>();

                for (final String condition : splitParameters(conditions))
                {
                    criteriaList.add(parseCondition(condition, CriterionData.class));
                }
                final ExecuteResult result = getEntitlementFacade().execute(action, userId, entitlementType, criteriaList, false);
                Assert.assertEquals(exists, result.isResult());
                return result;
            }
        }, expectedExceptionType, expectedExceptionMessage);
    }

    /**
     * Execute entitlement with conditions.
     *
     * @param action action to perform (see {@link com.hybris.services.entitlements.api.Actions})
     * @param entitlementType Type of entitlement
     * @param userId User id
     * @param conditions conditions list
     * @param exists condition
	 * @param details show details or not
	 * @param grantCount expected number of grants in result
	 * @return execution result
     */
    public ExecuteResult executeEntitlementWithConditionAndDetails(final String action,
                                                                   final String entitlementType,
                                                                   final String userId,
                                                                   final String conditions,
                                                                   final boolean exists,
                                                                   final boolean details,
                                                                   final int grantCount)
	{
        return executeEntitlementWithConditionAndDetails(
				action, entitlementType, userId, conditions, exists, details, grantCount, null, null);
    }

    /**
     * Execute entitlement with conditions and expect exception.
     *
	 * @param action action to perform (see {@link com.hybris.services.entitlements.api.Actions})
	 * @param entitlementType Type of entitlement
	 * @param userId User id
	 * @param conditions conditions list
	 * @param exists condition
	 * @param details show details or not
	 * @param grantCount expected number of grants in result
	 * @param expectedExceptionType exception type. null if exceptions are not expected
	 * @param expectedExceptionMessage exception message pattern
	 * @return execution result
	 */
	public ExecuteResult executeEntitlementWithConditionAndDetails(final String action,
			final String entitlementType,
			final String userId,
			final String conditions,
			final boolean exists,
			final boolean details,
			final int grantCount,
            final String expectedExceptionType,
            final String expectedExceptionMessage)
	{
        return executeAndExpectException(new ExpectExceptionCallback<ExecuteResult>()
		{
            @Override
            public ExecuteResult execute() throws Exception
            {
                final List<CriterionData> criteriaList = new ArrayList<>();

                for (final String condition : splitParameters(conditions))
                {
                    criteriaList.add(parseCondition(condition, CriterionData.class));
                }
                final ExecuteResult result = getEntitlementFacade().execute(action, userId, entitlementType, criteriaList, details);
                Assert.assertEquals(exists, result.isResult());
                int count = 0;
                if (result.getGrantDataList() != null)
				{
                    count = result.getGrantDataList().size();
                }
                Assert.assertEquals(grantCount, count);
                return result;
            }
        }, expectedExceptionType, expectedExceptionMessage);
    }

    /**
     * Execute entitlement with conditions.
     *
	 * @param action action to perform (see {@link com.hybris.services.entitlements.api.Actions})
	 * @param entitlementType Type of entitlement
	 * @param userId User id
	 * @param details show details or not
	 * @param result expected result
	 * @param grantCount expected number of grants in result
     */
    public void executeEntitlementWithDetails(final String action,
                                              final String entitlementType,
                                              final String userId,
                                              final boolean details,
                                              final boolean result,
                                              final int grantCount
    )
    {
        final ExecuteResult executeResult = getEntitlementFacade().execute(action,
                userId,
                entitlementType,
                Collections.<CriterionData>emptyList(),
                details
        );
        Assert.assertEquals(result, executeResult.isResult());
        int count = 0;
        if (executeResult.getGrantDataList() != null)
		{
            count = executeResult.getGrantDataList().size();
        }
        Assert.assertEquals(grantCount, count);
    }

    /**
     * Execute entitlement with conditions and ensure expected grant is applicable.
     *
     * @param action action to perform (see {@link com.hybris.services.entitlements.api.Actions})
     * @param entitlementType Type of entitlement.
     * @param userId User id.
     * @param conditions conditions list.
     * @param grantSourceId the grantSourceId.
     */
    public void executeEntitlementWithConditionAndHasGrantSourceId(
            final String action,
            final String entitlementType,
            final String userId,
            final String conditions,
            final String grantSourceId)
    {
        final List<CriterionData> criteriaList = new ArrayList<>();
		if (conditions != null)
		{
			for (final String condition : splitParameters(conditions))
			{
				criteriaList.add(parseCondition(condition, CriterionData.class));
			}
		}

        final ExecuteResult executeResult = getEntitlementFacade().execute(action,
                userId,
                entitlementType,
                criteriaList,
                true
        );
        for (GrantData result : executeResult.getGrantDataList())
        {
            if (grantSourceId.equals(result.getGrantSourceId()))
            {
                return;
            }
        }

        Assert.fail("Grant was not found");
    }
}
