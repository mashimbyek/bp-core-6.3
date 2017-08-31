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
package de.hybris.platform.warehousing.data.shipping;

import de.hybris.platform.core.model.product.ProductModel;

public  class ReturnFormEntry  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>ReturnFormEntry.product</code> property defined at extension <code>warehousing</code>. */
		
	private ProductModel product;

	/** <i>Generated property</i> for <code>ReturnFormEntry.basePrice</code> property defined at extension <code>warehousing</code>. */
		
	private Double basePrice;

	/** <i>Generated property</i> for <code>ReturnFormEntry.quantityPurchased</code> property defined at extension <code>warehousing</code>. */
		
	private Long quantityPurchased;

	/** <i>Generated property</i> for <code>ReturnFormEntry.quantityReturned</code> property defined at extension <code>warehousing</code>. */
		
	private Long quantityReturned;
	
	public ReturnFormEntry()
	{
		// default constructor
	}
	
		
	
	public void setProduct(final ProductModel product)
	{
		this.product = product;
	}

		
	
	public ProductModel getProduct() 
	{
		return product;
	}
	
		
	
	public void setBasePrice(final Double basePrice)
	{
		this.basePrice = basePrice;
	}

		
	
	public Double getBasePrice() 
	{
		return basePrice;
	}
	
		
	
	public void setQuantityPurchased(final Long quantityPurchased)
	{
		this.quantityPurchased = quantityPurchased;
	}

		
	
	public Long getQuantityPurchased() 
	{
		return quantityPurchased;
	}
	
		
	
	public void setQuantityReturned(final Long quantityReturned)
	{
		this.quantityReturned = quantityReturned;
	}

		
	
	public Long getQuantityReturned() 
	{
		return quantityReturned;
	}
	


}