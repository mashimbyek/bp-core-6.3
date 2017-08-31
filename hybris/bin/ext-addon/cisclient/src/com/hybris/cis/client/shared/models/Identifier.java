/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.hybris.cis.client.shared.models;

/**
 * Identifier object to be attached to other objects.
 */
public class Identifier implements Identifiable
{
	private String id;

	public Identifier()
	{
		// default
	}

	/**
	 * Instantiates an identifier object.
	 * 
	 * @param id the id used to identify
	 */
	public Identifier(final String id)
	{
		super();
		this.id = id;
	}

	@Override
	public String getId()
	{
		return this.id;
	}

	@Override
	public void setId(final String id)
	{
		this.id = id;
	}

	@Override
	public String toString()
	{
		return "Identifier [id=" + this.id + "]";
	}

}
