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
package de.hybris.platform.warehousingfacade.product.data;

import de.hybris.platform.basecommerce.enums.InStockStatus;
import de.hybris.platform.warehousingfacade.storelocator.data.WarehouseData;
import java.util.Date;

public  class StockLevelData  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>StockLevelData.productCode</code> property defined at extension <code>warehousingfacade</code>. */
		
	private String productCode;

	/** <i>Generated property</i> for <code>StockLevelData.bin</code> property defined at extension <code>warehousingfacade</code>. */
		
	private String bin;

	/** <i>Generated property</i> for <code>StockLevelData.initialQuantityOnHand</code> property defined at extension <code>warehousingfacade</code>. */
		
	private Integer initialQuantityOnHand;

	/** <i>Generated property</i> for <code>StockLevelData.releaseDate</code> property defined at extension <code>warehousingfacade</code>. */
		
	private Date releaseDate;

	/** <i>Generated property</i> for <code>StockLevelData.inStockStatus</code> property defined at extension <code>warehousingfacade</code>. */
		
	private InStockStatus inStockStatus;

	/** <i>Generated property</i> for <code>StockLevelData.warehouse</code> property defined at extension <code>warehousingfacade</code>. */
		
	private WarehouseData warehouse;
	
	public StockLevelData()
	{
		// default constructor
	}
	
		
	
	public void setProductCode(final String productCode)
	{
		this.productCode = productCode;
	}

		
	
	public String getProductCode() 
	{
		return productCode;
	}
	
		
	
	public void setBin(final String bin)
	{
		this.bin = bin;
	}

		
	
	public String getBin() 
	{
		return bin;
	}
	
		
	
	public void setInitialQuantityOnHand(final Integer initialQuantityOnHand)
	{
		this.initialQuantityOnHand = initialQuantityOnHand;
	}

		
	
	public Integer getInitialQuantityOnHand() 
	{
		return initialQuantityOnHand;
	}
	
		
	
	public void setReleaseDate(final Date releaseDate)
	{
		this.releaseDate = releaseDate;
	}

		
	
	public Date getReleaseDate() 
	{
		return releaseDate;
	}
	
		
	
	public void setInStockStatus(final InStockStatus inStockStatus)
	{
		this.inStockStatus = inStockStatus;
	}

		
	
	public InStockStatus getInStockStatus() 
	{
		return inStockStatus;
	}
	
		
	
	public void setWarehouse(final WarehouseData warehouse)
	{
		this.warehouse = warehouse;
	}

		
	
	public WarehouseData getWarehouse() 
	{
		return warehouse;
	}
	


}