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
package de.hybris.platform.ordermanagementwebservices.dto.returns;

public  class CancelReturnRequestWsDTO  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>CancelReturnRequestWsDTO.code</code> property defined at extension <code>ordermanagementwebservices</code>. */
		
	private String code;

	/** <i>Generated property</i> for <code>CancelReturnRequestWsDTO.cancelReason</code> property defined at extension <code>ordermanagementwebservices</code>. */
		
	private String cancelReason;

	/** <i>Generated property</i> for <code>CancelReturnRequestWsDTO.notes</code> property defined at extension <code>ordermanagementwebservices</code>. */
		
	private String notes;
	
	public CancelReturnRequestWsDTO()
	{
		// default constructor
	}
	
		
	
	public void setCode(final String code)
	{
		this.code = code;
	}

		
	
	public String getCode() 
	{
		return code;
	}
	
		
	
	public void setCancelReason(final String cancelReason)
	{
		this.cancelReason = cancelReason;
	}

		
	
	public String getCancelReason() 
	{
		return cancelReason;
	}
	
		
	
	public void setNotes(final String notes)
	{
		this.notes = notes;
	}

		
	
	public String getNotes() 
	{
		return notes;
	}
	


}