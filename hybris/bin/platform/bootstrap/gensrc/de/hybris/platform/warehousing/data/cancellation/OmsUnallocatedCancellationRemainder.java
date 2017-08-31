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
package de.hybris.platform.warehousing.data.cancellation;

import de.hybris.platform.ordercancel.model.OrderEntryCancelRecordEntryModel;

public  class OmsUnallocatedCancellationRemainder  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>OmsUnallocatedCancellationRemainder.orderEntryCancellationRecord</code> property defined at extension <code>warehousing</code>. */
		
	private OrderEntryCancelRecordEntryModel orderEntryCancellationRecord;

	/** <i>Generated property</i> for <code>OmsUnallocatedCancellationRemainder.remainingQuantity</code> property defined at extension <code>warehousing</code>. */
		
	private Integer remainingQuantity;
	
	public OmsUnallocatedCancellationRemainder()
	{
		// default constructor
	}
	
		
	
	public void setOrderEntryCancellationRecord(final OrderEntryCancelRecordEntryModel orderEntryCancellationRecord)
	{
		this.orderEntryCancellationRecord = orderEntryCancellationRecord;
	}

		
	
	public OrderEntryCancelRecordEntryModel getOrderEntryCancellationRecord() 
	{
		return orderEntryCancellationRecord;
	}
	
		
	
	public void setRemainingQuantity(final Integer remainingQuantity)
	{
		this.remainingQuantity = remainingQuantity;
	}

		
	
	public Integer getRemainingQuantity() 
	{
		return remainingQuantity;
	}
	


}