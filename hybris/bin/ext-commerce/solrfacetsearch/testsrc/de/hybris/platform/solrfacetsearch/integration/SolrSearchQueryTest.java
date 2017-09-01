/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.platform.solrfacetsearch.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.config.exceptions.FacetConfigServiceException;
import de.hybris.platform.solrfacetsearch.search.FacetValueField;
import de.hybris.platform.solrfacetsearch.search.FieldNameTranslator;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import de.hybris.platform.solrfacetsearch.search.SolrQueryConverter;
import de.hybris.platform.solrfacetsearch.search.SolrQueryPostProcessor;
import de.hybris.platform.solrfacetsearch.search.impl.DefaultSolrQueryConverter;
import de.hybris.platform.solrfacetsearch.solr.Index;
import de.hybris.platform.solrfacetsearch.solr.SolrSearchProvider;
import de.hybris.platform.solrfacetsearch.solr.SolrSearchProviderFactory;
import de.hybris.platform.solrfacetsearch.solr.exceptions.SolrServiceException;
import de.hybris.platform.testframework.Transactional;

import java.io.IOException;
import java.util.Iterator;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.DirectXmlRequest;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.fest.util.Collections;
import org.junit.Test;


@Transactional
public class SolrSearchQueryTest extends AbstractIntegrationTest
{
	@Resource
	private FieldNameTranslator fieldNameTranslator;

	@Resource
	private SolrQueryConverter solrQueryConverter;

	@Resource(name = "solrSearchProviderFactory")
	private SolrSearchProviderFactory solrSearchProviderFactory;

	@Override
	protected void loadData()
			throws ImpExException, IOException, FacetConfigServiceException, SolrServiceException, SolrServerException
	{
		//create a file reader to get the string in the xml file
		final String xmlFile = readFile("/test/integration/SolrSearchQuery.xml");
		final DirectXmlRequest xmlRequest = new DirectXmlRequest("/update", xmlFile);
		final FacetSearchConfig facetSearchConfig = getFacetSearchConfig();
		final IndexedType indexedType = facetSearchConfig.getIndexConfig().getIndexedTypes().values().iterator().next();

		final SolrSearchProvider solrSearchProvider = solrSearchProviderFactory.getSearchProvider(facetSearchConfig, indexedType);
		final Index index = solrSearchProvider.resolveIndex(facetSearchConfig, indexedType, "1");

		solrSearchProvider.createIndex(index);

		final SolrClient solrClient = solrSearchProvider.getClientForIndexing(index);
		solrClient.request(xmlRequest, index.getName());
		solrClient.commit(index.getName());
		solrClient.close();
	}

	@Test
	public void testSearchQuery() throws Exception
	{
		final FacetSearchConfig facetSearchConfig = getFacetSearchConfig();
		final IndexedType indexedType = facetSearchConfig.getIndexConfig().getIndexedTypes().values().iterator().next();
		final SolrSearchProvider solrSearchProvider = solrSearchProviderFactory.getSearchProvider(facetSearchConfig, indexedType);
		final Index index = solrSearchProvider.resolveIndex(facetSearchConfig, indexedType, "1");
		final SolrClient solrClient = solrSearchProvider.getClient(index);

		//test number of all imported products
		SearchQuery query = new SearchQuery(facetSearchConfig, indexedType);

		String language = "de";
		query.setLanguage(language);
		final String currency = "eur";
		query.setCurrency(currency);

		assertEquals(10, solrClient.query(index.getName(), solrQueryConverter.convertSolrQuery(query)).getResults().getNumFound());

		query = new SearchQuery(facetSearchConfig, indexedType);
		query.setLanguage(language);
		query.setCurrency(currency);

		//test single field
		final String FIELD_NAME = "name";
		final String FIELD_CATEGORY = "categoryCode";
		query.addFacetValue(FIELD_NAME, "Dell");
		assertEquals(1, solrClient.query(index.getName(), solrQueryConverter.convertSolrQuery(query)).getResults().getNumFound());

		query = new SearchQuery(facetSearchConfig, indexedType);
		query.setLanguage(language);
		query.setCurrency(currency);
		query.addFacetValue(FIELD_NAME, "maxtor");

		assertEquals(2, solrClient.query(index.getName(), solrQueryConverter.convertSolrQuery(query)).getResults().getNumFound());

		query = new SearchQuery(facetSearchConfig, indexedType);
		query.setLanguage(language);
		query.setCurrency(currency);
		query.addFacetValue(FIELD_NAME, "notFound");

		assertEquals(0, solrClient.query(index.getName(), solrQueryConverter.convertSolrQuery(query)).getResults().getNumFound());

		//test multifields
		language = "en";

		query = new SearchQuery(facetSearchConfig, indexedType);
		query.setLanguage(language);
		query.setCurrency(currency);

		query.addFacetValue(FIELD_CATEGORY, "camera");
		assertEquals(3, solrClient.query(index.getName(), solrQueryConverter.convertSolrQuery(query)).getResults().getNumFound());
		query.addFacetValue(FIELD_NAME, "sony");
		assertEquals(2, solrClient.query(index.getName(), solrQueryConverter.convertSolrQuery(query)).getResults().getNumFound());

		//test remove field with value
		final Iterator<FacetValueField> it = query.getFacetValues().iterator();
		while (it.hasNext())
		{
			final FacetValueField facet = it.next();
			if (facet.getField().equals(FIELD_NAME) && facet.getValues().contains("sony"))
			{
				it.remove();
			}
		}

		assertEquals(3, solrClient.query(index.getName(), solrQueryConverter.convertSolrQuery(query)).getResults().getNumFound());

		solrClient.close();
	}

	@Test
	public void testSearchQueryWithPreProcessors() throws Exception
	{
		final FacetSearchConfig facetSearchConfig = getFacetSearchConfig();
		final IndexedType indexedType = facetSearchConfig.getIndexConfig().getIndexedTypes().values().iterator().next();
		final SolrSearchProvider solrSearchProvider = solrSearchProviderFactory.getSearchProvider(facetSearchConfig, indexedType);
		final Index index = solrSearchProvider.resolveIndex(facetSearchConfig, indexedType, "1");
		final SolrClient solrClient = solrSearchProvider.getClient(index);

		//test number of all imported products
		final SearchQuery query = new SearchQuery(facetSearchConfig, indexedType);

		final String language = "de";
		query.setLanguage(language);
		final String currency = "eur";
		query.setCurrency(currency);

		final DefaultSolrQueryConverter converter = new DefaultSolrQueryConverter();
		converter.setFieldNameTranslator(fieldNameTranslator);
		converter.setFacetSort(DefaultSolrQueryConverter.FacetSort.COUNT);
		converter.setQueryPostProcessors(Collections.<SolrQueryPostProcessor> list(new SolrQueryPostProcessor()
		{
			@Override
			public SolrQuery process(final SolrQuery query, final SearchQuery solrSearchQuery)
			{
				query.setSort("id", ORDER.asc);
				query.setStart(Integer.valueOf(0));
				query.setRows(Integer.valueOf(10));
				return query;
			}
		}));

		Object prev = null;
		SolrDocumentList results = solrClient.query(index.getName(), converter.convertSolrQuery(query)).getResults();
		assertEquals("Result size limited in PostProcessor", 10, results.size());
		for (final SolrDocument doc : results)
		{
			final Object fieldValue = doc.getFieldValue("id");
			if (prev != null)
			{
				assertTrue(prev instanceof Comparable);
				assertTrue(((Comparable) prev).compareTo(fieldValue) < 0);
			}
			prev = fieldValue;
		}
		converter.setQueryPostProcessors(Collections.<SolrQueryPostProcessor> list(new SolrQueryPostProcessor()
		{

			@Override
			public SolrQuery process(final SolrQuery query, final SearchQuery solrSearchQuery)
			{
				query.setSort("id", ORDER.desc);
				query.setStart(Integer.valueOf(2));
				query.setRows(Integer.valueOf(10));
				return query;
			}
		}));
		results = solrClient.query(index.getName(), converter.convertSolrQuery(query)).getResults();
		assertEquals("Result size limited in PostProcessor", 8, results.size());
		for (final SolrDocument doc : results)
		{
			final Object fieldValue = doc.getFieldValue("id");
			if (prev != null)
			{
				assertTrue(prev instanceof Comparable);
				assertTrue(((Comparable) prev).compareTo(fieldValue) > 0);
			}
			prev = fieldValue;
		}
		converter.setQueryPostProcessors(Collections.<SolrQueryPostProcessor> list(new SolrQueryPostProcessor()
		{
			@Override
			public SolrQuery process(final SolrQuery query, final SearchQuery solrSearchQuery)
			{
				query.setSort("id", ORDER.asc);
				return query;
			}
		}, new SolrQueryPostProcessor()
		{

			@Override
			public SolrQuery process(final SolrQuery query, final SearchQuery solrSearchQuery)
			{
				query.setStart(Integer.valueOf(3));
				return query;
			}
		}, new SolrQueryPostProcessor()
		{

			@Override
			public SolrQuery process(final SolrQuery query, final SearchQuery solrSearchQuery)
			{
				query.setRows(Integer.valueOf(6));
				return query;
			}
		}));
		results = solrClient.query(index.getName(), converter.convertSolrQuery(query)).getResults();
		assertEquals("Result size limited in PostProcessor", 6, results.size());
		for (final SolrDocument doc : results)
		{
			final Object fieldValue = doc.getFieldValue("id");
			if (prev != null)
			{
				assertTrue(prev instanceof Comparable);
				assertTrue(((Comparable) prev).compareTo(fieldValue) <= 0);
			}
			prev = fieldValue;
		}

		solrClient.close();
	}
}
