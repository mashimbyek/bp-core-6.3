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
package de.hybris.platform.warehousingfacade.order.data;

import de.hybris.platform.warehousing.enums.DeclineReason;
import java.util.List;

public  class DeclineReasonDataList  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>DeclineReasonDataList.reasons</code> property defined at extension <code>warehousingfacade</code>. */
		
	private List<DeclineReason> reasons;
	
	public DeclineReasonDataList()
	{
		// default constructor
	}
	
		
	
	public void setReasons(final List<DeclineReason> reasons)
	{
		this.reasons = reasons;
	}

		
	
	public List<DeclineReason> getReasons() 
	{
		return reasons;
	}
	


}