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
package de.hybris.platform.b2bapprovalprocessfacades.company.data;

import de.hybris.platform.b2b.enums.PermissionStatus;
import de.hybris.platform.b2bapprovalprocessfacades.company.data.B2BPermissionTypeData;

public  class B2BPermissionResultData  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>B2BPermissionResultData.status</code> property defined at extension <code>b2bapprovalprocessfacades</code>. */
		
	private PermissionStatus status;

	/** <i>Generated property</i> for <code>B2BPermissionResultData.approver</code> property defined at extension <code>b2bapprovalprocessfacades</code>. */
		
	private String approver;

	/** <i>Generated property</i> for <code>B2BPermissionResultData.permissionToEvaluate</code> property defined at extension <code>b2bapprovalprocessfacades</code>. */
		
	private String permissionToEvaluate;

	/** <i>Generated property</i> for <code>B2BPermissionResultData.permissionTypeCode</code> property defined at extension <code>b2bapprovalprocessfacades</code>. */
		
	private String permissionTypeCode;

	/** <i>Generated property</i> for <code>B2BPermissionResultData.approverNotes</code> property defined at extension <code>b2bapprovalprocessfacades</code>. */
		
	private String approverNotes;

	/** <i>Generated property</i> for <code>B2BPermissionResultData.statusDisplay</code> property defined at extension <code>b2bapprovalprocessfacades</code>. */
		
	private String statusDisplay;

	/** <i>Generated property</i> for <code>B2BPermissionResultData.permissionTypeData</code> property defined at extension <code>b2bapprovalprocessfacades</code>. */
		
	private B2BPermissionTypeData permissionTypeData;
	
	public B2BPermissionResultData()
	{
		// default constructor
	}
	
		
	
	public void setStatus(final PermissionStatus status)
	{
		this.status = status;
	}

		
	
	public PermissionStatus getStatus() 
	{
		return status;
	}
	
		
	
	public void setApprover(final String approver)
	{
		this.approver = approver;
	}

		
	
	public String getApprover() 
	{
		return approver;
	}
	
		
	
	public void setPermissionToEvaluate(final String permissionToEvaluate)
	{
		this.permissionToEvaluate = permissionToEvaluate;
	}

		
	
	public String getPermissionToEvaluate() 
	{
		return permissionToEvaluate;
	}
	
		
	
	public void setPermissionTypeCode(final String permissionTypeCode)
	{
		this.permissionTypeCode = permissionTypeCode;
	}

		
	
	public String getPermissionTypeCode() 
	{
		return permissionTypeCode;
	}
	
		
	
	public void setApproverNotes(final String approverNotes)
	{
		this.approverNotes = approverNotes;
	}

		
	
	public String getApproverNotes() 
	{
		return approverNotes;
	}
	
		
	
	public void setStatusDisplay(final String statusDisplay)
	{
		this.statusDisplay = statusDisplay;
	}

		
	
	public String getStatusDisplay() 
	{
		return statusDisplay;
	}
	
		
	
	public void setPermissionTypeData(final B2BPermissionTypeData permissionTypeData)
	{
		this.permissionTypeData = permissionTypeData;
	}

		
	
	public B2BPermissionTypeData getPermissionTypeData() 
	{
		return permissionTypeData;
	}
	


}