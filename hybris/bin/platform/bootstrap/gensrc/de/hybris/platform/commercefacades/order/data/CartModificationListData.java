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
package de.hybris.platform.commercefacades.order.data;

import java.util.List;

public  class CartModificationListData  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>CartModificationListData.cartModifications</code> property defined at extension <code>b2bacceleratorfacades</code>. */
		
	private List<CartModificationData> cartModifications;
	
	public CartModificationListData()
	{
		// default constructor
	}
	
		
	
	public void setCartModifications(final List<CartModificationData> cartModifications)
	{
		this.cartModifications = cartModifications;
	}

		
	
	public List<CartModificationData> getCartModifications() 
	{
		return cartModifications;
	}
	


}