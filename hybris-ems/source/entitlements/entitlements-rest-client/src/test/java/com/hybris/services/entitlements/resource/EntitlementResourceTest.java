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
package com.hybris.services.entitlements.resource;

import com.hybris.commons.client.RestResponse;
import com.hybris.commons.client.RestResponseException;
import com.hybris.services.entitlements.api.GrantData;
import com.hybris.services.entitlements.client.DefaultEntitlementRestClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-rest-client-test-spring.xml")
public class EntitlementResourceTest
{
	@Autowired
	private DefaultEntitlementRestClient facade;

	@Test
	public void shouldNotRequireTenantHeaderForOptions() throws RestResponseException
	{
		checkOptionsMethod("/grants", "POST");

		// Next checks are impossible without a grant
		final GrantData grant = new GrantData();
		grant.setUserId(UUID.randomUUID().toString());
		grant.setGrantSource("shouldNotRequireTenantHeaderForOptions");
		grant.setGrantSourceId("1");
		grant.setEntitlementType("test");
		final String grantId = facade.createGrant(grant).getId();

		checkOptionsMethod("/grants/" + grantId, "GET", "DELETE");
		checkOptionsMethod("/grants/" + grantId + "/properties", "GET");
		checkOptionsMethod("/grants/" + grantId + "/properties/name", "GET", "DELETE", "PUT", "POST");
		checkOptionsMethod("/grants/" + grantId + "/status", "GET", "PUT");
		checkOptionsMethod("/grants/" + grantId + "/conditions", "POST", "DELETE");
		checkOptionsMethod("/grants/" + grantId + "/conditions/conditionType", "DELETE");
		checkOptionsMethod("/grants/userId/" + grant.getUserId() + "/execute", "PUT");
		checkOptionsMethod("/grants/userId/" + grant.getUserId() + "/executeMany", "PUT");
		checkOptionsMethod("/grants/userId/" + grant.getUserId(), "GET", "DELETE");
	}

	private void checkOptionsMethod(final String path, final String ... methods) throws RestResponseException
	{
		final RestResponse response = facade.call(path).options(String.class);
		List<Object> allowHeader = response.getResponse().getHeaders().get("Allow");
		if (allowHeader.size() == 1)
		{
			allowHeader = Arrays.asList(allowHeader.get(0).toString().split(",\\s*"));
		}
		assertNotNull("URI '" + path + "' returned no 'Allow' header", allowHeader);
		for (final String method : methods)
		{
			assertTrue("URI " + path + ": 'Allow' header does not contain '" + method + "' method: '"
					+ ArrayUtils.toString(allowHeader) + "'",
					allowHeader.contains(method));
		}
	}
}
