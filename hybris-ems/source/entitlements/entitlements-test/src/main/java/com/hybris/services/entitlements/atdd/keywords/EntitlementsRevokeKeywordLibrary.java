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

import org.springframework.beans.factory.annotation.Configurable;

/**
 * Grant revoking ATDD keywords.
 */
@Configurable
public class EntitlementsRevokeKeywordLibrary extends AbstractEntitlementsKeywordLibrary
{
    public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

    /**
     * Revoke entitlement by filter.
     *
     * @param userId User id.
     * @param entitlementType Entitlement type.
     * @param grantSource Grant source.
     * @param grantSourceId Grant id.
     */
    public void revokeEntitlement(final String userId,
                                     final String entitlementType,
                                     final String grantSource,
                                     final String grantSourceId)
    {
        getEntitlementFacade().revokeGrants(userId, entitlementType, grantSource, grantSourceId);
    }

    /**
     * Revoke entitlement.
     * @param grantId Grant id.
     */
    public void revokeEntitlementByGrantId(final String grantId)
    {
        getEntitlementFacade().revokeGrant(grantId);
    }
}
