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
package de.hybris.platform.commercefacades.order.data;

import de.hybris.platform.catalog.enums.ProductInfoStatus;
import de.hybris.platform.commercefacades.comment.data.CommentData;
import de.hybris.platform.commercefacades.order.data.ConfigurationInfoData;
import de.hybris.platform.commercefacades.order.data.DeliveryModeData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.storelocator.data.PointOfServiceData;
import java.util.List;
import java.util.Map;

public  class OrderEntryData  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>OrderEntryData.entryNumber</code> property defined at extension <code>commercefacades</code>. */
		
	private Integer entryNumber;

	/** <i>Generated property</i> for <code>OrderEntryData.quantity</code> property defined at extension <code>commercefacades</code>. */
		
	private Long quantity;

	/** <i>Generated property</i> for <code>OrderEntryData.basePrice</code> property defined at extension <code>commercefacades</code>. */
		
	private PriceData basePrice;

	/** <i>Generated property</i> for <code>OrderEntryData.totalPrice</code> property defined at extension <code>commercefacades</code>. */
		
	private PriceData totalPrice;

	/** <i>Generated property</i> for <code>OrderEntryData.product</code> property defined at extension <code>commercefacades</code>. */
		
	private ProductData product;

	/** <i>Generated property</i> for <code>OrderEntryData.updateable</code> property defined at extension <code>commercefacades</code>. */
		
	private boolean updateable;

	/** <i>Generated property</i> for <code>OrderEntryData.deliveryMode</code> property defined at extension <code>commercefacades</code>. */
		
	private DeliveryModeData deliveryMode;

	/** <i>Generated property</i> for <code>OrderEntryData.deliveryPointOfService</code> property defined at extension <code>commercefacades</code>. */
		
	private PointOfServiceData deliveryPointOfService;

	/** <i>Generated property</i> for <code>OrderEntryData.entries</code> property defined at extension <code>commercefacades</code>. */
		
	private List<OrderEntryData> entries;

	/** <i>Generated property</i> for <code>OrderEntryData.configurationInfos</code> property defined at extension <code>commercefacades</code>. */
		
	private List<ConfigurationInfoData> configurationInfos;

	/** <i>Generated property</i> for <code>OrderEntryData.statusSummaryMap</code> property defined at extension <code>commercefacades</code>. */
		
	private Map<ProductInfoStatus, Integer> statusSummaryMap;

	/** <i>Generated property</i> for <code>OrderEntryData.comments</code> property defined at extension <code>commercefacades</code>. */
		
	private List<CommentData> comments;

	/** <i>Generated property</i> for <code>OrderEntryData.url</code> property defined at extension <code>ordermanagementfacade</code>. */
		
	private String url;

	/** <i>Generated property</i> for <code>OrderEntryData.quantityAllocated</code> property defined at extension <code>warehousingfacade</code>. */
		
	private Long quantityAllocated;

	/** <i>Generated property</i> for <code>OrderEntryData.quantityUnallocated</code> property defined at extension <code>warehousingfacade</code>. */
		
	private Long quantityUnallocated;

	/** <i>Generated property</i> for <code>OrderEntryData.quantityCancelled</code> property defined at extension <code>warehousingfacade</code>. */
		
	private Long quantityCancelled;

	/** <i>Generated property</i> for <code>OrderEntryData.quantityPending</code> property defined at extension <code>warehousingfacade</code>. */
		
	private Long quantityPending;

	/** <i>Generated property</i> for <code>OrderEntryData.quantityShipped</code> property defined at extension <code>warehousingfacade</code>. */
		
	private Long quantityShipped;

	/** <i>Generated property</i> for <code>OrderEntryData.quantityReturned</code> property defined at extension <code>warehousingfacade</code>. */
		
	private Long quantityReturned;

	/** <i>Generated property</i> for <code>OrderEntryData.cancellableQty</code> property defined at extension <code>orderselfserviceaddon</code>. */
		
	private long cancellableQty;

	/** <i>Generated property</i> for <code>OrderEntryData.returnableQty</code> property defined at extension <code>orderselfserviceaddon</code>. */
		
	private long returnableQty;

	/** <i>Generated property</i> for <code>OrderEntryData.cancelledItemsPrice</code> property defined at extension <code>orderselfserviceaddon</code>. */
		
	private PriceData cancelledItemsPrice;

	/** <i>Generated property</i> for <code>OrderEntryData.returnedItemsPrice</code> property defined at extension <code>orderselfserviceaddon</code>. */
		
	private PriceData returnedItemsPrice;
	
	public OrderEntryData()
	{
		// default constructor
	}
	
		
	
	public void setEntryNumber(final Integer entryNumber)
	{
		this.entryNumber = entryNumber;
	}

		
	
	public Integer getEntryNumber() 
	{
		return entryNumber;
	}
	
		
	
	public void setQuantity(final Long quantity)
	{
		this.quantity = quantity;
	}

		
	
	public Long getQuantity() 
	{
		return quantity;
	}
	
		
	
	public void setBasePrice(final PriceData basePrice)
	{
		this.basePrice = basePrice;
	}

		
	
	public PriceData getBasePrice() 
	{
		return basePrice;
	}
	
		
	
	public void setTotalPrice(final PriceData totalPrice)
	{
		this.totalPrice = totalPrice;
	}

		
	
	public PriceData getTotalPrice() 
	{
		return totalPrice;
	}
	
		
	
	public void setProduct(final ProductData product)
	{
		this.product = product;
	}

		
	
	public ProductData getProduct() 
	{
		return product;
	}
	
		
	
	public void setUpdateable(final boolean updateable)
	{
		this.updateable = updateable;
	}

		
	
	public boolean isUpdateable() 
	{
		return updateable;
	}
	
		
	
	public void setDeliveryMode(final DeliveryModeData deliveryMode)
	{
		this.deliveryMode = deliveryMode;
	}

		
	
	public DeliveryModeData getDeliveryMode() 
	{
		return deliveryMode;
	}
	
		
	
	public void setDeliveryPointOfService(final PointOfServiceData deliveryPointOfService)
	{
		this.deliveryPointOfService = deliveryPointOfService;
	}

		
	
	public PointOfServiceData getDeliveryPointOfService() 
	{
		return deliveryPointOfService;
	}
	
		
	
	public void setEntries(final List<OrderEntryData> entries)
	{
		this.entries = entries;
	}

		
	
	public List<OrderEntryData> getEntries() 
	{
		return entries;
	}
	
		
	
	public void setConfigurationInfos(final List<ConfigurationInfoData> configurationInfos)
	{
		this.configurationInfos = configurationInfos;
	}

		
	
	public List<ConfigurationInfoData> getConfigurationInfos() 
	{
		return configurationInfos;
	}
	
		
	
	public void setStatusSummaryMap(final Map<ProductInfoStatus, Integer> statusSummaryMap)
	{
		this.statusSummaryMap = statusSummaryMap;
	}

		
	
	public Map<ProductInfoStatus, Integer> getStatusSummaryMap() 
	{
		return statusSummaryMap;
	}
	
		
	
	public void setComments(final List<CommentData> comments)
	{
		this.comments = comments;
	}

		
	
	public List<CommentData> getComments() 
	{
		return comments;
	}
	
		
	
	public void setUrl(final String url)
	{
		this.url = url;
	}

		
	
	public String getUrl() 
	{
		return url;
	}
	
		
	
	public void setQuantityAllocated(final Long quantityAllocated)
	{
		this.quantityAllocated = quantityAllocated;
	}

		
	
	public Long getQuantityAllocated() 
	{
		return quantityAllocated;
	}
	
		
	
	public void setQuantityUnallocated(final Long quantityUnallocated)
	{
		this.quantityUnallocated = quantityUnallocated;
	}

		
	
	public Long getQuantityUnallocated() 
	{
		return quantityUnallocated;
	}
	
		
	
	public void setQuantityCancelled(final Long quantityCancelled)
	{
		this.quantityCancelled = quantityCancelled;
	}

		
	
	public Long getQuantityCancelled() 
	{
		return quantityCancelled;
	}
	
		
	
	public void setQuantityPending(final Long quantityPending)
	{
		this.quantityPending = quantityPending;
	}

		
	
	public Long getQuantityPending() 
	{
		return quantityPending;
	}
	
		
	
	public void setQuantityShipped(final Long quantityShipped)
	{
		this.quantityShipped = quantityShipped;
	}

		
	
	public Long getQuantityShipped() 
	{
		return quantityShipped;
	}
	
		
	
	public void setQuantityReturned(final Long quantityReturned)
	{
		this.quantityReturned = quantityReturned;
	}

		
	
	public Long getQuantityReturned() 
	{
		return quantityReturned;
	}
	
		
	
	public void setCancellableQty(final long cancellableQty)
	{
		this.cancellableQty = cancellableQty;
	}

		
	
	public long getCancellableQty() 
	{
		return cancellableQty;
	}
	
		
	
	public void setReturnableQty(final long returnableQty)
	{
		this.returnableQty = returnableQty;
	}

		
	
	public long getReturnableQty() 
	{
		return returnableQty;
	}
	
		
	
	public void setCancelledItemsPrice(final PriceData cancelledItemsPrice)
	{
		this.cancelledItemsPrice = cancelledItemsPrice;
	}

		
	
	public PriceData getCancelledItemsPrice() 
	{
		return cancelledItemsPrice;
	}
	
		
	
	public void setReturnedItemsPrice(final PriceData returnedItemsPrice)
	{
		this.returnedItemsPrice = returnedItemsPrice;
	}

		
	
	public PriceData getReturnedItemsPrice() 
	{
		return returnedItemsPrice;
	}
	


}