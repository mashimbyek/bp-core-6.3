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
package de.hybris.platform.warehousingfacade.storelocator.data;

import de.hybris.platform.commercefacades.order.data.ConsignmentData;
import de.hybris.platform.commercefacades.order.data.DeliveryModeData;
import de.hybris.platform.commercefacades.storelocator.data.PointOfServiceData;
import java.util.List;

public  class WarehouseData  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>WarehouseData.code</code> property defined at extension <code>warehousingfacade</code>. */
		
	private String code;

	/** <i>Generated property</i> for <code>WarehouseData.isDefault</code> property defined at extension <code>warehousingfacade</code>. */
		
	private Boolean isDefault;

	/** <i>Generated property</i> for <code>WarehouseData.url</code> property defined at extension <code>warehousingfacade</code>. */
		
	private String url;

	/** <i>Generated property</i> for <code>WarehouseData.consignments</code> property defined at extension <code>warehousingfacade</code>. */
		
	private List<ConsignmentData> consignments;

	/** <i>Generated property</i> for <code>WarehouseData.pointsOfServices</code> property defined at extension <code>warehousingfacade</code>. */
		
	private List<PointOfServiceData> pointsOfServices;

	/** <i>Generated property</i> for <code>WarehouseData.priority</code> property defined at extension <code>warehousingfacade</code>. */
		
	private Integer priority;

	/** <i>Generated property</i> for <code>WarehouseData.deliveryModes</code> property defined at extension <code>warehousingfacade</code>. */
		
	private List<DeliveryModeData> deliveryModes;
	
	public WarehouseData()
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
	
		
	
	public void setConsignments(final List<ConsignmentData> consignments)
	{
		this.consignments = consignments;
	}

		
	
	public List<ConsignmentData> getConsignments() 
	{
		return consignments;
	}
	
		
	
	public void setPointsOfServices(final List<PointOfServiceData> pointsOfServices)
	{
		this.pointsOfServices = pointsOfServices;
	}

		
	
	public List<PointOfServiceData> getPointsOfServices() 
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
	
		
	
	public void setDeliveryModes(final List<DeliveryModeData> deliveryModes)
	{
		this.deliveryModes = deliveryModes;
	}

		
	
	public List<DeliveryModeData> getDeliveryModes() 
	{
		return deliveryModes;
	}
	


}