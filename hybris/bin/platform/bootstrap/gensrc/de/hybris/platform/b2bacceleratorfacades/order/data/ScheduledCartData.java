/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 25 Aug 2017 4:31:24 PM
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

import de.hybris.platform.b2bacceleratorfacades.order.data.TriggerData;
import de.hybris.platform.commercefacades.order.data.CartData;
import java.util.Date;

public  class ScheduledCartData extends CartData 
{


	/** <i>Generated property</i> for <code>ScheduledCartData.active</code> property defined at extension <code>b2bacceleratorfacades</code>. */
		
	private boolean active;

	/** <i>Generated property</i> for <code>ScheduledCartData.triggerData</code> property defined at extension <code>b2bacceleratorfacades</code>. */
		
	private TriggerData triggerData;

	/** <i>Generated property</i> for <code>ScheduledCartData.firstOrderDate</code> property defined at extension <code>b2bacceleratorfacades</code>. */
		
	private Date firstOrderDate;

	/** <i>Generated property</i> for <code>ScheduledCartData.jobCode</code> property defined at extension <code>b2bacceleratorfacades</code>. */
		
	private String jobCode;
	
	public ScheduledCartData()
	{
		// default constructor
	}
	
		
	
	public void setActive(final boolean active)
	{
		this.active = active;
	}

		
	
	public boolean isActive() 
	{
		return active;
	}
	
		
	
	public void setTriggerData(final TriggerData triggerData)
	{
		this.triggerData = triggerData;
	}

		
	
	public TriggerData getTriggerData() 
	{
		return triggerData;
	}
	
		
	
	public void setFirstOrderDate(final Date firstOrderDate)
	{
		this.firstOrderDate = firstOrderDate;
	}

		
	
	public Date getFirstOrderDate() 
	{
		return firstOrderDate;
	}
	
		
	
	public void setJobCode(final String jobCode)
	{
		this.jobCode = jobCode;
	}

		
	
	public String getJobCode() 
	{
		return jobCode;
	}
	


}