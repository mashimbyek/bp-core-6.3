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
package de.hybris.platform.ordermanagementfacade.order.data;

import de.hybris.platform.basecommerce.enums.CancelReason;
import java.util.List;

public  class CancelReasonDataList  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>CancelReasonDataList.reasons</code> property defined at extension <code>ordermanagementfacade</code>. */
		
	private List<CancelReason> reasons;
	
	public CancelReasonDataList()
	{
		// default constructor
	}
	
		
	
	public void setReasons(final List<CancelReason> reasons)
	{
		this.reasons = reasons;
	}

		
	
	public List<CancelReason> getReasons() 
	{
		return reasons;
	}
	


}