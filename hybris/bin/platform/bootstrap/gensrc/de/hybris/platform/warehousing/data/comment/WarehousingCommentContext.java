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
package de.hybris.platform.warehousing.data.comment;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.warehousing.data.comment.WarehousingCommentEventType;

public  class WarehousingCommentContext  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>WarehousingCommentContext.commentType</code> property defined at extension <code>warehousing</code>. */
		
	private WarehousingCommentEventType commentType;

	/** <i>Generated property</i> for <code>WarehousingCommentContext.item</code> property defined at extension <code>warehousing</code>. */
		
	private ItemModel item;

	/** <i>Generated property</i> for <code>WarehousingCommentContext.subject</code> property defined at extension <code>warehousing</code>. */
		
	private String subject;

	/** <i>Generated property</i> for <code>WarehousingCommentContext.text</code> property defined at extension <code>warehousing</code>. */
		
	private String text;
	
	public WarehousingCommentContext()
	{
		// default constructor
	}
	
		
	
	public void setCommentType(final WarehousingCommentEventType commentType)
	{
		this.commentType = commentType;
	}

		
	
	public WarehousingCommentEventType getCommentType() 
	{
		return commentType;
	}
	
		
	
	public void setItem(final ItemModel item)
	{
		this.item = item;
	}

		
	
	public ItemModel getItem() 
	{
		return item;
	}
	
		
	
	public void setSubject(final String subject)
	{
		this.subject = subject;
	}

		
	
	public String getSubject() 
	{
		return subject;
	}
	
		
	
	public void setText(final String text)
	{
		this.text = text;
	}

		
	
	public String getText() 
	{
		return text;
	}
	


}