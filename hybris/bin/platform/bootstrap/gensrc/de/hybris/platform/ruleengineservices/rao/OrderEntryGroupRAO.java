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

import de.hybris.platform.ruleengineservices.rao.OrderEntryGroupRAO;

public  class OrderEntryGroupRAO  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>OrderEntryGroupRAO.rootEntryGroup</code> property defined at extension <code>ruleengineservices</code>. */
		
	private OrderEntryGroupRAO rootEntryGroup;

	/** <i>Generated property</i> for <code>OrderEntryGroupRAO.entryGroupId</code> property defined at extension <code>ruleengineservices</code>. */
		
	private String entryGroupId;
	
	public OrderEntryGroupRAO()
	{
		// default constructor
	}
	
		
	
	public void setRootEntryGroup(final OrderEntryGroupRAO rootEntryGroup)
	{
		this.rootEntryGroup = rootEntryGroup;
	}

		
	
	public OrderEntryGroupRAO getRootEntryGroup() 
	{
		return rootEntryGroup;
	}
	
		
	
	public void setEntryGroupId(final String entryGroupId)
	{
		this.entryGroupId = entryGroupId;
	}

		
	
	public String getEntryGroupId() 
	{
		return entryGroupId;
	}
	

	@Override
	public boolean equals(final Object o)
	{
	
		if (o == null) return false;
		if (o == this) return true;

		try
		{
			final OrderEntryGroupRAO other = (OrderEntryGroupRAO) o;
			return new org.apache.commons.lang.builder.EqualsBuilder()
			.append(getRootEntryGroup(), other.getRootEntryGroup()) 
			.append(getEntryGroupId(), other.getEntryGroupId()) 
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
		.append(getRootEntryGroup()) 
		.append(getEntryGroupId()) 
		.toHashCode();
	}

}