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
package de.hybris.platform.b2bacceleratorfacades.product.data;

public  class CartEntryData  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>CartEntryData.entryNumber</code> property defined at extension <code>b2bacceleratorfacades</code>. */
		
	private Long entryNumber;

	/** <i>Generated property</i> for <code>CartEntryData.sku</code> property defined at extension <code>b2bacceleratorfacades</code>. */
		
	private String sku;

	/** <i>Generated property</i> for <code>CartEntryData.quantity</code> property defined at extension <code>b2bacceleratorfacades</code>. */
		
	private Long quantity;
	
	public CartEntryData()
	{
		// default constructor
	}
	
		
	
	public void setEntryNumber(final Long entryNumber)
	{
		this.entryNumber = entryNumber;
	}

		
	
	public Long getEntryNumber() 
	{
		return entryNumber;
	}
	
		
	
	public void setSku(final String sku)
	{
		this.sku = sku;
	}

		
	
	public String getSku() 
	{
		return sku;
	}
	
		
	
	public void setQuantity(final Long quantity)
	{
		this.quantity = quantity;
	}

		
	
	public Long getQuantity() 
	{
		return quantity;
	}
	


}