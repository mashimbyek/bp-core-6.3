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
package com.hybris.services.entitlements.service;


import com.hybris.services.entitlements.api.ObjectNotFoundException;
import com.hybris.services.entitlements.api.status.Status;
import com.hybris.services.entitlements.domain.Condition;
import com.hybris.services.entitlements.domain.Criterion;
import com.hybris.services.entitlements.domain.Grant;

import java.util.List;

/**
 * Grant service.
 */
public interface GrantService
{
	/**
	 * Creates empty grant in database.
	 *
	 * @return brand new grant instance
	 */
	Grant newGrant();

	/**
	 * Revoke grants by filter.
	 *
	 * @param userId user id
	 * @param entitlementType type of grant
	 * @param grantSource the reason of grant
	 * @param grantSourceId grant id
	 * @return number of revoked items
	 */
	int revokeGrants(String userId, String entitlementType, String grantSource, String grantSourceId);

	/**
	 * Get grants by filter.
	 *
	 * @param userId user id
	 * @param entitlementType type of grant
	 * @param grantSource the reason of grant
	 * @param grantSourceId grant id
     * @param status grant status. Can be null (includes all statuses)
	 * @return list of grants that fit the criteria
	 */
	List<Grant> getGrants(
                                final String userId,
                                final String entitlementType,
                                final String grantSource,
                                final String grantSourceId,
                                final Status status);

	/**
	 * Create new condition.
	 *
	 * @return condition model
	 */
	Condition newCondition();

	/**
	 * Revoke single grant.
	 *
	 * @param id internal grant id
     * @throws ObjectNotFoundException when application could not found object with given id
	 */
	void revokeGrant(String id) throws ObjectNotFoundException;

	/**
	 * Returns Grant by its id.
	 * 
	 * @param id Grant id.
	 * @return Grant by its id.
     * @throws ObjectNotFoundException when application could not found object with given id
	 */
	Grant getGrant(String id) throws ObjectNotFoundException;

    /**
     * Updates given grant's conditions in database.
	 *
	 * @param id the id of grant to update conditions
	 * @param conditions new set of conditions
     * @return corresponding DB object
     * @throws ObjectNotFoundException when application could not found object with given id
     */
    Grant updateConditions(final String id, final List<Condition> conditions) throws ObjectNotFoundException;

	/**
	 * Updates grant status.
	 *
	 * @param id the id og grant to update status.
	 * @param status the new status.
	 * @return updated grant
     * @throws ObjectNotFoundException when application could not found object with given id
	 */
	Grant updateGrantStatus(String id, Status status) throws ObjectNotFoundException;

	/**
	 * Apply given action to appropriate grants.
	 *
	 * @param action one of supported {@link com.hybris.services.entitlements.api.Actions}
	 * @param userId id of user
	 * @param entitlementType type of user's entitlement
	 * @param criteria condition-specific criteria
	 * @param comparatorType the comparatorType
	 * @return list of grants affected by the action and entitlement status.
	 */
	UserGrants execute(String action, String userId, String entitlementType, List<Criterion> criteria, String comparatorType);
}


