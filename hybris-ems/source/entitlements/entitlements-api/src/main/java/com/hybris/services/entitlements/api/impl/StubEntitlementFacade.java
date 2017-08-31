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
package com.hybris.services.entitlements.api.impl;

import com.hybris.services.entitlements.api.EntitlementFacade;
import com.hybris.services.entitlements.api.ExecuteManyData;
import com.hybris.services.entitlements.api.ExecuteManyResult;
import com.hybris.services.entitlements.api.ExecuteResult;
import com.hybris.services.entitlements.api.GrantData;
import com.hybris.services.entitlements.api.ObjectNotFoundException;
import com.hybris.services.entitlements.api.exceptions.ServiceException;
import com.hybris.services.entitlements.api.exceptions.ValidationException;
import com.hybris.services.entitlements.api.status.Status;
import com.hybris.services.entitlements.condition.ConditionData;
import com.hybris.services.entitlements.condition.CriterionData;
import org.springframework.dao.DuplicateKeyException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Default implementation of the Entitlement facade {@link com.hybris.services.entitlements.api.EntitlementFacade}
 */
public class StubEntitlementFacade implements EntitlementFacade
{

    @Override
    public GrantData createGrant(final GrantData grantData) throws ValidationException, ServiceException, DuplicateKeyException
	{
        return grantData;
    }

    @Override
    public ExecuteResult execute(final String action, final String userId, final String entitlementType,
			final List<CriterionData> criteria, final boolean details) throws ValidationException
	{
        final GrantData grantData = new GrantData();
        grantData.setUserId(userId);
        grantData.setEntitlementType(entitlementType);

        final ArrayList<GrantData> grantDataList = new ArrayList<>();
        grantDataList.add(grantData);

        final ExecuteResult result = new ExecuteResult(true);
        result.setGrantDataList(grantDataList);
        return result;
    }

    @Override
    public ExecuteManyResult execute(final String action, final String userId,
			final List<ExecuteManyData> executeManyDataList, final Boolean details) throws ValidationException
	{
        return new ExecuteManyResult();
    }

    @Override
    public List<GrantData> getGrants(final String userId, final String entitlementType,
			final String grantSource, final String grantSourceId) throws ValidationException
	{
        return Collections.emptyList();
    }

    @Override
    public void revokeGrants(final String userId, final String entitlementType,
			final String grantSource, final String grantSourceId) throws ValidationException
	{
    }

    @Override
    public void revokeGrant(final String grantId) throws ObjectNotFoundException
	{
    }

    @Override
    public GrantData updateConditions(final String grantId, final List<ConditionData> conditionDataList)
	{
        final GrantData result = new GrantData();
        result.setId(grantId);
        result.setConditions(conditionDataList);
        return result;
    }

    @Override
    public GrantData deleteConditions(final String grantId, final String conditionType)
	{
        final GrantData result = new GrantData();
        result.setId(grantId);
        return result;
    }

    @Override
    public GrantData updateGrantStatus(final String grantId, final Status status)
	{
        final GrantData result = new GrantData();
        result.setId(grantId);
        result.setStatus(status);
        return result;
    }

    @Override
    public GrantData createGrantProperty(final String grantId, final String name, final String value) throws ValidationException
	{
        final GrantData result = new GrantData();
        result.setId(grantId);
        return result;
    }

    @Override
    public GrantData deleteGrantProperty(final String grantId, final String name) throws ObjectNotFoundException
	{
        final GrantData result = new GrantData();
        result.setId(grantId);
        return result;
    }

    @Override
    public GrantData updateGrantProperty(final String grantId, final String key, final String value) throws ValidationException
	{
        final GrantData result = new GrantData();
        result.setId(grantId);
        return result;
    }

    @Override
    public GrantData addGrantProperty(final String grantId, final String key, final int amountToAdd) throws ValidationException
	{
        final GrantData result = new GrantData();
        result.setId(grantId);
        return result;
    }

    @Override
    public GrantData getGrant(final String grantId) throws ObjectNotFoundException
	{
        final GrantData result = new GrantData();
        result.setId(grantId);
        return result;
    }
}
