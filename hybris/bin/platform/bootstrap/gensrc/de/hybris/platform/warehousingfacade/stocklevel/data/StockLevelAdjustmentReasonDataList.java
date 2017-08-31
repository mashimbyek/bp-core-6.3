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
package de.hybris.platform.warehousingfacade.stocklevel.data;

import de.hybris.platform.warehousing.enums.StockLevelAdjustmentReason;
import java.util.List;

public  class StockLevelAdjustmentReasonDataList  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>StockLevelAdjustmentReasonDataList.reasons</code> property defined at extension <code>warehousingfacade</code>. */
		
	private List<StockLevelAdjustmentReason> reasons;
	
	public StockLevelAdjustmentReasonDataList()
	{
		// default constructor
	}
	
		
	
	public void setReasons(final List<StockLevelAdjustmentReason> reasons)
	{
		this.reasons = reasons;
	}

		
	
	public List<StockLevelAdjustmentReason> getReasons() 
	{
		return reasons;
	}
	


}