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

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * Metered entitlement handling ATDD keywords.
 */
@Configurable
public class EntitlementsAddUsageKeywordLibrary extends AbstractEntitlementsKeywordLibrary
{
    public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

	private static final String REMAINING_QUANTITY = "remainingQuantity";

	/**
	 * Track usage of metered entitlement.
	 *
	 * @param grantId grant id
	 * @param amountToAdd number of used units
	 * @return updated grant
	 */
    public GrantData addUsage(final String grantId, final int amountToAdd)
    {
        return addUsage(grantId, amountToAdd, null, null);
    }

    /**
     * Track usage of metered entitlement and expect exception.
     *
     * @param grantId grant id
     * @param amountToAdd number of used units
     * @param expectedExceptionType type of expected exception, may be null if no exception is expected
     * @param expectedExceptionMessage message of expected exception, may be null if no exception is expected
     * @return grant
     */
    public GrantData addUsage(final String grantId,
                              final int amountToAdd,
                              final String expectedExceptionType,
                              final String expectedExceptionMessage)
    {
        return executeAndExpectException(new ExpectExceptionCallback<GrantData>()
		{
            @Override
            public GrantData execute() throws Exception
			{
                return getEntitlementFacade().addGrantProperty(grantId, REMAINING_QUANTITY, amountToAdd);
            }
        }, expectedExceptionType, expectedExceptionMessage);
    }

	/**
	 * Check whether grant has correct amount of remaining units.
	 *
	 * @param grantId grant id
	 * @param amountToAdd expected number of units
	 */
    public void assertUsedQuantity(final String grantId, final int amountToAdd)
	{
        final GrantData grantData = getEntitlementFacade().getGrant(grantId);
        final int remainingQuantity = Integer.valueOf(grantData.getProperty(REMAINING_QUANTITY));
        Assert.assertTrue(remainingQuantity == amountToAdd);
    }
}
