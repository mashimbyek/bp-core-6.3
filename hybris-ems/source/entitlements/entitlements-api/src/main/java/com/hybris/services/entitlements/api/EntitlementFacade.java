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
package com.hybris.services.entitlements.api;


import com.hybris.services.entitlements.api.exceptions.ServiceException;
import com.hybris.services.entitlements.api.exceptions.ValidationException;
import com.hybris.services.entitlements.api.status.Status;
import com.hybris.services.entitlements.condition.ConditionData;
import com.hybris.services.entitlements.condition.CriterionData;

import java.util.List;

/**
 * Entitlement service operations.
 */
public interface EntitlementFacade
{
	/**
	 * Grant entitlement to user.
	 *
	 * @param grantData entitlement definition
	 * @return created grant
	 * @throws IllegalArgumentException if incorrect parameters passed
	 * @throws ValidationException if grantData is invalid (see exception message for details)
	 * @throws ServiceException if creation of service was failed. One of possible reasons is accidental duplicating
	 * grant ids. Then you can call the method again to give it a shot to generate another id.
	 */
	GrantData createGrant(GrantData grantData) throws ValidationException, ServiceException;

	/**
	 * Perform given action on user's entitlements.
	 *
	 * @param action action to perform. See {@link Actions} for possible options.
	 * @param userId user identifier
	 * @param entitlementType type of entitlement
	 * @param criteria execution criteria
	 * @param details if true - return list of effective grants.
	 * @return true if user is entitled
	 * @throws ValidationException if criteria have any malformed items
	 */
	ExecuteResult execute(String action, final String userId, final String entitlementType,
			final List<CriterionData> criteria, boolean details)
			throws ValidationException;

	/**
	 * Perform given action on user's entitlements.
	 *
	 * @param action action to perform. Only check is supported yet.
	 * @param userId user identifier
	 * @param executeManyDataList the list of executeManyData
	 * @param details if true - return list of effective grants.
	 * @return results of action in the same order as executeManyDataList.
	 * @throws ValidationException if executeManyDataList was not validated
	 */
	ExecuteManyResult execute(String action, String userId, List<ExecuteManyData> executeManyDataList, Boolean details)
	        throws ValidationException;

	/**
	 * Get effective user grants by filter.
	 *
	 * @param userId user identifier
	 * @param entitlementType type of entitlement. Omit to get all user entitlements.
	 * @param grantSource the reason of grant. Omit to do not take the reason into account. Must be null if entitlementType is null.
	 * @param grantSourceId external id. Omit to do not take it into account. Must be null if grantSource is null.
	 * @return list of found grants.
	 * @throws ValidationException if the filter is wrong
	 */
	List<GrantData> getGrants(final String userId, final String entitlementType,
			final String grantSource, final String grantSourceId) throws ValidationException;


	/**
	 * Revoke user grants by filter.
	 *
	 * @param userId user identifier
	 * @param entitlementType type of grant. Omit to revoke all user grants.
	 * @param grantSource the reason of grant. Omit to do not take the reason into account. Must be null if entitlementType is null.
	 * @param grantSourceId external id. Omit to do not take it into account. Must be null if grantSource is null.
	 * @throws ValidationException if the filter is wrong
	 */
	void revokeGrants(String userId, String entitlementType, String grantSource, String grantSourceId)
			throws ValidationException;

	/**
	 * Revoke grant by internal identifier.
	 *
	 * @param grantId identifier
	 * @throws ObjectNotFoundException if grant does not exits
	 */
	void revokeGrant(String grantId) throws ObjectNotFoundException;

    /**
     * Update existing grant.
     *
	 * @param grantId the id of grant to update conditions
	 * @param conditionDataList the list of conditions to modify
     * @return updated grant
     */
    GrantData updateConditions(final String grantId, final List<ConditionData> conditionDataList);

	/**
	 * Delete grant condition(s).
	 *
	 * @param grantId grant id
	 * @param conditionType type of condition to remove. Null to delete all grant conditions
	 * @return updated grant
	 */
	GrantData deleteConditions(String grantId, String conditionType);

    /**
     * Updates grant status data.
     *
     * @param grantId identifier
     * @param status new status.
     * @return GrantData
     */
	GrantData updateGrantStatus(String grantId, Status status);

	/**
	 * Create dynamic grant property.
	 *
	 * @param grantId grant to add property to
	 * @param name name of new property
	 * @param value value of the property
	 * @return updated grant of null if the property already exists
	 * @throws ValidationException if property is exist
	 */
	GrantData createGrantProperty(String grantId, String name, String value) throws ValidationException;

	/**
	 * Remove dynamic grant property.
	 *
	 * @param grantId grant id
	 * @param name property to remove
	 * @return updated grant
	 * @throws ObjectNotFoundException if grant does not exits
	 */
	GrantData deleteGrantProperty(String grantId, String name) throws ObjectNotFoundException;

    /**
     * Update existing dynamic grant property.
     *
     * @param grantId identifier
     * @param key name of property
     * @param value value of property
     * @return updated grant or null if such property has already benn created
	 * @throws ValidationException if property is exist
     */
    GrantData updateGrantProperty(String grantId, String key, String value) throws ValidationException;

	/**
	 * Add specified value to existing grant property's value. Can be negative.
	 *
	 * @param grantId identifier
	 * @param key name of property
	 * @param amountToAdd amount to add
	 * @return updated grant or null if such property has already benn created
	 * @throws ValidationException if property is exist
	 */
	GrantData addGrantProperty(final String grantId, final String key, final int amountToAdd) throws ValidationException;

	/**
	 * Get grant by its id.
	 *
	 * @param grantId the grant's id.
	 * @return found grant.
	 * @throws ObjectNotFoundException if grant does not exits
	 */
	GrantData getGrant(String grantId) throws ObjectNotFoundException;
}
