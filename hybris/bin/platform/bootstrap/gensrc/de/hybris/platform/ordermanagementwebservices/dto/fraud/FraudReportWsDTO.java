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
package de.hybris.platform.ordermanagementwebservices.dto.fraud;

import de.hybris.platform.ordermanagementwebservices.dto.fraud.FraudSymptomScoringsWsDTO;
import java.util.Date;
import java.util.List;

public  class FraudReportWsDTO  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>FraudReportWsDTO.explanation</code> property defined at extension <code>ordermanagementwebservices</code>. */
		
	private String explanation;

	/** <i>Generated property</i> for <code>FraudReportWsDTO.fraudSymptomScorings</code> property defined at extension <code>ordermanagementwebservices</code>. */
		
	private List<FraudSymptomScoringsWsDTO> fraudSymptomScorings;

	/** <i>Generated property</i> for <code>FraudReportWsDTO.provider</code> property defined at extension <code>ordermanagementwebservices</code>. */
		
	private String provider;

	/** <i>Generated property</i> for <code>FraudReportWsDTO.status</code> property defined at extension <code>ordermanagementwebservices</code>. */
		
	private String status;

	/** <i>Generated property</i> for <code>FraudReportWsDTO.timestamp</code> property defined at extension <code>ordermanagementwebservices</code>. */
		
	private Date timestamp;
	
	public FraudReportWsDTO()
	{
		// default constructor
	}
	
		
	
	public void setExplanation(final String explanation)
	{
		this.explanation = explanation;
	}

		
	
	public String getExplanation() 
	{
		return explanation;
	}
	
		
	
	public void setFraudSymptomScorings(final List<FraudSymptomScoringsWsDTO> fraudSymptomScorings)
	{
		this.fraudSymptomScorings = fraudSymptomScorings;
	}

		
	
	public List<FraudSymptomScoringsWsDTO> getFraudSymptomScorings() 
	{
		return fraudSymptomScorings;
	}
	
		
	
	public void setProvider(final String provider)
	{
		this.provider = provider;
	}

		
	
	public String getProvider() 
	{
		return provider;
	}
	
		
	
	public void setStatus(final String status)
	{
		this.status = status;
	}

		
	
	public String getStatus() 
	{
		return status;
	}
	
		
	
	public void setTimestamp(final Date timestamp)
	{
		this.timestamp = timestamp;
	}

		
	
	public Date getTimestamp() 
	{
		return timestamp;
	}
	


}