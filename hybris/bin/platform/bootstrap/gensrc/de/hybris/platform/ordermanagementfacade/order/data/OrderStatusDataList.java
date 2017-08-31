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
package de.hybris.platform.ordermanagementfacade.order.data;

import de.hybris.platform.core.enums.OrderStatus;
import java.util.List;

public  class OrderStatusDataList  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>OrderStatusDataList.statuses</code> property defined at extension <code>ordermanagementfacade</code>. */
		
	private List<OrderStatus> statuses;
	
	public OrderStatusDataList()
	{
		// default constructor
	}
	
		
	
	public void setStatuses(final List<OrderStatus> statuses)
	{
		this.statuses = statuses;
	}

		
	
	public List<OrderStatus> getStatuses() 
	{
		return statuses;
	}
	


}