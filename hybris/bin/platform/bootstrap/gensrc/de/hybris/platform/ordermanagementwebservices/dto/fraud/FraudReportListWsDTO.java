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
package de.hybris.platform.ordermanagementwebservices.dto.fraud;

import de.hybris.platform.ordermanagementwebservices.dto.fraud.FraudReportWsDTO;
import java.util.List;

public  class FraudReportListWsDTO  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>FraudReportListWsDTO.reports</code> property defined at extension <code>ordermanagementwebservices</code>. */
		
	private List<FraudReportWsDTO> reports;
	
	public FraudReportListWsDTO()
	{
		// default constructor
	}
	
		
	
	public void setReports(final List<FraudReportWsDTO> reports)
	{
		this.reports = reports;
	}

		
	
	public List<FraudReportWsDTO> getReports() 
	{
		return reports;
	}
	


}