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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Settings for load phase of performance testing.
 */
@XmlRootElement
public class LoadDataSettings
{
	/**
	 * Order number of user’s audience.
	 */
	private int audienceId;
	/**
	 * Order number of the first user.
	 */
	private int userOffset;
	/**
	 * Number of loaded users.
	 */
	private int totalUsers;
	/**
	 * Collection of grants.
	 */
	private List<GrantData> grants = new ArrayList<>();

	public int getTotalUsers()
	{
		return totalUsers;
	}
	public void setTotalUsers(final int totalUsers)
	{
		this.totalUsers = totalUsers;
	}
	public int getAudienceId()
	{
		return audienceId;
	}

	public void setAudienceId(final int audienceId)
	{
		this.audienceId = audienceId;
	}
	public List<GrantData> getGrants()
	{
		return grants;
	}

	public void setGrants(final List<GrantData> grants)
	{
		this.grants = grants;
	}

	public int getUserOffset()
	{
		return userOffset;
	}

	public void setUserOffset(final int userOffset)
	{
		this.userOffset = userOffset;
	}
}
