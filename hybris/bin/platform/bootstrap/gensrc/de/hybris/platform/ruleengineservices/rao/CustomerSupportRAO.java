/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 25 Aug 2017 4:31:27 PM
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

public  class CustomerSupportRAO  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>CustomerSupportRAO.customerSupportAgentActive</code> property defined at extension <code>ruleengineservices</code>. */
		
	private Boolean customerSupportAgentActive;

	/** <i>Generated property</i> for <code>CustomerSupportRAO.customerEmulationActive</code> property defined at extension <code>ruleengineservices</code>. */
		
	private Boolean customerEmulationActive;
	
	public CustomerSupportRAO()
	{
		// default constructor
	}
	
		
	
	public void setCustomerSupportAgentActive(final Boolean customerSupportAgentActive)
	{
		this.customerSupportAgentActive = customerSupportAgentActive;
	}

		
	
	public Boolean getCustomerSupportAgentActive() 
	{
		return customerSupportAgentActive;
	}
	
		
	
	public void setCustomerEmulationActive(final Boolean customerEmulationActive)
	{
		this.customerEmulationActive = customerEmulationActive;
	}

		
	
	public Boolean getCustomerEmulationActive() 
	{
		return customerEmulationActive;
	}
	

	@Override
	public boolean equals(final Object o)
	{
	
		if (o == null) return false;
		if (o == this) return true;

		try
		{
			final CustomerSupportRAO other = (CustomerSupportRAO) o;
			return new org.apache.commons.lang.builder.EqualsBuilder()
			.append(getCustomerSupportAgentActive(), other.getCustomerSupportAgentActive()) 
			.append(getCustomerEmulationActive(), other.getCustomerEmulationActive()) 
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
		.append(getCustomerSupportAgentActive()) 
		.append(getCustomerEmulationActive()) 
		.toHashCode();
	}

}