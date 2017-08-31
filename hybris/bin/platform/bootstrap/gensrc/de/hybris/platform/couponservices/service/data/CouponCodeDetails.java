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
package de.hybris.platform.couponservices.service.data;

/**
 * @deprecated Since version 6.2 use de.hybris.platform.commercefacades.coupon.data.CouponData instead
 */
@Deprecated
public  class CouponCodeDetails  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>CouponCodeDetails.couponId</code> property defined at extension <code>couponservices</code>. */
		
	private String couponId;

	/** <i>Generated property</i> for <code>CouponCodeDetails.code</code> property defined at extension <code>couponservices</code>. */
		
	private String code;

	/** <i>Generated property</i> for <code>CouponCodeDetails.redeemed</code> property defined at extension <code>couponservices</code>. */
		
	private Boolean redeemed;
	
	public CouponCodeDetails()
	{
		// default constructor
	}
	
		
	
	public void setCouponId(final String couponId)
	{
		this.couponId = couponId;
	}

		
	
	public String getCouponId() 
	{
		return couponId;
	}
	
		
	
	public void setCode(final String code)
	{
		this.code = code;
	}

		
	
	public String getCode() 
	{
		return code;
	}
	
		
	
	public void setRedeemed(final Boolean redeemed)
	{
		this.redeemed = redeemed;
	}

		
	
	public Boolean getRedeemed() 
	{
		return redeemed;
	}
	

	@Override
	public boolean equals(final Object o)
	{
	
		if (o == null) return false;
		if (o == this) return true;

		try
		{
			final CouponCodeDetails other = (CouponCodeDetails) o;
			return new org.apache.commons.lang.builder.EqualsBuilder()
			.append(getCode(), other.getCode()) 
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
		.append(getCode()) 
		.toHashCode();
	}

}