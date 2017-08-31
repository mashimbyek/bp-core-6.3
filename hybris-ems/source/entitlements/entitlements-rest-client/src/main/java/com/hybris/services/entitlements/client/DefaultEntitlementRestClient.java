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
package com.hybris.services.entitlements.client;

import com.hybris.commons.client.GenericRestClient;
import com.hybris.commons.client.RestCallBuilder;
import com.hybris.commons.client.RestResponseException;
import com.hybris.services.entitlements.api.exceptions.ValidationException;
import com.hybris.services.entitlements.api.EntitlementFacade;
import com.hybris.services.entitlements.api.ExecuteManyData;
import com.hybris.services.entitlements.api.ExecuteManyResult;
import com.hybris.services.entitlements.api.ExecuteResult;
import com.hybris.services.entitlements.api.GrantData;
import com.hybris.services.entitlements.api.ListOfConditionData;
import com.hybris.services.entitlements.api.ListOfCriterionData;
import com.hybris.services.entitlements.api.ListOfExecuteManyData;
import com.hybris.services.entitlements.api.ListOfGrantData;
import com.hybris.services.entitlements.api.ObjectNotFoundException;
import com.hybris.services.entitlements.api.PropertyData;
import com.hybris.services.entitlements.api.UnprocessableEntityException;
import com.hybris.services.entitlements.api.status.StatusData;
import com.hybris.services.entitlements.condition.ConditionData;
import com.hybris.services.entitlements.condition.CriterionData;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Client implementation for entitlement management.
 */
public class DefaultEntitlementRestClient extends GenericRestClient implements EntitlementFacade
{
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultEntitlementRestClient.class);

	private static final String USER_ID = "userId";
	private static final String ENTITLEMENT_TYPE = "entitlementType";
	private static final String GRANT_SOURCE = "grantSource";
	private static final String GRANT_SOURCE_ID = "grantSourceId";
	private static final String ACTION = "action";
	private static final String DETAILS = "details";
    private static final String HYBRIS_TENANT_ID_HEADER = "X-tenantId";
    private static final String HYBRIS_TENANT_ID_VALUE = "single";
	private static final String GRANT_PROPERTY_PATH = "/grants/%s/properties/%s";

    private String mediaType;

	@Override
	public GrantData createGrant(final GrantData data)
	{
        LOGGER.debug("Creating grant for user'" + data.getUserId() + "'.");
		try
		{
            return this.buildCall("/grants").post(GrantData.class, data).result();
		}
		catch (final RestResponseException e)
		{
            return e.unwrap(ValidationException.class, ObjectNotFoundException.class, UnprocessableEntityException.class);
		}
	}

	@Override
	public ExecuteResult execute(
			final String action,
			final String userId,
			final String entitlementType,
			final List<CriterionData> criterionData,
			final boolean details)
	{
		LOGGER.debug("Executing grant with "
				+ ACTION + "'" + action + "', "
				+ USER_ID + "'" + userId + "', "
				+ ENTITLEMENT_TYPE + "'" + entitlementType + "', "
				+ DETAILS + "'" + details + "'."
		);
		try
		{
			return this.buildCall("/grants/userId/%s/execute", userId).
					queryParam(ACTION, action).
					queryParam(ENTITLEMENT_TYPE, entitlementType).
					queryParam(DETAILS, Boolean.toString(details))
					.put(ExecuteResult.class, new ListOfCriterionData(criterionData))
					.result();
		}
		catch (final RestResponseException e)
		{
            return e.unwrap(ValidationException.class, ObjectNotFoundException.class, UnprocessableEntityException.class);
		}
	}

	@Override
	public ExecuteManyResult execute(
			final String action,
			final String userId,
			final List<ExecuteManyData> executeManyDataList,
			final Boolean details)
	{
		LOGGER.debug("Executing many grants with "
				+ ACTION + "'" + action + "', "
				+ USER_ID + "'" + userId + "', "
				+ DETAILS + "'" + details + "'."
		);
		try
		{
			return this.buildCall("/grants/userId/%s/executeMany", userId).
					queryParam(ACTION, action).
					queryParam(DETAILS, Boolean.toString(details))
					.put(ExecuteManyResult.class, new ListOfExecuteManyData(executeManyDataList))
					.result();
		}
		catch (final RestResponseException e)
		{
            return e.unwrap(ValidationException.class, ObjectNotFoundException.class, UnprocessableEntityException.class);
		}
	}

	@Override
	public List<GrantData> getGrants(final String userId, final String entitlementType,
			final String grantSource, final String grantSourceId)
	{
		LOGGER.debug("Getting grants by "
				+ USER_ID + "'" + userId + "', "
				+ ENTITLEMENT_TYPE + "'" + entitlementType + "', "
				+ GRANT_SOURCE + "'" + grantSource + "', "
				+ GRANT_SOURCE_ID + "'" + grantSourceId + "'."
		);
		try
		{
			final RestCallBuilder callBuilder = this.buildCall("/grants/userId/%s", userId);
            addParamIfExists(callBuilder, ENTITLEMENT_TYPE, entitlementType);
            addParamIfExists(callBuilder, GRANT_SOURCE, grantSource);
            addParamIfExists(callBuilder, GRANT_SOURCE_ID, grantSourceId);
            return callBuilder.get(ListOfGrantData.class)
                    .result()
                    .getGrantDataList();
		}
		catch (final RestResponseException e)
		{
            return e.unwrap(ValidationException.class, ObjectNotFoundException.class, UnprocessableEntityException.class);
		}
	}

	@Override
	public GrantData getGrant(final String grantId)
	{
		LOGGER.debug("Getting grant by "
				+ "grantId" + "'" + grantId + "'."
		);
		try
		{
			return this.buildCall("/grants/%s", grantId).get(GrantData.class).result();
		}
		catch (final RestResponseException e)
		{
            return e.unwrap(ValidationException.class, ObjectNotFoundException.class, UnprocessableEntityException.class);
		}
	}

	@Override
	public void revokeGrants(final String userId, final String entitlementType,
			final String grantSource, final String grantSourceId)
	{
		LOGGER.debug("Revoking grants by "
				+ USER_ID + "'" + userId + "', "
				+ GRANT_SOURCE + "'" + grantSource + "', "
				+ GRANT_SOURCE_ID + "'" + grantSourceId + "'."
		);
		try
		{
			final RestCallBuilder callBuilder = this.buildCall("/grants/userId/%s", userId);
            addParamIfExists(callBuilder, ENTITLEMENT_TYPE, entitlementType);
            addParamIfExists(callBuilder, GRANT_SOURCE, grantSource);
            addParamIfExists(callBuilder, GRANT_SOURCE_ID, grantSourceId);
            callBuilder.delete().result();
		}
		catch (final RestResponseException e)
		{
            e.unwrap(ValidationException.class, ObjectNotFoundException.class, UnprocessableEntityException.class);
		}
	}

	@Override
	public void revokeGrant(final String grantId)
	{
		LOGGER.debug("Revoking grant by "
				+ "grantId" + "'" + grantId + "'."
		);
		try
		{
            this.buildCall("/grants/%s", grantId).delete().result();
		}
		catch (final RestResponseException e)
		{
            e.unwrap(ValidationException.class, ObjectNotFoundException.class, UnprocessableEntityException.class);
		}
	}

    @Override
	public GrantData updateConditions(final String grantId, final List<ConditionData> conditionDataList)
	{
		LOGGER.debug("Updating conditions by "
				+ "grantId" + "'" + grantId + "'."
		);
		try
		{
			return this.buildCall("/grants/%s/conditions", grantId)
					.post(GrantData.class, new ListOfConditionData(conditionDataList))
					.result();
		}
		catch (final RestResponseException e)
		{
            return e.unwrap(ValidationException.class, ObjectNotFoundException.class, UnprocessableEntityException.class);
		}
    }

	@Override
	public GrantData deleteConditions(final String grantId, final String conditionType)
	{
		LOGGER.debug("Removing conditions of " + grantId + (conditionType != null ? " having type " + conditionType : ""));
		try
		{
			if (conditionType == null)
			{
				return buildCall("/grants/%s/conditions", grantId).delete(GrantData.class).result();
			}
			else
			{
				return buildCall("/grants/%s/conditions/%s", grantId, conditionType).delete(GrantData.class).result();
			}
		}
		catch (final RestResponseException e)
		{
			return e.unwrap(ValidationException.class, ObjectNotFoundException.class, UnprocessableEntityException.class);
		}
	}

	@Override
    public GrantData updateGrantStatus(final String grantId, final com.hybris.services.entitlements.api.status.Status status)
    {
		LOGGER.debug("Updating status by "
				+ "grantId" + "'" + grantId + "'."
		);
		try
		{
			return this.buildCall("/grants/%s/status", grantId)
					.put(GrantData.class, new StatusData(status))
					.result();
		}
		catch (final RestResponseException e)
		{
            return e.unwrap(ValidationException.class, ObjectNotFoundException.class, UnprocessableEntityException.class);
		}
    }

    @Override
    public GrantData updateGrantProperty(final String grantId, final String key, final String value)
    {
		LOGGER.debug("Updating grant property by "
				+ "grantId" + "'" + grantId + "' with "
				+ "key " + "'" + key + "', and "
				+ "value" + "'" + value + "'."
		);
		try
		{
			final PropertyData data = new PropertyData();
			data.setRelative(false);
			data.setValue(String.valueOf(value));
			return this.buildCall(GRANT_PROPERTY_PATH, grantId, key)
					.put(GrantData.class, data)
					.result();
		}
		catch (final RestResponseException e)
		{
            return e.unwrap(ValidationException.class, ObjectNotFoundException.class, UnprocessableEntityException.class);
		}
    }

	@Override
	public GrantData addGrantProperty(final String grantId, final String key, final int amountToAdd)
	{
		LOGGER.debug("Adding grant property by "
				+ "grantId" + "'" + grantId + "' with "
				+ "key " + "'" + key + "', and "
				+ "value" + "'" + amountToAdd + "'."
		);
		try
		{
			final PropertyData data = new PropertyData();
			data.setRelative(true);
			data.setValue(String.valueOf(amountToAdd));
			return this.buildCall(GRANT_PROPERTY_PATH, grantId, key)
					.put(GrantData.class, data)
					.result();
		}
		catch (final RestResponseException e)
		{
            return e.unwrap(ValidationException.class, ObjectNotFoundException.class, UnprocessableEntityException.class);
		}
	}

	@Override
	public GrantData createGrantProperty(final String grantId, final String key, final String value)
	{
		LOGGER.debug("Creating property by grantId" + "'" + grantId
				+ "' with key " + "'" + key + "', and value" + "'" + value + "'.");
		try
		{
			return this.buildCall(GRANT_PROPERTY_PATH, grantId, key)
					.post(GrantData.class, value)
					.result();
		}
		catch (final RestResponseException e)
		{
			return e.unwrap(ValidationException.class, ObjectNotFoundException.class, UnprocessableEntityException.class);
		}
	}

	@Override
	public GrantData deleteGrantProperty(final String grantId, final String key)
	{
		LOGGER.debug("Creating property by grantId" + "'" + grantId + "' with key " + "'" + key);
		try
		{
			return this.buildCall(GRANT_PROPERTY_PATH, grantId, key)
					.delete(GrantData.class)
					.result();
		}
		catch (final RestResponseException e)
		{
			return e.unwrap(ValidationException.class, ObjectNotFoundException.class, UnprocessableEntityException.class);
		}
	}

    /**
     *  Build call with common RestClient HTTP headers.
     */
	private RestCallBuilder buildCall(final String path, final Object... params)
	{
        return this.call(path, params)
                // add headers
                .accept(mediaType)
                .type(mediaType)
                .header(HYBRIS_TENANT_ID_HEADER, HYBRIS_TENANT_ID_VALUE);
    }

    /** Add query parameter to {@link com.hybris.commons.client.RestCallBuilder} if value is not null.
     */
    private RestCallBuilder addParamIfExists(final RestCallBuilder rcb, final String key, final String value)
    {
        // It  would be much better to have this logic in RestCallBuilder.queryParam
        if (value != null)
        {
            rcb.queryParam(key, value);
        }
        return rcb;
    }

    public void setMediaType(final String mediaType)
    {
        this.mediaType = mediaType;
    }
}
