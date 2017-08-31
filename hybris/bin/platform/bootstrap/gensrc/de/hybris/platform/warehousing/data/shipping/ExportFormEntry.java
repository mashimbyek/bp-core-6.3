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
package de.hybris.platform.warehousing.data.shipping;

import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import java.math.BigDecimal;

public  class ExportFormEntry  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>ExportFormEntry.consignmentEntry</code> property defined at extension <code>warehousing</code>. */
		
	private ConsignmentEntryModel consignmentEntry;

	/** <i>Generated property</i> for <code>ExportFormEntry.itemPrice</code> property defined at extension <code>warehousing</code>. */
		
	private BigDecimal itemPrice;

	/** <i>Generated property</i> for <code>ExportFormEntry.totalPrice</code> property defined at extension <code>warehousing</code>. */
		
	private BigDecimal totalPrice;
	
	public ExportFormEntry()
	{
		// default constructor
	}
	
		
	
	public void setConsignmentEntry(final ConsignmentEntryModel consignmentEntry)
	{
		this.consignmentEntry = consignmentEntry;
	}

		
	
	public ConsignmentEntryModel getConsignmentEntry() 
	{
		return consignmentEntry;
	}
	
		
	
	public void setItemPrice(final BigDecimal itemPrice)
	{
		this.itemPrice = itemPrice;
	}

		
	
	public BigDecimal getItemPrice() 
	{
		return itemPrice;
	}
	
		
	
	public void setTotalPrice(final BigDecimal totalPrice)
	{
		this.totalPrice = totalPrice;
	}

		
	
	public BigDecimal getTotalPrice() 
	{
		return totalPrice;
	}
	


}