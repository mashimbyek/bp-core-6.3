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
package de.hybris.platform.warehousingwebservices.dto.stocklevel;

import java.util.List;

public  class StockLevelAdjustmentReasonsWsDTO  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>StockLevelAdjustmentReasonsWsDTO.reasons</code> property defined at extension <code>warehousingwebservices</code>. */
		
	private List<String> reasons;
	
	public StockLevelAdjustmentReasonsWsDTO()
	{
		// default constructor
	}
	
		
	
	public void setReasons(final List<String> reasons)
	{
		this.reasons = reasons;
	}

		
	
	public List<String> getReasons() 
	{
		return reasons;
	}
	


}