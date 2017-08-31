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
package de.hybris.platform.warehousingwebservices.dto.order;

import java.util.List;

public  class ConsignmentStatusListWsDTO  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>ConsignmentStatusListWsDTO.statuses</code> property defined at extension <code>warehousingwebservices</code>. */
		
	private List<String> statuses;
	
	public ConsignmentStatusListWsDTO()
	{
		// default constructor
	}
	
		
	
	public void setStatuses(final List<String> statuses)
	{
		this.statuses = statuses;
	}

		
	
	public List<String> getStatuses() 
	{
		return statuses;
	}
	


}