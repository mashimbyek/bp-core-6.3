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
package de.hybris.platform.warehousing.data.shipping;

import  java.util.List;
import java.math.BigDecimal;

public  class ExportForm  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>ExportForm.formEntries</code> property defined at extension <code>warehousing</code>. */
		
	private List<ExportFormEntry> formEntries;

	/** <i>Generated property</i> for <code>ExportForm.totalPrice</code> property defined at extension <code>warehousing</code>. */
		
	private BigDecimal totalPrice;
	
	public ExportForm()
	{
		// default constructor
	}
	
		
	
	public void setFormEntries(final List<ExportFormEntry> formEntries)
	{
		this.formEntries = formEntries;
	}

		
	
	public List<ExportFormEntry> getFormEntries() 
	{
		return formEntries;
	}
	
		
	
	public void setTotalPrice(final BigDecimal totalPrice)
	{
		this.totalPrice = totalPrice;
	}

		
	
	public BigDecimal getTotalPrice() 
	{
		return totalPrice;
	}
	


}