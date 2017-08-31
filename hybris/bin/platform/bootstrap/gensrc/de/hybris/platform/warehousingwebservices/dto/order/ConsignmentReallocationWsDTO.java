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
package de.hybris.platform.warehousingwebservices.dto.order;

import de.hybris.platform.warehousingwebservices.dto.order.DeclineEntryWsDTO;
import java.util.List;

public  class ConsignmentReallocationWsDTO  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>ConsignmentReallocationWsDTO.declineEntries</code> property defined at extension <code>warehousingwebservices</code>. */
		
	private List<DeclineEntryWsDTO> declineEntries;

	/** <i>Generated property</i> for <code>ConsignmentReallocationWsDTO.globalReallocationWarehouseCode</code> property defined at extension <code>warehousingwebservices</code>. */
		
	private String globalReallocationWarehouseCode;

	/** <i>Generated property</i> for <code>ConsignmentReallocationWsDTO.globalReason</code> property defined at extension <code>warehousingwebservices</code>. */
		
	private String globalReason;

	/** <i>Generated property</i> for <code>ConsignmentReallocationWsDTO.globalComment</code> property defined at extension <code>warehousingwebservices</code>. */
		
	private String globalComment;
	
	public ConsignmentReallocationWsDTO()
	{
		// default constructor
	}
	
		
	
	public void setDeclineEntries(final List<DeclineEntryWsDTO> declineEntries)
	{
		this.declineEntries = declineEntries;
	}

		
	
	public List<DeclineEntryWsDTO> getDeclineEntries() 
	{
		return declineEntries;
	}
	
		
	
	public void setGlobalReallocationWarehouseCode(final String globalReallocationWarehouseCode)
	{
		this.globalReallocationWarehouseCode = globalReallocationWarehouseCode;
	}

		
	
	public String getGlobalReallocationWarehouseCode() 
	{
		return globalReallocationWarehouseCode;
	}
	
		
	
	public void setGlobalReason(final String globalReason)
	{
		this.globalReason = globalReason;
	}

		
	
	public String getGlobalReason() 
	{
		return globalReason;
	}
	
		
	
	public void setGlobalComment(final String globalComment)
	{
		this.globalComment = globalComment;
	}

		
	
	public String getGlobalComment() 
	{
		return globalComment;
	}
	


}