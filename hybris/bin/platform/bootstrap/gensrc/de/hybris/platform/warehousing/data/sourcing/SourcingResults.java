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

import de.hybris.platform.warehousing.data.sourcing.SourcingResult;
import java.util.Set;

public  class SourcingResults  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>SourcingResults.results</code> property defined at extension <code>warehousing</code>. */
		
	private Set<SourcingResult> results;

	/** <i>Generated property</i> for <code>SourcingResults.complete</code> property defined at extension <code>warehousing</code>. */
		
	private boolean complete;
	
	public SourcingResults()
	{
		// default constructor
	}
	
		
	
	public void setResults(final Set<SourcingResult> results)
	{
		this.results = results;
	}

		
	
	public Set<SourcingResult> getResults() 
	{
		return results;
	}
	
		
	
	public void setComplete(final boolean complete)
	{
		this.complete = complete;
	}

		
	
	public boolean isComplete() 
	{
		return complete;
	}
	


}