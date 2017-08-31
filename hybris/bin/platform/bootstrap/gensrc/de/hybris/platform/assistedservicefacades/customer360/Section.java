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
package de.hybris.platform.assistedservicefacades.customer360;

import de.hybris.platform.assistedservicefacades.customer360.Fragment;
import java.util.List;

public  class Section  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>Section.title</code> property defined at extension <code>assistedservicefacades</code>. */
		
	private String title;

	/** <i>Generated property</i> for <code>Section.id</code> property defined at extension <code>assistedservicefacades</code>. */
		
	private String id;

	/** <i>Generated property</i> for <code>Section.fragments</code> property defined at extension <code>assistedservicefacades</code>. */
		
	private List<Fragment> fragments;
	
	public Section()
	{
		// default constructor
	}
	
		
	
	public void setTitle(final String title)
	{
		this.title = title;
	}

		
	
	public String getTitle() 
	{
		return title;
	}
	
		
	
	public void setId(final String id)
	{
		this.id = id;
	}

		
	
	public String getId() 
	{
		return id;
	}
	
		
	
	public void setFragments(final List<Fragment> fragments)
	{
		this.fragments = fragments;
	}

		
	
	public List<Fragment> getFragments() 
	{
		return fragments;
	}
	


}