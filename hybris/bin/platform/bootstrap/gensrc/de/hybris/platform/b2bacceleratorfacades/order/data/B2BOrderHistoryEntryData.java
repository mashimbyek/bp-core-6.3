/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 25 Aug 2017 4:31:25 PM
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
package de.hybris.platform.b2bacceleratorfacades.order.data;

import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.user.data.PrincipalData;
import java.util.Date;

public  class B2BOrderHistoryEntryData  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>B2BOrderHistoryEntryData.b2bOrderData</code> property defined at extension <code>b2bacceleratorfacades</code>. */
		
	private OrderData b2bOrderData;

	/** <i>Generated property</i> for <code>B2BOrderHistoryEntryData.description</code> property defined at extension <code>b2bacceleratorfacades</code>. */
		
	private String description;

	/** <i>Generated property</i> for <code>B2BOrderHistoryEntryData.timeStamp</code> property defined at extension <code>b2bacceleratorfacades</code>. */
		
	private Date timeStamp;

	/** <i>Generated property</i> for <code>B2BOrderHistoryEntryData.previousOrderVersionData</code> property defined at extension <code>b2bacceleratorfacades</code>. */
		
	private OrderData previousOrderVersionData;

	/** <i>Generated property</i> for <code>B2BOrderHistoryEntryData.ownerData</code> property defined at extension <code>b2bacceleratorfacades</code>. */
		
	private PrincipalData ownerData;
	
	public B2BOrderHistoryEntryData()
	{
		// default constructor
	}
	
		
	
	public void setB2bOrderData(final OrderData b2bOrderData)
	{
		this.b2bOrderData = b2bOrderData;
	}

		
	
	public OrderData getB2bOrderData() 
	{
		return b2bOrderData;
	}
	
		
	
	public void setDescription(final String description)
	{
		this.description = description;
	}

		
	
	public String getDescription() 
	{
		return description;
	}
	
		
	
	public void setTimeStamp(final Date timeStamp)
	{
		this.timeStamp = timeStamp;
	}

		
	
	public Date getTimeStamp() 
	{
		return timeStamp;
	}
	
		
	
	public void setPreviousOrderVersionData(final OrderData previousOrderVersionData)
	{
		this.previousOrderVersionData = previousOrderVersionData;
	}

		
	
	public OrderData getPreviousOrderVersionData() 
	{
		return previousOrderVersionData;
	}
	
		
	
	public void setOwnerData(final PrincipalData ownerData)
	{
		this.ownerData = ownerData;
	}

		
	
	public PrincipalData getOwnerData() 
	{
		return ownerData;
	}
	


}