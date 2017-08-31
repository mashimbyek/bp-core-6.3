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
package com.hybris.services.entitlements.rest.resources;


import com.hybris.services.entitlements.api.*;
import com.hybris.services.entitlements.api.codes.ResourceCode;
import com.hybris.services.entitlements.api.exceptions.ValidationException;
import com.hybris.services.entitlements.api.status.StatusData;
import com.hybris.services.entitlements.validation.ValidationExecutor;
import com.hybris.services.entitlements.validation.ValidationViolations;
import org.codehaus.enunciate.jaxrs.ResponseCode;
import org.codehaus.enunciate.jaxrs.StatusCodes;
import org.codehaus.enunciate.jaxrs.TypeHint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
/**
 * REST resource for grants.
 */
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@StatusCodes({
        @ResponseCode(code = ResourceCode.CODE_404, condition = "ERROR - Resource not found")
})
@Path("/grants")
public class EntitlementsResource
{

	@Autowired
	private ValidationExecutor validationExecutor;
	@Autowired
	private EntitlementFacade entitlementFacade;

    /**
     * Creates a grant.
     *
     * @param grantData Grant object including list of conditions
     * @return Same as request, but <i>Id</i> is added and current time assigned to grantTime if it was omitted.
     */
    @StatusCodes({
            @ResponseCode(code = ResourceCode.CODE_201, condition = "SUCCESS - Created : Grant has been created"),
            @ResponseCode(code = ResourceCode.CODE_412, condition = "ERROR - Create : Invalid input data")
    })
    @POST
    @TypeHint(GrantData.class)
    public Response createEntitlement(@RequestBody final GrantData grantData)
    {
       	final GrantData grantData1 = entitlementFacade.createGrant(grantData);
       	return Response.status(Response.Status.CREATED).entity(grantData1).build();
    }

    /**
     * Set new conditions for existing grant.
     *
     * @param grantId id of grant
     * @param listOfConditionData new list of conditions. It will overwrite the existing one.
     * @return modified grant
     */
    @StatusCodes({
            @ResponseCode(code = ResourceCode.CODE_200, condition = "SUCCESS - Updated : Grant has been updated"),
            @ResponseCode(code = ResourceCode.CODE_404, condition = "ERROR - Update : Grant does not exist"),
            @ResponseCode(code = ResourceCode.CODE_412, condition = "ERROR - Update : Invalid conditions")
    })
    @POST
    @Path("/{grantId}/conditions")
    @TypeHint(GrantData.class)
    public Response resetConditions(@PathParam("grantId") final String grantId,
			@RequestBody final ListOfConditionData listOfConditionData
	)
    {
        final GrantData grantData1 = entitlementFacade.updateConditions(grantId, listOfConditionData.getConditionDataList());
        return Response.status(Response.Status.CREATED).entity(grantData1).build();
    }

	/**
	 * Remove condition of given type from existing grant.
	 *
	 * @param grantId id of grant
	 * @param conditionType optional type of condition to remove
	 * @return updated grant
	 */
	@StatusCodes({
			@ResponseCode(code = ResourceCode.CODE_200, condition = "SUCCESS - Deleted : Conditions were successfully deleted"),
			@ResponseCode(code = ResourceCode.CODE_404, condition = "ERROR - Delete : Grant does not exist")
	})
	@DELETE
	@Path("/{grantId}/conditions/{conditionType}")
	@TypeHint(GrantData.class)
	public Response deleteConditions(
			@PathParam("grantId") final String grantId,
			@PathParam("conditionType") final String conditionType)
	{
		final GrantData grantData = entitlementFacade.deleteConditions(grantId, conditionType);
		return Response.status(Response.Status.OK).entity(grantData).build();
	}

	/**
	 * Remove conditions from existing grant.
	 *
	 * @param grantId id of grant
	 * @return updated grant
	 */
	@StatusCodes({
			@ResponseCode(code = ResourceCode.CODE_200, condition = "SUCCESS - Deleted : Conditions were successfully deleted"),
			@ResponseCode(code = ResourceCode.CODE_404, condition = "ERROR - Delete : Grant does not exist")
	})
	@DELETE
	@Path("/{grantId}/conditions")
	@TypeHint(GrantData.class)
	public Response deleteConditions(
			@PathParam("grantId") final String grantId)
	{
		final GrantData grantData = entitlementFacade.deleteConditions(grantId, null);
		return Response.status(Response.Status.OK).entity(grantData).build();
	}

	/**
     * Checks if a user has a grant.
     *
	 * @param action which action to perform on the grant
     * @param userId user id
     * @param entitlementType type of entitlement
     * @param details if grant details should be returned
     * @param listOfCriterionData execution criteria. Depend on set of conditions you expect to have in grants.
     * @return boolean result and optional list of affected grants. If the result is false,
	 * the list contains the most suitable candidates.
     */
    @StatusCodes({
            @ResponseCode(code = ResourceCode.CODE_200, condition = "SUCCESS - Executed : Execution done"),
			@ResponseCode(code = ResourceCode.CODE_412, condition = "ERROR - Execute : Invalid input data")
    })
    @PUT
	@Path("/userId/{userId}/execute")
    @TypeHint(ExecuteResult.class)
    public Response execute(
			@QueryParam("action") final String action,
			@PathParam("userId") final String userId,
			@QueryParam("entitlementType") final String entitlementType,
			@DefaultValue("false") @QueryParam("details") final Boolean details,
			@RequestBody final ListOfCriterionData listOfCriterionData)
    {
		final ExecuteResult executeResult = entitlementFacade.execute(
				action, userId, entitlementType, listOfCriterionData.getCriterionDataList(), details);
		return Response.status(Response.Status.OK).entity(executeResult).build();
    }

	/**
	 * Checks if a user has a grant.
	 *
	 * @param action which action to perform on the grant
	 * @param userId user id
	 * @param details if grant details should be returned
	 * @param listOfExecuteManyData entitlement types and execution criteria for each type.
	 * The criteria on set of conditions you expect to have in grants.
	 * @return boolean result and optional list of affected grants for each entitlement type.
	 * For the results which are false, the lists contain the most suitable candidates.
	 */
	@StatusCodes({
			@ResponseCode(code = ResourceCode.CODE_200, condition = "SUCCESS - Executed : Execution done"),
			@ResponseCode(code = ResourceCode.CODE_412, condition = "ERROR - Execute : Invalid input data")
	})
	@PUT
	@Path("/userId/{userId}/executeMany")
	@TypeHint(ExecuteManyResult.class)
	public Response executeMany(
			@QueryParam("action") final String action,
			@PathParam("userId") final String userId,
			@DefaultValue("false") @QueryParam("details") final Boolean details,
			@RequestBody final ListOfExecuteManyData listOfExecuteManyData)
	{
		final ExecuteManyResult executeResult = entitlementFacade.execute(
				action, userId, listOfExecuteManyData.getExecuteManyDataList(), details);
		return Response.status(Response.Status.OK).entity(executeResult).build();
	}

    /**
     * Get all user's grants of given entitlement type, grant source and grant source id.
     *
     * @param userId user id
     * @param entitlementType type of entitlement
     * @param grantSource reason of granting.
     * @param grantSourceId external granting event id.
     * @return list of user's grants
     */
    @StatusCodes({
            @ResponseCode(code = ResourceCode.CODE_200, condition = "SUCCESS - Get : OK"),
			@ResponseCode(code = ResourceCode.CODE_412, condition = "ERROR - Get : Invalid combination of parameters")
    })
    @TypeHint(ListOfGrantData.class)
    @GET
	@Path("/userId/{userId}")
    public Response getEntitlements(
            @PathParam("userId") final String userId,
            @QueryParam("entitlementType") final String entitlementType,
            @QueryParam("grantSource") final String grantSource,
            @QueryParam("grantSourceId") final String grantSourceId
    )
    {
		final ListOfGrantData listOfGrantData =
				new ListOfGrantData(entitlementFacade.getGrants(userId, entitlementType, grantSource, grantSourceId));
		return Response.ok().entity(listOfGrantData).build();
    }

	/**
	 * Get single grant by internal id.
	 *
	 * @param grantId grant id
	 * @return grant
	 */
	@StatusCodes({
			@ResponseCode(code = ResourceCode.CODE_200, condition = "SUCCESS - Get : Grant has been found."),
			@ResponseCode(code = ResourceCode.CODE_404, condition = "ERROR: Get : Grant does not exist.")
	})
	@GET
	@Path("/{grantId}")
    @TypeHint(GrantData.class)
    public Response getEntitlement(@PathParam("grantId") final String grantId)
	{
		final GrantData grantData = entitlementFacade.getGrant(grantId);
		return Response.ok().entity(grantData).build();
	}

    /**
     * Revoke user's entitlements of given type, grant source and grant source id.
     *
     * @param userId user id
	 * @param entitlementType type of entitlement
	 * @param grantSource reason of granting
	 * @param grantSourceId external granting event id
     */
    @StatusCodes({
            @ResponseCode(code = ResourceCode.CODE_200, condition = "SUCCESS - Revoked : Grants have been revoked.")
    })
    @DELETE
	@Path("/userId/{userId}")
    public void revokeGrants(
            @PathParam("userId") final String userId,
            @QueryParam("entitlementType") final String entitlementType,
            @QueryParam("grantSource") final String grantSource,
            @QueryParam("grantSourceId") final String grantSourceId
    )
    {
        entitlementFacade.revokeGrants(userId, entitlementType, grantSource,  grantSourceId);
    }

    /**
     * Revoke single grant by internal id.
     *
     * @param grantId grant id
     */
    @StatusCodes({
            @ResponseCode(code = ResourceCode.CODE_200, condition = "SUCCESS - Revoked : The grant has been revoked."),
			@ResponseCode(code = ResourceCode.CODE_404, condition = "ERROR - Revoke : Grant not found")
    })
    @DELETE
    @Path("/{grantId}")
    public void revoke(@PathParam("grantId") final String grantId)
    {
        entitlementFacade.revokeGrant(grantId);
    }


    /**
     * Get grant status. Status affects if grant is processed or skipped by methods
	 * {@link #executeMany(String, String, Boolean, com.hybris.services.entitlements.api.ListOfExecuteManyData)} and
	 * {@link #execute(String, String, String, Boolean, com.hybris.services.entitlements.api.ListOfCriterionData)}.
     *
     * @param grantId grant id
     * @return grant status
     */
    @StatusCodes({
            @ResponseCode(code = ResourceCode.CODE_200, condition = "SUCCESS - Status gotten."),
            @ResponseCode(code = ResourceCode.CODE_404, condition = "ERROR - Get status : Grant does not exist.")
    })
    @GET
    @Path("/{grantId}/status")
    @TypeHint(StatusData.class)
    public Response getGrantStatus(@PathParam("grantId") final String grantId)
    {
        final GrantData grant = entitlementFacade.getGrant(grantId);
		final StatusData data = new StatusData(grant.getStatus());
        return Response.status(Response.Status.OK).entity(data).build();
    }

    /**
     * Update grant status. Status affects if grant is processed or skipped by methods
	 * {@link #executeMany(String, String, Boolean, com.hybris.services.entitlements.api.ListOfExecuteManyData)} and
	 * {@link #execute(String, String, String, Boolean, com.hybris.services.entitlements.api.ListOfCriterionData)}.
     *
     * @param grantId grant id
     * @param status new status
     * @return updated grant
     */
    @StatusCodes({
            @ResponseCode(code = ResourceCode.CODE_200, condition = "SUCCESS - Updated : The grant status has been updated"),
            @ResponseCode(code = ResourceCode.CODE_404, condition = "ERROR - Update : Grant does not exist.")
    })
    @PUT
    @Path("/{grantId}/status")
    @TypeHint(GrantData.class)
    public Response updateGrantStatus(@PathParam("grantId") final String grantId, @RequestBody final StatusData status)
    {
        final GrantData result = entitlementFacade.updateGrantStatus(grantId, status.getStatus());
        return Response.status(Response.Status.OK).entity(result).build();
    }

	/**
	 * Get grant properties.
	 *
	 * @param grantId id of an existing grant
	 * @return list of grant properties
	 */
	@StatusCodes({
			@ResponseCode(code = ResourceCode.CODE_200, condition = "SUCCESS - Gotten"),
			@ResponseCode(code = ResourceCode.CODE_404, condition = "ERROR - Get : grant does not exist")
	})
	@GET
	@Path("/{grantId}/properties")
	@TypeHint(Map.class)
	public Response getProperties(@PathParam("grantId") final String grantId)
	{
		final GrantData grant = entitlementFacade.getGrant(grantId);
		return Response.status(Response.Status.OK).entity(grant.getProperties()).build();
	}

	/**
	 * Get single property of given grant.
	 *
	 * @param grantId id of an existing grant
	 * @param key property name
	 * @return current value of property
	 */
	@StatusCodes({
			@ResponseCode(code = ResourceCode.CODE_200, condition = "SUCCESS - Gotten"),
			@ResponseCode(code = ResourceCode.CODE_404, condition = "ERROR - Get : grant does not exist")
	})
	@GET
	@Path("/{grantId}/properties/{key}")
	@TypeHint(String.class)
	public Response getProperties(
			@PathParam("grantId") final String grantId,
			@PathParam("key") final String key)
	{
		final GrantData grant = entitlementFacade.getGrant(grantId);
		return Response.status(Response.Status.OK).entity(grant.getProperty(key)).build();
	}

	/**
	 * Delete single property of a grant.
	 *
	 * @param grantId id of an existing grant
	 * @param key property name
	 * @return the most recent value of the property
	 */
	@StatusCodes({
			@ResponseCode(code = ResourceCode.CODE_200, condition = "SUCCESS - Deleted"),
			@ResponseCode(code = ResourceCode.CODE_404, condition = "ERROR - Delete : grant does not exist")
	})
	@DELETE
	@Path("/{grantId}/properties/{key}")
	@TypeHint(Void.class)
	public Response removeProperty(
			@PathParam("grantId") final String grantId,
			@PathParam("key") final String key)
	{
		entitlementFacade.updateGrantProperty(grantId, key, null);
		return Response.status(Response.Status.OK).build();
	}

	/**
     * Update custom grant property.
	 * <p>
	 *     To increment value of given property by specified number set <i>relative</i> to true. Use negative numbers for decrement.
	 *     Increment/decrement works for numeric properties only.
	 * </p>
     *
     * @param grantId grant id
     * @param key property name
     * @param value new value
     * @return updated grant
     */
    @StatusCodes({
            @ResponseCode(code = ResourceCode.CODE_200,
					condition = "SUCCESS - Updated : The grant property has been updated"),
			@ResponseCode(code = ResourceCode.CODE_412,
					condition = "ERROR - Update : this value is not allowed or property does not exist"),
            @ResponseCode(code = ResourceCode.CODE_404,
					condition = "ERROR - Update : Grant does not exist.")
    })
    @PUT
    @Path("/{grantId}/properties/{key}")
    @TypeHint(GrantData.class)
    public Response putGrantProperty(
			@PathParam("grantId") final String grantId,
			@PathParam("key") final String key,
			@RequestBody final PropertyData value)
    {
		final GrantData result;
		validate(value);


		if (value.isRelative())
		{
			result = entitlementFacade.addGrantProperty(grantId, key, Integer.parseInt(value.getValue()));
		}
		else
		{
        	result = entitlementFacade.updateGrantProperty(grantId, key, value.getValue());
		}
        return Response.status(Response.Status.OK).entity(result).build();
    }

	private void validate(final PropertyData value)
	{
		final ValidationViolations errors = validationExecutor.validate(value);
		if (errors.hasErrors())
		{
			throw new ValidationException(errors.toString());
		}
	}

	/**
     * Set custom grant property.
     *
     * @param grantId grant id
     * @param key property name
     * @param value new value
     * @return updated grant
     */
    @StatusCodes({
            @ResponseCode(code = ResourceCode.CODE_200,
					condition = "SUCCESS - Updated : The grant property has been updated"),
			@ResponseCode(code = ResourceCode.CODE_412,
					condition = "ERROR - Update : this value is not allowed or property already exists"),
            @ResponseCode(code = ResourceCode.CODE_404,
					condition = "ERROR - Update : Grant does not exist.")
    })
    @POST
    @Path("/{grantId}/properties/{key}")
    @TypeHint(GrantData.class)
    public Response postGrantProperty(
			@PathParam("grantId") final String grantId,
			@PathParam("key") final String key,
			@RequestBody final String value)
    {
		final GrantData grant = entitlementFacade.createGrantProperty(grantId, key, value);
		return Response.status(Response.Status.CREATED).entity(grant).build();
    }

    @Required
    public void setEntitlementFacade(final EntitlementFacade entitlementFacade)
    {
        this.entitlementFacade = entitlementFacade;
    }

    @Required
    public void setValidationExecutor(final ValidationExecutor validationExecutor)
    {
        this.validationExecutor = validationExecutor;
    }

}


