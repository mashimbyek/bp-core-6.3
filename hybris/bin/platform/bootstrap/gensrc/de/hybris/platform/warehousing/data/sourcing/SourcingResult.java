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
package de.hybris.platform.warehousing.data.sourcing;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import java.util.Map;

public  class SourcingResult  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>SourcingResult.allocation</code> property defined at extension <code>warehousing</code>. */
		
	private Map<AbstractOrderEntryModel, Long> allocation;

	/** <i>Generated property</i> for <code>SourcingResult.warehouse</code> property defined at extension <code>warehousing</code>. */
		
	private WarehouseModel warehouse;
	
	public SourcingResult()
	{
		// default constructor
	}
	
		
	
	public void setAllocation(final Map<AbstractOrderEntryModel, Long> allocation)
	{
		this.allocation = allocation;
	}

		
	
	public Map<AbstractOrderEntryModel, Long> getAllocation() 
	{
		return allocation;
	}
	
		
	
	public void setWarehouse(final WarehouseModel warehouse)
	{
		this.warehouse = warehouse;
	}

		
	
	public WarehouseModel getWarehouse() 
	{
		return warehouse;
	}
	


}