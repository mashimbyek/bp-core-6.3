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
package de.hybris.platform.warehousing.data.allocation;

import java.util.Collection;

public  class DeclineEntries  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>DeclineEntries.entries</code> property defined at extension <code>warehousing</code>. */
		
	private Collection<DeclineEntry> entries;
	
	public DeclineEntries()
	{
		// default constructor
	}
	
		
	
	public void setEntries(final Collection<DeclineEntry> entries)
	{
		this.entries = entries;
	}

		
	
	public Collection<DeclineEntry> getEntries() 
	{
		return entries;
	}
	


}