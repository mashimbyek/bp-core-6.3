/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 25 Aug 2017 4:31:26 PM
 * ----------------------------------------------------------------
 *
 * [y] hybris Platform
 * 
 * Copyright (c) 2000-2016 SAP SE
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information of SAP 
 * Hybris ("Confidential Information"). You shall not disclose such 
 * Confidential Information and shall use it only in accordance with the 
 * terms of the license agreement you entered into with SAP Hybris.
 */
package de.hybris.platform.ruleengineservices.rao;

import de.hybris.platform.ruleengineservices.rao.AbstractOrderRAO;
import de.hybris.platform.ruleengineservices.rao.UserGroupRAO;
import java.util.Set;

public  class UserRAO  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>UserRAO.id</code> property defined at extension <code>ruleengineservices</code>. */
		
	private String id;

	/** <i>Generated property</i> for <code>UserRAO.orders</code> property defined at extension <code>ruleengineservices</code>. */
		
	private Set<AbstractOrderRAO> orders;

	/** <i>Generated property</i> for <code>UserRAO.groups</code> property defined at extension <code>ruleengineservices</code>. */
		
	private Set<UserGroupRAO> groups;
	
	public UserRAO()
	{
		// default constructor
	}
	
		
	
	public void setId(final String id)
	{
		this.id = id;
	}

		
	
	public String getId() 
	{
		return id;
	}
	
		
	
	public void setOrders(final Set<AbstractOrderRAO> orders)
	{
		this.orders = orders;
	}

		
	
	public Set<AbstractOrderRAO> getOrders() 
	{
		return orders;
	}
	
		
	
	public void setGroups(final Set<UserGroupRAO> groups)
	{
		this.groups = groups;
	}

		
	
	public Set<UserGroupRAO> getGroups() 
	{
		return groups;
	}
	

	@Override
	public boolean equals(final Object o)
	{
	
		if (o == null) return false;
		if (o == this) return true;

		try
		{
			final UserRAO other = (UserRAO) o;
			return new org.apache.commons.lang.builder.EqualsBuilder()
			.append(getId(), other.getId()) 
			.isEquals();
		} 
		catch (ClassCastException c)
		{
			return false;
		}
	}
	
	@Override
	public int hashCode()
	{
		return new org.apache.commons.lang.builder.HashCodeBuilder()
		.append(getId()) 
		.toHashCode();
	}

}