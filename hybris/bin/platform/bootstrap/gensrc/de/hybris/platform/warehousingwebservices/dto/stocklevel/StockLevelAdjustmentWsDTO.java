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
package de.hybris.platform.warehousingwebservices.dto.stocklevel;

public  class StockLevelAdjustmentWsDTO  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>StockLevelAdjustmentWsDTO.reason</code> property defined at extension <code>warehousingwebservices</code>. */
		
	private String reason;

	/** <i>Generated property</i> for <code>StockLevelAdjustmentWsDTO.quantity</code> property defined at extension <code>warehousingwebservices</code>. */
		
	private Long quantity;

	/** <i>Generated property</i> for <code>StockLevelAdjustmentWsDTO.comment</code> property defined at extension <code>warehousingwebservices</code>. */
		
	private String comment;
	
	public StockLevelAdjustmentWsDTO()
	{
		// default constructor
	}
	
		
	
	public void setReason(final String reason)
	{
		this.reason = reason;
	}

		
	
	public String getReason() 
	{
		return reason;
	}
	
		
	
	public void setQuantity(final Long quantity)
	{
		this.quantity = quantity;
	}

		
	
	public Long getQuantity() 
	{
		return quantity;
	}
	
		
	
	public void setComment(final String comment)
	{
		this.comment = comment;
	}

		
	
	public String getComment() 
	{
		return comment;
	}
	


}