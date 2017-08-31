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
package de.hybris.platform.warehousingfacade.order.data;

import de.hybris.platform.warehousing.enums.DeclineReason;
import de.hybris.platform.warehousingfacade.order.data.DeclineEntryData;
import java.util.List;

public  class ConsignmentReallocationData  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>ConsignmentReallocationData.declineEntries</code> property defined at extension <code>warehousingfacade</code>. */
		
	private List<DeclineEntryData> declineEntries;

	/** <i>Generated property</i> for <code>ConsignmentReallocationData.globalReallocationWarehouseCode</code> property defined at extension <code>warehousingfacade</code>. */
		
	private String globalReallocationWarehouseCode;

	/** <i>Generated property</i> for <code>ConsignmentReallocationData.globalReason</code> property defined at extension <code>warehousingfacade</code>. */
		
	private DeclineReason globalReason;

	/** <i>Generated property</i> for <code>ConsignmentReallocationData.globalComment</code> property defined at extension <code>warehousingfacade</code>. */
		
	private String globalComment;
	
	public ConsignmentReallocationData()
	{
		// default constructor
	}
	
		
	
	public void setDeclineEntries(final List<DeclineEntryData> declineEntries)
	{
		this.declineEntries = declineEntries;
	}

		
	
	public List<DeclineEntryData> getDeclineEntries() 
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
	
		
	
	public void setGlobalReason(final DeclineReason globalReason)
	{
		this.globalReason = globalReason;
	}

		
	
	public DeclineReason getGlobalReason() 
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