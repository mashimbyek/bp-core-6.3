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
 * Property handling keywords.
 */
@Configurable
public class EntitlementsAddGrantPropertyKeywordLibrary extends AbstractEntitlementsKeywordLibrary
{
    public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

    private static final String SIMPLE_TYPE = "simple";
    private static final String TIMEFRAME_TYPE = "timeframe";

	/**
	 * Increment numeric custom property of {@link GrantData}.
	 *
	 * @param grantId grant id
	 * @param key property name
	 * @param amountToAdd increment value
	 * @return resulting grant
	 */
    public GrantData addGrantProperty(final String grantId,
                                      final String key,
                                      final int amountToAdd)
    {
        return this.addGrantProperty(grantId, key, amountToAdd, null, null);
    }

    /**
     * Increment numeric custom property of {@link GrantData} and expect exception.
     *
     * @param grantId grant id
	 * @param key property name
     * @param amountToAdd increment value
     * @param expectedExceptionType type of expected exception, may be null if no exception is expected
     * @param expectedExceptionMessage message of expected exception, may be null if no exception is expected
	 * @return resulting grant
     */
    public GrantData addGrantProperty(final String grantId,
                                      final String key,
                                      final int amountToAdd,
                                      final String expectedExceptionType,
                                      final String expectedExceptionMessage)
    {
        return executeAndExpectException(new ExpectExceptionCallback<GrantData>()
        {
            @Override
            public GrantData execute() throws Exception
			{
                return getEntitlementFacade().addGrantProperty(grantId, key, amountToAdd);
            }
        }, expectedExceptionType, expectedExceptionMessage);
    }

	/**
	 * Ensure custom property has correct value.
	 * @param grantId grant id
	 * @param key property name
	 * @param expectedValue expected value of the property
	 */
	public void assertGrantPropertyQuantity(final String grantId, final String key, final int expectedValue)
	{
        final GrantData grantData = getEntitlementFacade().getGrant(grantId);
		try
		{
			int value = Integer.valueOf(grantData.getProperty(key));
			Assert.assertTrue(value == expectedValue);
		}
		catch (final NumberFormatException e)
		{
			Assert.assertTrue(false);
		}
    }
}
