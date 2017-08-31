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
package de.hybris.platform.ordermanagementfacade.returns.data;

import de.hybris.platform.basecommerce.enums.CancelReason;

public  class CancelReturnRequestData  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>CancelReturnRequestData.code</code> property defined at extension <code>ordermanagementfacade</code>. */
		
	private String code;

	/** <i>Generated property</i> for <code>CancelReturnRequestData.cancelReason</code> property defined at extension <code>ordermanagementfacade</code>. */
		
	private CancelReason cancelReason;

	/** <i>Generated property</i> for <code>CancelReturnRequestData.notes</code> property defined at extension <code>ordermanagementfacade</code>. */
		
	private String notes;
	
	public CancelReturnRequestData()
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
	
		
	
	public void setCancelReason(final CancelReason cancelReason)
	{
		this.cancelReason = cancelReason;
	}

		
	
	public CancelReason getCancelReason() 
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