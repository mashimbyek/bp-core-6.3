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
package de.hybris.platform.solrfacetsearch.config;

public  class SolrClientConfig  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>SolrClientConfig.aliveCheckInterval</code> property defined at extension <code>solrfacetsearch</code>. */
		
	private Integer aliveCheckInterval;

	/** <i>Generated property</i> for <code>SolrClientConfig.connectionTimeout</code> property defined at extension <code>solrfacetsearch</code>. */
		
	private Integer connectionTimeout;

	/** <i>Generated property</i> for <code>SolrClientConfig.socketTimeout</code> property defined at extension <code>solrfacetsearch</code>. */
		
	private Integer socketTimeout;

	/** <i>Generated property</i> for <code>SolrClientConfig.maxConnections</code> property defined at extension <code>solrfacetsearch</code>. */
		
	private Integer maxConnections;

	/** <i>Generated property</i> for <code>SolrClientConfig.maxConnectionsPerHost</code> property defined at extension <code>solrfacetsearch</code>. */
		
	private Integer maxConnectionsPerHost;

	/** <i>Generated property</i> for <code>SolrClientConfig.tcpNoDelay</code> property defined at extension <code>solrfacetsearch</code>. */
		
	private boolean tcpNoDelay;
	
	public SolrClientConfig()
	{
		// default constructor
	}
	
		
	
	public void setAliveCheckInterval(final Integer aliveCheckInterval)
	{
		this.aliveCheckInterval = aliveCheckInterval;
	}

		
	
	public Integer getAliveCheckInterval() 
	{
		return aliveCheckInterval;
	}
	
		
	
	public void setConnectionTimeout(final Integer connectionTimeout)
	{
		this.connectionTimeout = connectionTimeout;
	}

		
	
	public Integer getConnectionTimeout() 
	{
		return connectionTimeout;
	}
	
		
	
	public void setSocketTimeout(final Integer socketTimeout)
	{
		this.socketTimeout = socketTimeout;
	}

		
	
	public Integer getSocketTimeout() 
	{
		return socketTimeout;
	}
	
		
	
	public void setMaxConnections(final Integer maxConnections)
	{
		this.maxConnections = maxConnections;
	}

		
	
	public Integer getMaxConnections() 
	{
		return maxConnections;
	}
	
		
	
	public void setMaxConnectionsPerHost(final Integer maxConnectionsPerHost)
	{
		this.maxConnectionsPerHost = maxConnectionsPerHost;
	}

		
	
	public Integer getMaxConnectionsPerHost() 
	{
		return maxConnectionsPerHost;
	}
	
		
	
	public void setTcpNoDelay(final boolean tcpNoDelay)
	{
		this.tcpNoDelay = tcpNoDelay;
	}

		
	
	public boolean isTcpNoDelay() 
	{
		return tcpNoDelay;
	}
	


}