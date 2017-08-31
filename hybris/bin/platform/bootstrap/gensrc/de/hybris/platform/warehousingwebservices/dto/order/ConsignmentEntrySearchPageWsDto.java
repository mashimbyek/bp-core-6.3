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

import de.hybris.platform.commercewebservicescommons.dto.order.ConsignmentEntryWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.search.pagedata.PaginationWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.search.pagedata.SortWsDTO;
import java.util.List;

public  class ConsignmentEntrySearchPageWsDto  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>ConsignmentEntrySearchPageWsDto.sorts</code> property defined at extension <code>warehousingwebservices</code>. */
		
	private List<SortWsDTO> sorts;

	/** <i>Generated property</i> for <code>ConsignmentEntrySearchPageWsDto.pagination</code> property defined at extension <code>warehousingwebservices</code>. */
		
	private PaginationWsDTO pagination;

	/** <i>Generated property</i> for <code>ConsignmentEntrySearchPageWsDto.consignmentEntries</code> property defined at extension <code>warehousingwebservices</code>. */
		
	private List<ConsignmentEntryWsDTO> consignmentEntries;
	
	public ConsignmentEntrySearchPageWsDto()
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
	
		
	
	public void setConsignmentEntries(final List<ConsignmentEntryWsDTO> consignmentEntries)
	{
		this.consignmentEntries = consignmentEntries;
	}

		
	
	public List<ConsignmentEntryWsDTO> getConsignmentEntries() 
	{
		return consignmentEntries;
	}
	


}