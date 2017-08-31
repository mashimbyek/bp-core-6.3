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

import com.hybris.services.entitlements.api.ExecuteManyData;
import com.hybris.services.entitlements.api.ExecuteManyResult;
import com.hybris.services.entitlements.condition.ConditionData;
import com.hybris.services.entitlements.condition.CriterionData;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * Entitlement execution ATDD library.
 */
@Configurable
public class EntitlementsExecuteManyKeywordLibrary extends AbstractEntitlementsKeywordLibrary
{

    public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

    private static final String SIMPLE_TYPE = "simple";
    private static final String TIMEFRAME_TYPE = "timeframe";
	private static final String TIMEFRAME_EXECUTION_PARAMETER_TIME = "time";

    /**
     * Execute entitlement without condition.
     *
     * @param action action to perform (see {@link com.hybris.services.entitlements.api.Actions})
     * @param userId User id
	 * @param executeManyDataCollection execution conditions
	 * @return execution result
     */
    public ExecuteManyResult executeManyEntitlement(final String action,
                                                    final List<ExecuteManyData> executeManyDataCollection,
                                                    final String userId)
    {
        return this.executeManyEntitlement(action, executeManyDataCollection, userId, null, null);
    }

    /**
     * Execute entitlement without condition.
     *
     * @param action action to perform (see {@link com.hybris.services.entitlements.api.Actions})
     * @param userId User id.
     * @param expectedExceptionType type of expected exception, may be null if no exception is expected
     * @param expectedExceptionMessage message of expected exception, may be null if no exception is expected
	 * @param executeManyDataCollection execution parameters
	 * @return list of results
     */
    public ExecuteManyResult executeManyEntitlement(final String action,
                                                    final List<ExecuteManyData> executeManyDataCollection,
                                                    final String userId,
                                                    final String expectedExceptionType,
                                                    final String expectedExceptionMessage)
    {
        return executeAndExpectException(new ExpectExceptionCallback<ExecuteManyResult>()
		{
            @Override
            public ExecuteManyResult execute() throws Exception
			{
                return getEntitlementFacade().execute(action, userId, executeManyDataCollection, false);

            }
        }, expectedExceptionType, expectedExceptionMessage);
    }

	/**
	 * Check if result of grant execution is correct.
	 * @param executeManyResult the result is being checked
	 * @param offset index of grant to check
	 * @param resultFlag expected value
	 */
    public void assertExecuteManyResult(final ExecuteManyResult executeManyResult, final int offset, final boolean resultFlag)
	{
        Assert.assertTrue(executeManyResult.getExecuteResultList().get(offset).isResult() == resultFlag);
    }


	/**
	 * Construct execution data.
	 *
	 * @return empty execution data
	 */
    public ExecuteManyData createSimpleExecuteManyData()
	{
        final ExecuteManyData simpleExecuteManyData = new ExecuteManyData();
        simpleExecuteManyData.setEntitlementType(SIMPLE_TYPE);
        final List<CriterionData> simpleCriterionDataList = new ArrayList<>();
        simpleExecuteManyData.setCriterionDataList(simpleCriterionDataList);
        return simpleExecuteManyData;
    }

	/**
	 * Constructs execution data with single timeframe condition.
	 *
	 * @return execution data
	 */
    public ExecuteManyData createTimeframeExecuteManyData()
	{
        final ExecuteManyData timeframeExecuteManyData = new ExecuteManyData();
        timeframeExecuteManyData.setEntitlementType(TIMEFRAME_TYPE);
        final List<CriterionData> timeframeCriterionDataList = new ArrayList<>();
        final CriterionData timeframeCriterionData = new CriterionData();
        timeframeCriterionData.setType(TIMEFRAME_TYPE);
        timeframeCriterionData.setProperty(TIMEFRAME_EXECUTION_PARAMETER_TIME, "2005-01-01T10:20:30Z");
        timeframeCriterionDataList.add(timeframeCriterionData);
        timeframeExecuteManyData.setCriterionDataList(timeframeCriterionDataList);
        return timeframeExecuteManyData;
    }

	/**
	 * Constructs custom execution from given conditions.
	 *
	 * @param conditionRaw execution conditions
	 * @return execution data
	 */
    public ExecuteManyData createExecuteManyData(final String conditionRaw)
    {
        final ConditionData condition = parseCondition(conditionRaw, ConditionData.class);
        final ExecuteManyData executeManyData = new ExecuteManyData();
        executeManyData.setEntitlementType(condition.getType());

        final List<CriterionData> criterionDataList = new ArrayList<>();

        final CriterionData criterionData = new CriterionData();
        criterionData.setType(condition.getType());
        for (String propertyKey: condition.getProperties().keySet())
        {
            criterionData.setProperty(propertyKey, condition.getProperty(propertyKey));
        }

        criterionDataList.add(criterionData);
        executeManyData.setCriterionDataList(criterionDataList);
        return executeManyData;
    }
}
