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
import com.hybris.services.entitlements.api.ObjectNotFoundException;
import com.hybris.services.entitlements.api.status.Status;
import com.hybris.services.entitlements.api.status.StatusData;
import com.hybris.services.entitlements.condition.ConditionData;

import java.util.List;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * Grant manipulating ATDD keywords.
 */
@Configurable
public class EntitlementsDetailsKeywordLibrary extends AbstractEntitlementsKeywordLibrary
{
    public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

    /**
     * Checks entitlement by filter.
     *
     * @param userId User id.
     * @param entitlementType Entitlement type.
     * @param grantSource Grant source.
     * @param grantId Grant id.
     * @param exists condition.
     */
    public void checkEntitlementByFilter(final String userId,
                                         final String entitlementType,
                                         final String grantSource,
                                         final String grantId,
                                         final boolean exists)
    {
        final List<GrantData> data =  getEntitlementFacade().getGrants(userId, entitlementType, grantSource, grantId);
        Assert.assertTrue((data.isEmpty() ^ exists));
    }

    /**
     * Checks entitlement status.
     *
     * @param grantId grant id
     * @param status Status
     */
    public void checkStatusOfEntitlement(final String grantId, final String status)
    {
		final GrantData grant = getEntitlementFacade().getGrant(grantId);
        Assert.assertTrue(grant.getStatus() == Status.valueOf(status));
    }

    /**
     * Checks entitlement number.
     *
	 * @param number expected count
     * @param userId user id
     */
    public void checkEntitlementsNumber(final int number, final String userId)
    {
        Assert.assertEquals(number, getEntitlementFacade().getGrants(userId, null, null, null).size());
    }

    /**
     * Updates status of entitlement.
     *
     * @param grantId id of grant
     * @param status Status
	 * @return grant status
     */
    public StatusData updateStatusOfEntitlement(final String grantId, final String status)
    {
        final GrantData grantData = getEntitlementFacade().updateGrantStatus(grantId, Status.valueOf(status));
        return new StatusData(grantData.getStatus());
    }

    /**
     * Update grant.
	 *
     * @param data new grant data
     */
    public void updateEntitlement(final GrantData data)
    {
        getEntitlementFacade().updateConditions(data.getId(), data.getConditions());
    }

	/**
	 * Remove all condition from given grant.
	 *
	 * @param grantId grant id
	 */
	public void deleteAllConditions(final String grantId)
	{
		getEntitlementFacade().deleteConditions(grantId, null);
	}

	/**
	 * Remove condition by type.
	 *
	 * @param grantId grant id
	 * @param type condition type
	 */
	public void deleteCondition(final String grantId, final String type)
	{
		getEntitlementFacade().deleteConditions(grantId, type);
	}

    /**
     * Get grant by id.
	 *
     * @param grantId the id of grant
	 * @param exists expected grant presence
	 * @return grant
     */
    public GrantData getEntitlement(final String grantId, final boolean exists)
    {
        try
        {
            final GrantData grantData = getEntitlementFacade().getGrant(grantId);
            Assert.assertTrue(exists);
            return grantData;
        }
        catch (final ObjectNotFoundException e)
        {
            Assert.assertFalse(exists);
            return null;
        }
    }

    /**
     * Updates entitlement property.
     *
     * @param entitlementId Id of entitlement
     * @param key property name
     * @param value property value
     * @return Updated GrantData
     */
    public GrantData updateEntitlementProperty(final String entitlementId, final String key, final String value)
    {
        return getEntitlementFacade().updateGrantProperty(entitlementId, key, value);
    }

    /**
     * Updates entitlement property.
     *
     * @param entitlementId Id of entitlement
     * @param key property name
     * @param value property value
     * @param expectedExceptionType type of expected exception, may be null if no exception is expected
     * @param expectedExceptionMessage message of expected exception, may be null if no exception is expected
     * @return Updated GrantData
     */
    public GrantData updateEntitlementProperty(final String entitlementId,
                                               final String key,
                                               final String value,
                                               final String expectedExceptionType,
                                               final String expectedExceptionMessage)
    {
        return executeAndExpectException(new ExpectExceptionCallback<GrantData>()
        {
            @Override
            public GrantData execute() throws Exception
            {
                return getEntitlementFacade().updateGrantProperty(entitlementId, key, value);
            }
        }, expectedExceptionType, expectedExceptionMessage);
    }

    /**
     * Checks entitlement property.
     *
     * @param entitlementId Id of entitlement
     * @param key property name
     * @param value property value
     * @return Updated GrantData
     */
    public GrantData checkEntitlementProperty(final String entitlementId, final String key, final String value)
    {
        final GrantData data = getEntitlementFacade().getGrant(entitlementId);

        Assert.assertTrue(value.compareTo(data.getProperty(key)) == 0);

        return data;
    }

	/**
	 * Add custom property to grant.
	 *
	 * @param grantId grant id
	 * @param key property name
	 * @param value property value
	 * @return updated grant
	 */
	public GrantData createEntitlementProperty(final String grantId, final String key, final String value)
	{
		return getEntitlementFacade().createGrantProperty(grantId, key, value);
	}

	/**
	 * Add custom property and expect exception.
	 *
	 * @param grantId grant id
	 * @param key property name
	 * @param value property value
	 * @param expectedExceptionType class of expected exception. Null if exceptions are not expected.
	 * @param expectedExceptionMessage exception message pattern
	 * @return grant
	 */
    public GrantData createEntitlementProperty(final String grantId,
                                               final String key,
                                               final String value,
                                               final String expectedExceptionType,
                                               final String expectedExceptionMessage)
    {
        return executeAndExpectException(new ExpectExceptionCallback<GrantData>()
        {
            @Override
            public GrantData execute() throws Exception
            {
                return getEntitlementFacade().createGrantProperty(grantId, key, value);
            }
        }, expectedExceptionType, expectedExceptionMessage);
    }

	/**
	 * Delete custom property from grant.
	 *
	 * @param grantId grant id
	 * @param key property name
	 * @return updated grant
	 */
	public GrantData deleteEntitlementProperty(final String grantId, final String key)
	{
		return getEntitlementFacade().deleteGrantProperty(grantId, key);
	}

	/**
	 * Delete custom property from grant and expect exception.
	 * @param grantId grant id
	 * @param key property name
	 * @param expectedExceptionType class of expected exception. Null to no exceptions are expected.
	 * @param expectedExceptionMessage exception message pattern
	 * @return grant
	 */
    public GrantData deleteEntitlementProperty(final String grantId, final String key,
                                               final String expectedExceptionType,
                                               final String expectedExceptionMessage)
    {
        return executeAndExpectException(new ExpectExceptionCallback<GrantData>()
        {
            @Override
            public GrantData execute() throws Exception
            {
                return getEntitlementFacade().deleteGrantProperty(grantId, key);
            }
        }, expectedExceptionType, expectedExceptionMessage);
    }

	/**
	 * Checks entitlement's condition property.
	 *
	 * @param entitlementId Id of entitlement
	 * @param type condition type
	 * @param key property name
	 * @param value property value
	 * @return Updated GrantData
	 */
	public GrantData assertConditionPropertyInEntitlement(
			final String entitlementId, final String type, final String key, final String value)
	{
		final GrantData data = getEntitlementFacade().getGrant(entitlementId);
		for (ConditionData conditionData : data.getConditions())
		{
			if(type.equals(conditionData.getType()))
			{
				Assert.assertTrue(value.compareTo(conditionData.getProperty(key)) == 0);
				break;
			}
		}
		return data;
	}
}
