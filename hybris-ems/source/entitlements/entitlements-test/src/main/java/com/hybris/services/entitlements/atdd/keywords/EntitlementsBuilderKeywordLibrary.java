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

import com.hybris.services.entitlements.api.GrantData;
import com.hybris.services.entitlements.condition.ConditionData;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Configurable;


/**
 * Grant building ATDD Keywords.
 */
@Configurable
public class EntitlementsBuilderKeywordLibrary extends AbstractEntitlementsKeywordLibrary
{
    public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

    private static final String GRANT_SOURCE = "order";
    private static final String GRANT_SOURCE_ID = "order#1";

    /**
     * Creates entitlement.
     *
     * @param entitlementType  Type of entitlement.
     * @param userId User id.
     * @return Grant data.
     */
    public GrantData createEntitlement(final String entitlementType, final String userId)
    {
        return createGrandData(entitlementType, userId);
    }

    /**
     * Grant entitlement.
     *
     * @param data grant
	 * @return grant created in DB
     */
    public GrantData grantEntitlement(final GrantData data)
    {
        return getEntitlementFacade().createGrant(data);
    }

    /**
     * Grant entitlement.
     *
     * @param entitlementType Type of entitlement.
     * @param userId User id
     * @return Grant data
     */
    public GrantData grantEntitlementWithParams(final String entitlementType, final String userId)
    {
        final GrantData data = createGrandData(entitlementType, userId);

        return getEntitlementFacade().createGrant(data);
    }

	/**
	 * Grant entitlement with conditions.
	 *
	 * @param entitlementType entitlement type
	 * @param userId user id
	 * @param conditions condition list
	 * @return created grant
	 */
    public GrantData grantEntitlementWithParamsAndConditions(final String entitlementType,
                                                             final String userId,
                                                             final String conditions)
	{
        return this.grantEntitlementWithParamsAndConditions(entitlementType, userId, conditions, null, null);
    }

    /**
     * Grant entitlement.
     *
     * @param entitlementType Type of entitlement.
     * @param userId User id
     * @param conditions Conditions
     * @param expectedExceptionType type of expected exception, may be null if no exception is expected
     * @param expectedExceptionMessage message of expected exception, may be null if no exception is expected
     * @return Grand data
     */
    public GrantData grantEntitlementWithParamsAndConditions(final String entitlementType,
                                                             final String userId,
                                                             final String conditions,
			                                                 final String expectedExceptionType,
			                                                 final String expectedExceptionMessage)
    {
        return executeAndExpectException(new ExpectExceptionCallback<GrantData>()
        {
            @Override
            public GrantData execute() throws Exception
            {
                final GrantData data = createGrandData(entitlementType, userId);

                for (final String condition : splitParameters(conditions))
                {
                    data.addCondition(
                            parseCondition(condition, ConditionData.class)
                    );
                }

                return getEntitlementFacade().createGrant(data);
            }
        }, expectedExceptionType, expectedExceptionMessage);
    }

    private GrantData createGrandData(final String entitlementType, final String userId)
    {
        final GrantData data = new GrantData();
        data.setEntitlementType(entitlementType);
        data.setUserId(userId);
        data.setGrantSource(GRANT_SOURCE);
        data.setGrantSourceId(GRANT_SOURCE_ID);

        return data;
    }


	/**
	 * Change grantEntitlement source value.
	 *
	 * @param entitlementData grantEntitlement data. Can be null
	 * @param grantSource new value
     * @return Grant data.
	 */
	public GrantData setGrantSource(final GrantData entitlementData, final String grantSource)
	{
        entitlementData.setGrantSource(grantSource);
		return  entitlementData;
	}

	/**
	 * Change grantEntitlement source id.
	 *
	 * @param entitlementData grantEntitlement data. Can be null
	 * @param grantSourceId new value
     * @return Grant data.
	 */
	public GrantData setGrantSourceId(final GrantData entitlementData, final String grantSourceId)
	{
        entitlementData.setGrantSourceId(grantSourceId);
        return  entitlementData;
	}

	/**
	 * Change grantEntitlement time.
	 *
	 * @param entitlementData grantEntitlement data. Can be null
	 * @param grantTime new value
     * @return Grant data.
	 */
	public GrantData setGrantTime(final GrantData entitlementData, final String grantTime)
	{
        entitlementData.setGrantTime(grantTime);
        return  entitlementData;
	}

    /**
     * Adds condition to GrantData.
     *
     * @param grant createGrant data
     * @param condition Condition
     * @return  Grant data.
     */
	public GrantData addCondition(final GrantData grant, final String condition)
	{
        grant.addCondition(parseCondition(condition, ConditionData.class));
        return grant;
	}

    /**
     * Removes all conditions from Grant.
     *
     * @param grant createGrant data
     * @return  Grant data.
     */
    public GrantData removeAllConditions(final GrantData grant)
    {
        grant.getConditions().clear();
        return grant;
    }

    /**
     * Generates random Id.
     * @return String GUID.
     */
    public String generateId()
    {
        return UUID.randomUUID().toString();
    }

}
