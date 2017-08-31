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
package de.hybris.platform.ordermanagementwebservices.dto.returns;

import de.hybris.platform.commercewebservicescommons.dto.order.OrderWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.product.PriceWsDTO;
import de.hybris.platform.ordermanagementwebservices.dto.returns.ReturnEntryWsDTO;
import java.util.List;

public  class ReturnRequestWsDTO  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>ReturnRequestWsDTO.code</code> property defined at extension <code>ordermanagementwebservices</code>. */
		
	private String code;

	/** <i>Generated property</i> for <code>ReturnRequestWsDTO.rma</code> property defined at extension <code>ordermanagementwebservices</code>. */
		
	private String rma;

	/** <i>Generated property</i> for <code>ReturnRequestWsDTO.status</code> property defined at extension <code>ordermanagementwebservices</code>. */
		
	private String status;

	/** <i>Generated property</i> for <code>ReturnRequestWsDTO.order</code> property defined at extension <code>ordermanagementwebservices</code>. */
		
	private OrderWsDTO order;

	/** <i>Generated property</i> for <code>ReturnRequestWsDTO.deliveryCost</code> property defined at extension <code>ordermanagementwebservices</code>. */
		
	private PriceWsDTO deliveryCost;

	/** <i>Generated property</i> for <code>ReturnRequestWsDTO.entries</code> property defined at extension <code>ordermanagementwebservices</code>. */
		
	private List<ReturnEntryWsDTO> entries;

	/** <i>Generated property</i> for <code>ReturnRequestWsDTO.refundDeliveryCost</code> property defined at extension <code>ordermanagementwebservices</code>. */
		
	private Boolean refundDeliveryCost;
	
	public ReturnRequestWsDTO()
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
	
		
	
	public void setRma(final String rma)
	{
		this.rma = rma;
	}

		
	
	public String getRma() 
	{
		return rma;
	}
	
		
	
	public void setStatus(final String status)
	{
		this.status = status;
	}

		
	
	public String getStatus() 
	{
		return status;
	}
	
		
	
	public void setOrder(final OrderWsDTO order)
	{
		this.order = order;
	}

		
	
	public OrderWsDTO getOrder() 
	{
		return order;
	}
	
		
	
	public void setDeliveryCost(final PriceWsDTO deliveryCost)
	{
		this.deliveryCost = deliveryCost;
	}

		
	
	public PriceWsDTO getDeliveryCost() 
	{
		return deliveryCost;
	}
	
		
	
	public void setEntries(final List<ReturnEntryWsDTO> entries)
	{
		this.entries = entries;
	}

		
	
	public List<ReturnEntryWsDTO> getEntries() 
	{
		return entries;
	}
	
		
	
	public void setRefundDeliveryCost(final Boolean refundDeliveryCost)
	{
		this.refundDeliveryCost = refundDeliveryCost;
	}

		
	
	public Boolean getRefundDeliveryCost() 
	{
		return refundDeliveryCost;
	}
	


}