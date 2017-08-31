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
package de.hybris.platform.warehousing.data.allocation;

import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.warehousing.enums.DeclineReason;

public  class DeclineEntry  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>DeclineEntry.consignmentEntry</code> property defined at extension <code>warehousing</code>. */
		
	private ConsignmentEntryModel consignmentEntry;

	/** <i>Generated property</i> for <code>DeclineEntry.quantity</code> property defined at extension <code>warehousing</code>. */
		
	private Long quantity;

	/** <i>Generated property</i> for <code>DeclineEntry.reason</code> property defined at extension <code>warehousing</code>. */
		
	private DeclineReason reason;

	/** <i>Generated property</i> for <code>DeclineEntry.notes</code> property defined at extension <code>warehousing</code>. */
		
	private String notes;

	/** <i>Generated property</i> for <code>DeclineEntry.reallocationWarehouse</code> property defined at extension <code>warehousing</code>. */
		
	private WarehouseModel reallocationWarehouse;
	
	public DeclineEntry()
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
	
		
	
	public void setQuantity(final Long quantity)
	{
		this.quantity = quantity;
	}

		
	
	public Long getQuantity() 
	{
		return quantity;
	}
	
		
	
	public void setReason(final DeclineReason reason)
	{
		this.reason = reason;
	}

		
	
	public DeclineReason getReason() 
	{
		return reason;
	}
	
		
	
	public void setNotes(final String notes)
	{
		this.notes = notes;
	}

		
	
	public String getNotes() 
	{
		return notes;
	}
	
		
	
	public void setReallocationWarehouse(final WarehouseModel reallocationWarehouse)
	{
		this.reallocationWarehouse = reallocationWarehouse;
	}

		
	
	public WarehouseModel getReallocationWarehouse() 
	{
		return reallocationWarehouse;
	}
	


}