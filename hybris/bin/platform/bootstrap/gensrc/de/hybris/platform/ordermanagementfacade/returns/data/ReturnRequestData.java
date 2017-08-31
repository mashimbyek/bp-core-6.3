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
package de.hybris.platform.ordermanagementfacade.returns.data;

import de.hybris.platform.basecommerce.enums.ReturnStatus;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.ordermanagementfacade.returns.data.ReturnEntryData;
import java.util.Date;
import java.util.List;

public  class ReturnRequestData  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>ReturnRequestData.code</code> property defined at extension <code>ordermanagementfacade</code>. */
		
	private String code;

	/** <i>Generated property</i> for <code>ReturnRequestData.rma</code> property defined at extension <code>ordermanagementfacade</code>. */
		
	private String rma;

	/** <i>Generated property</i> for <code>ReturnRequestData.creationTime</code> property defined at extension <code>ordermanagementfacade</code>. */
		
	private Date creationTime;

	/** <i>Generated property</i> for <code>ReturnRequestData.order</code> property defined at extension <code>ordermanagementfacade</code>. */
		
	private OrderData order;

	/** <i>Generated property</i> for <code>ReturnRequestData.deliveryCost</code> property defined at extension <code>ordermanagementfacade</code>. */
		
	private PriceData deliveryCost;

	/** <i>Generated property</i> for <code>ReturnRequestData.entries</code> property defined at extension <code>ordermanagementfacade</code>. */
		
	private List<ReturnEntryData> entries;

	/** <i>Generated property</i> for <code>ReturnRequestData.status</code> property defined at extension <code>ordermanagementfacade</code>. */
		
	private ReturnStatus status;

	/** <i>Generated property</i> for <code>ReturnRequestData.refundDeliveryCost</code> property defined at extension <code>ordermanagementfacade</code>. */
		
	private Boolean refundDeliveryCost;

	/** <i>Generated property</i> for <code>ReturnRequestData.subTotal</code> property defined at extension <code>ordermanagementfacade</code>. */
		
	private PriceData subTotal;

	/** <i>Generated property</i> for <code>ReturnRequestData.total</code> property defined at extension <code>ordermanagementfacade</code>. */
		
	private PriceData total;
	
	public ReturnRequestData()
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
	
		
	
	public void setCreationTime(final Date creationTime)
	{
		this.creationTime = creationTime;
	}

		
	
	public Date getCreationTime() 
	{
		return creationTime;
	}
	
		
	
	public void setOrder(final OrderData order)
	{
		this.order = order;
	}

		
	
	public OrderData getOrder() 
	{
		return order;
	}
	
		
	
	public void setDeliveryCost(final PriceData deliveryCost)
	{
		this.deliveryCost = deliveryCost;
	}

		
	
	public PriceData getDeliveryCost() 
	{
		return deliveryCost;
	}
	
		
	
	public void setEntries(final List<ReturnEntryData> entries)
	{
		this.entries = entries;
	}

		
	
	public List<ReturnEntryData> getEntries() 
	{
		return entries;
	}
	
		
	
	public void setStatus(final ReturnStatus status)
	{
		this.status = status;
	}

		
	
	public ReturnStatus getStatus() 
	{
		return status;
	}
	
		
	
	public void setRefundDeliveryCost(final Boolean refundDeliveryCost)
	{
		this.refundDeliveryCost = refundDeliveryCost;
	}

		
	
	public Boolean getRefundDeliveryCost() 
	{
		return refundDeliveryCost;
	}
	
		
	
	public void setSubTotal(final PriceData subTotal)
	{
		this.subTotal = subTotal;
	}

		
	
	public PriceData getSubTotal() 
	{
		return subTotal;
	}
	
		
	
	public void setTotal(final PriceData total)
	{
		this.total = total;
	}

		
	
	public PriceData getTotal() 
	{
		return total;
	}
	


}