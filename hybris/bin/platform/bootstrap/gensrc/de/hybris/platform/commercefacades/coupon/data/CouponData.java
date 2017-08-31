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
package de.hybris.platform.commercefacades.coupon.data;

import java.util.Date;

public  class CouponData  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>CouponData.couponCode</code> property defined at extension <code>commercefacades</code>. */
		
	private String couponCode;

	/** <i>Generated property</i> for <code>CouponData.couponId</code> property defined at extension <code>commercefacades</code>. */
		
	private String couponId;

	/** <i>Generated property</i> for <code>CouponData.name</code> property defined at extension <code>commercefacades</code>. */
		
	private String name;

	/** <i>Generated property</i> for <code>CouponData.active</code> property defined at extension <code>commercefacades</code>. */
		
	private boolean active;

	/** <i>Generated property</i> for <code>CouponData.startDate</code> property defined at extension <code>commercefacades</code>. */
		
	private Date startDate;

	/** <i>Generated property</i> for <code>CouponData.endDate</code> property defined at extension <code>commercefacades</code>. */
		
	private Date endDate;
	
	public CouponData()
	{
		// default constructor
	}
	
		
	
	public void setCouponCode(final String couponCode)
	{
		this.couponCode = couponCode;
	}

		
	
	public String getCouponCode() 
	{
		return couponCode;
	}
	
		
	
	public void setCouponId(final String couponId)
	{
		this.couponId = couponId;
	}

		
	
	public String getCouponId() 
	{
		return couponId;
	}
	
		
	
	public void setName(final String name)
	{
		this.name = name;
	}

		
	
	public String getName() 
	{
		return name;
	}
	
		
	
	public void setActive(final boolean active)
	{
		this.active = active;
	}

		
	
	public boolean isActive() 
	{
		return active;
	}
	
		
	
	public void setStartDate(final Date startDate)
	{
		this.startDate = startDate;
	}

		
	
	public Date getStartDate() 
	{
		return startDate;
	}
	
		
	
	public void setEndDate(final Date endDate)
	{
		this.endDate = endDate;
	}

		
	
	public Date getEndDate() 
	{
		return endDate;
	}
	


}