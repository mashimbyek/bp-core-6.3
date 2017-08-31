/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 25 Aug 2017 4:31:27 PM
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
package de.hybris.platform.ordermanagementfacade.fraud.data;

import de.hybris.platform.ordermanagementfacade.fraud.data.FraudSymptomScoringsData;
import java.util.Date;
import java.util.List;

public  class FraudReportData  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>FraudReportData.explanation</code> property defined at extension <code>ordermanagementfacade</code>. */
		
	private String explanation;

	/** <i>Generated property</i> for <code>FraudReportData.fraudSymptomScorings</code> property defined at extension <code>ordermanagementfacade</code>. */
		
	private List<FraudSymptomScoringsData> fraudSymptomScorings;

	/** <i>Generated property</i> for <code>FraudReportData.provider</code> property defined at extension <code>ordermanagementfacade</code>. */
		
	private String provider;

	/** <i>Generated property</i> for <code>FraudReportData.status</code> property defined at extension <code>ordermanagementfacade</code>. */
		
	private String status;

	/** <i>Generated property</i> for <code>FraudReportData.timestamp</code> property defined at extension <code>ordermanagementfacade</code>. */
		
	private Date timestamp;
	
	public FraudReportData()
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
	
		
	
	public void setFraudSymptomScorings(final List<FraudSymptomScoringsData> fraudSymptomScorings)
	{
		this.fraudSymptomScorings = fraudSymptomScorings;
	}

		
	
	public List<FraudSymptomScoringsData> getFraudSymptomScorings() 
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