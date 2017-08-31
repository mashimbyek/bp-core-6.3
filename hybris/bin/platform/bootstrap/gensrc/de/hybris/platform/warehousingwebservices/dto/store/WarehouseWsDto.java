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
package de.hybris.platform.warehousingwebservices.dto.store;

import de.hybris.platform.commercewebservicescommons.dto.order.ConsignmentWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.order.DeliveryModeWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.store.PointOfServiceWsDTO;
import java.util.List;

public  class WarehouseWsDto  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>WarehouseWsDto.code</code> property defined at extension <code>warehousingwebservices</code>. */
		
	private String code;

	/** <i>Generated property</i> for <code>WarehouseWsDto.isDefault</code> property defined at extension <code>warehousingwebservices</code>. */
		
	private Boolean isDefault;

	/** <i>Generated property</i> for <code>WarehouseWsDto.url</code> property defined at extension <code>warehousingwebservices</code>. */
		
	private String url;

	/** <i>Generated property</i> for <code>WarehouseWsDto.consignments</code> property defined at extension <code>warehousingwebservices</code>. */
		
	private List<ConsignmentWsDTO> consignments;

	/** <i>Generated property</i> for <code>WarehouseWsDto.pointsOfServices</code> property defined at extension <code>warehousingwebservices</code>. */
		
	private List<PointOfServiceWsDTO> pointsOfServices;

	/** <i>Generated property</i> for <code>WarehouseWsDto.priority</code> property defined at extension <code>warehousingwebservices</code>. */
		
	private Integer priority;

	/** <i>Generated property</i> for <code>WarehouseWsDto.deliveryModes</code> property defined at extension <code>warehousingwebservices</code>. */
		
	private List<DeliveryModeWsDTO> deliveryModes;
	
	public WarehouseWsDto()
	{
		// default constructor
	}
	
		
	
	public void setCode(final String code)
	{
		this.code = code;
	}

		
	
	public String getCode() 
	{
		return code;
	}
	
		
	
	public void setIsDefault(final Boolean isDefault)
	{
		this.isDefault = isDefault;
	}

		
	
	public Boolean getIsDefault() 
	{
		return isDefault;
	}
	
		
	
	public void setUrl(final String url)
	{
		this.url = url;
	}

		
	
	public String getUrl() 
	{
		return url;
	}
	
		
	
	public void setConsignments(final List<ConsignmentWsDTO> consignments)
	{
		this.consignments = consignments;
	}

		
	
	public List<ConsignmentWsDTO> getConsignments() 
	{
		return consignments;
	}
	
		
	
	public void setPointsOfServices(final List<PointOfServiceWsDTO> pointsOfServices)
	{
		this.pointsOfServices = pointsOfServices;
	}

		
	
	public List<PointOfServiceWsDTO> getPointsOfServices() 
	{
		return pointsOfServices;
	}
	
		
	
	public void setPriority(final Integer priority)
	{
		this.priority = priority;
	}

		
	
	public Integer getPriority() 
	{
		return priority;
	}
	
		
	
	public void setDeliveryModes(final List<DeliveryModeWsDTO> deliveryModes)
	{
		this.deliveryModes = deliveryModes;
	}

		
	
	public List<DeliveryModeWsDTO> getDeliveryModes() 
	{
		return deliveryModes;
	}
	


}