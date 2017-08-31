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
package de.hybris.platform.warehousing.data.sourcing;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.warehousing.data.sourcing.SourcingResults;
import java.util.Collection;

public  class SourcingContext  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>SourcingContext.orderEntries</code> property defined at extension <code>warehousing</code>. */
		
	private Collection<AbstractOrderEntryModel> orderEntries;

	/** <i>Generated property</i> for <code>SourcingContext.sourcingLocations</code> property defined at extension <code>warehousing</code>. */
		
	private Collection<SourcingLocation> sourcingLocations;

	/** <i>Generated property</i> for <code>SourcingContext.result</code> property defined at extension <code>warehousing</code>. */
		
	private SourcingResults result;
	
	public SourcingContext()
	{
		// default constructor
	}
	
		
	
	public void setOrderEntries(final Collection<AbstractOrderEntryModel> orderEntries)
	{
		this.orderEntries = orderEntries;
	}

		
	
	public Collection<AbstractOrderEntryModel> getOrderEntries() 
	{
		return orderEntries;
	}
	
		
	
	public void setSourcingLocations(final Collection<SourcingLocation> sourcingLocations)
	{
		this.sourcingLocations = sourcingLocations;
	}

		
	
	public Collection<SourcingLocation> getSourcingLocations() 
	{
		return sourcingLocations;
	}
	
		
	
	public void setResult(final SourcingResults result)
	{
		this.result = result;
	}

		
	
	public SourcingResults getResult() 
	{
		return result;
	}
	


}