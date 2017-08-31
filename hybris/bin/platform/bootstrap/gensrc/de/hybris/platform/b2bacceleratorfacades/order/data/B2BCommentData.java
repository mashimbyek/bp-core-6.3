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
package de.hybris.platform.b2bacceleratorfacades.order.data;

import de.hybris.platform.commercefacades.user.data.PrincipalData;
import java.util.Date;

/**
 * @deprecated Since 6.3. Use {@link de.hybris.platform.commercefacades.comment.data.CommentData} instead.
 */
@Deprecated
public  class B2BCommentData  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>B2BCommentData.comment</code> property defined at extension <code>b2bacceleratorfacades</code>. */
		
	private String comment;

	/** <i>Generated property</i> for <code>B2BCommentData.code</code> property defined at extension <code>b2bacceleratorfacades</code>. */
		
	private String code;

	/** <i>Generated property</i> for <code>B2BCommentData.timeStamp</code> property defined at extension <code>b2bacceleratorfacades</code>. */
		
	private Date timeStamp;

	/** <i>Generated property</i> for <code>B2BCommentData.ownerData</code> property defined at extension <code>b2bacceleratorfacades</code>. */
		
	private PrincipalData ownerData;
	
	public B2BCommentData()
	{
		// default constructor
	}
	
		
	
	public void setComment(final String comment)
	{
		this.comment = comment;
	}

		
	
	public String getComment() 
	{
		return comment;
	}
	
		
	
	public void setCode(final String code)
	{
		this.code = code;
	}

		
	
	public String getCode() 
	{
		return code;
	}
	
		
	
	public void setTimeStamp(final Date timeStamp)
	{
		this.timeStamp = timeStamp;
	}

		
	
	public Date getTimeStamp() 
	{
		return timeStamp;
	}
	
		
	
	public void setOwnerData(final PrincipalData ownerData)
	{
		this.ownerData = ownerData;
	}

		
	
	public PrincipalData getOwnerData() 
	{
		return ownerData;
	}
	


}