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
package de.hybris.platform.warehousingwebservices.dto.store;

import de.hybris.platform.commercewebservicescommons.dto.search.pagedata.PaginationWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.search.pagedata.SortWsDTO;
import de.hybris.platform.warehousingwebservices.dto.store.WarehouseWsDto;
import java.util.List;

public  class WarehouseSearchPageWsDto  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>WarehouseSearchPageWsDto.warehouses</code> property defined at extension <code>warehousingwebservices</code>. */
		
	private List<WarehouseWsDto> warehouses;

	/** <i>Generated property</i> for <code>WarehouseSearchPageWsDto.sorts</code> property defined at extension <code>warehousingwebservices</code>. */
		
	private List<SortWsDTO> sorts;

	/** <i>Generated property</i> for <code>WarehouseSearchPageWsDto.pagination</code> property defined at extension <code>warehousingwebservices</code>. */
		
	private PaginationWsDTO pagination;
	
	public WarehouseSearchPageWsDto()
	{
		// default constructor
	}
	
		
	
	public void setWarehouses(final List<WarehouseWsDto> warehouses)
	{
		this.warehouses = warehouses;
	}

		
	
	public List<WarehouseWsDto> getWarehouses() 
	{
		return warehouses;
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
	


}