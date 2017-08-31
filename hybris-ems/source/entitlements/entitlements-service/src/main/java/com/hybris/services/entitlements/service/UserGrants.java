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

import com.hybris.services.entitlements.domain.Grant;

import java.util.List;

/**
 * The UserGrants.
 */
public class UserGrants
{
	private boolean valid;
	private List<Grant> relatedGrants;

	/**
	 * The constructor.
	 *
	 * @param valid valif flag.
	 * @param relatedGrants related grants.
	 */
	public UserGrants(final boolean valid, final List<Grant> relatedGrants)
	{
		this.valid = valid;
		this.relatedGrants = relatedGrants;
	}

	public UserGrants(final List<Grant> relatedGrants)
	{
		this.valid = !relatedGrants.isEmpty();
		this.relatedGrants = relatedGrants;
	}

	public boolean isValid()
	{
		return valid;
	}

	public List<Grant> getRelatedGrants()
	{
		return relatedGrants;
	}
}
