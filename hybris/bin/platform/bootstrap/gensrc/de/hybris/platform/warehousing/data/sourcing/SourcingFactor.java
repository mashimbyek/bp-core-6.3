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
package de.hybris.platform.warehousing.data.sourcing;

import de.hybris.platform.warehousing.data.sourcing.SourcingFactorIdentifiersEnum;

public  class SourcingFactor  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>SourcingFactor.factorId</code> property defined at extension <code>warehousing</code>. */
		
	private SourcingFactorIdentifiersEnum factorId;

	/** <i>Generated property</i> for <code>SourcingFactor.weight</code> property defined at extension <code>warehousing</code>. */
		
	private int weight;
	
	public SourcingFactor()
	{
		// default constructor
	}
	
		
	
	public void setFactorId(final SourcingFactorIdentifiersEnum factorId)
	{
		this.factorId = factorId;
	}

		
	
	public SourcingFactorIdentifiersEnum getFactorId() 
	{
		return factorId;
	}
	
		
	
	public void setWeight(final int weight)
	{
		this.weight = weight;
	}

		
	
	public int getWeight() 
	{
		return weight;
	}
	


}