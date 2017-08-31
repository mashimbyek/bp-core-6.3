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
package de.hybris.platform.ordermanagementwebservices.dto.returns;

import de.hybris.platform.commercewebservicescommons.dto.search.pagedata.PaginationWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.search.pagedata.SortWsDTO;
import de.hybris.platform.ordermanagementwebservices.dto.returns.ReturnEntryWsDTO;
import java.util.List;

public  class ReturnEntrySearchPageWsDTO  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>ReturnEntrySearchPageWsDTO.sorts</code> property defined at extension <code>ordermanagementwebservices</code>. */
		
	private List<SortWsDTO> sorts;

	/** <i>Generated property</i> for <code>ReturnEntrySearchPageWsDTO.pagination</code> property defined at extension <code>ordermanagementwebservices</code>. */
		
	private PaginationWsDTO pagination;

	/** <i>Generated property</i> for <code>ReturnEntrySearchPageWsDTO.orderEntries</code> property defined at extension <code>ordermanagementwebservices</code>. */
		
	private List<ReturnEntryWsDTO> orderEntries;
	
	public ReturnEntrySearchPageWsDTO()
	{
		// default constructor
	}
	
		
	
	public void setSorts(final List<SortWsDTO> sorts)
	{
		this.sorts = sorts;
	}

		
	
	public List<SortWsDTO> getSorts() 
	{
		return sorts;
	}
	
		
	
	public void setPagination(final PaginationWsDTO pagination)
	{
		this.pagination = pagination;
	}

		
	
	public PaginationWsDTO getPagination() 
	{
		return pagination;
	}
	
		
	
	public void setOrderEntries(final List<ReturnEntryWsDTO> orderEntries)
	{
		this.orderEntries = orderEntries;
	}

		
	
	public List<ReturnEntryWsDTO> getOrderEntries() 
	{
		return orderEntries;
	}
	


}