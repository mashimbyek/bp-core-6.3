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
package de.hybris.platform.commercesearch.searchandizing.heroproduct.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.ManualTest;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercesearch.searchandizing.AbstractSearchandisingIntegrationTest;
import de.hybris.platform.commercesearch.searchandizing.heroproduct.HeroProductFacade;
import de.hybris.platform.commerceservices.impersonation.ImpersonationService.Executor;
import de.hybris.platform.commerceservices.search.ProductSearchService;
import de.hybris.platform.commerceservices.search.facetdata.ProductCategorySearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryTermData;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;


@ManualTest
public class HeroProductIntegrationTest extends AbstractSearchandisingIntegrationTest
{

	@Resource
	private HeroProductFacade heroProductFacade;

	protected final String CATEGORY_CODE_WITH_HEROES = "575";
	protected final String CATEGORY_CODE_WITHOUT_HEROES = "576";
	protected final String[] HERO_PRODUCT_CODES =
	{ "1391319", "816802" };


	@Before
	public void addHeroProducts() throws Exception
	{
		executeInContext(new Executor<Object, Exception>()
		{

			@Override
			public Object execute()
			{
				for (int i = 0; i < HERO_PRODUCT_CODES.length; i++)
				{
					final String code = HERO_PRODUCT_CODES[i];
					heroProductFacade.addHeroProduct(CATEGORY_CODE_WITH_HEROES, code);
				}
				return null;
			}
		});
	}

	@Resource(name = "commerceProductSearchService")
	private ProductSearchService<SolrSearchQueryData, SearchResultValueData, ProductCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel>> productSearchService;

	@Test
	public void categorySearchShouldHaveHeroProductOnTop() throws Exception
	{
		final PageableData pageable = new PageableData();
		ProductCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel> result;
		result = executeInContext(
				new Executor<ProductCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel>, Exception>()
				{
					@Override
					public ProductCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel> execute()
					{
						return productSearchService.categorySearch(CATEGORY_CODE_WITH_HEROES, pageable);
					}

				});
		assertHeroProductsOnTop(result, true);
	}


	@Test
	public void facetSearchShouldHaveHeroProductOnTop() throws Exception
	{
		final SolrSearchQueryData searchQuery = new SolrSearchQueryData();
		searchQuery.setFreeTextSearch("camera");
		final SolrSearchQueryTermData queryTerm = new SolrSearchQueryTermData();
		queryTerm.setKey("category");
		queryTerm.setValue(CATEGORY_CODE_WITH_HEROES);
		searchQuery.setFilterTerms(Collections.singletonList(queryTerm));
		ProductCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel> result;
		result = executeInContext(
				new Executor<ProductCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel>, Exception>()
				{
					@Override
					public ProductCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel> execute()
					{

						return productSearchService.searchAgain(searchQuery, null);
					}

				});
		assertHeroProductsOnTop(result, true);
	}

	private void assertHeroProductsOnTop(
			final ProductCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel> result,
			final boolean onTop)
	{
		assertNotNull("search result cannot be null", result);
		final List<SearchResultValueData> results = result.getResults();
		assertNotNull("search result list cannot be null", results);
		assertTrue("Size of results must be >= " + HERO_PRODUCT_CODES.length, results.size() >= HERO_PRODUCT_CODES.length);
		for (int i = 0; i < HERO_PRODUCT_CODES.length; i++)
		{
			final SearchResultValueData resultValue = results.get(i);
			final String productCode = HERO_PRODUCT_CODES[i];
			assertEquals(String.format("Hero product %s on top: %b", productCode, Boolean.valueOf(onTop)), Boolean.valueOf(onTop),
					Boolean.valueOf(productCode.equals(resultValue.getValues().get("code"))));
		}
	}

	@Test
	public void categorySearchShouldNotHaveHeroProductOnTopWhenNoCatergoryIsSelected() throws Exception
	{
		ProductCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel> result;
		result = executeInContext(
				new Executor<ProductCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel>, Exception>()
				{
					@Override
					public ProductCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel> execute()
					{

						return productSearchService.textSearch("camera", null);
					}

				});
		assertHeroProductsOnTop(result, false);
	}

	@Test
	public void categorySearchShouldNotHaveHeroProductOnTop() throws Exception
	{
		final PageableData pageable = new PageableData();
		ProductCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel> result;
		result = executeInContext(
				new Executor<ProductCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel>, Exception>()
				{
					@Override
					public ProductCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel> execute()
					{
						return productSearchService.categorySearch(CATEGORY_CODE_WITHOUT_HEROES, pageable);
					}

				});
		assertHeroProductsOnTop(result, false);
	}


	@Test
	public void facetSearchShouldNotHaveHeroProductOnTop() throws Exception
	{
		final SolrSearchQueryData searchQuery = new SolrSearchQueryData();
		searchQuery.setFreeTextSearch("camera");
		final SolrSearchQueryTermData queryTerm = new SolrSearchQueryTermData();
		queryTerm.setKey("category");
		queryTerm.setValue(CATEGORY_CODE_WITHOUT_HEROES);
		searchQuery.setFilterTerms(Collections.singletonList(queryTerm));
		ProductCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel> result;
		result = executeInContext(
				new Executor<ProductCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel>, Exception>()
				{
					@Override
					public ProductCategorySearchPageData<SolrSearchQueryData, SearchResultValueData, CategoryModel> execute()
					{

						return productSearchService.searchAgain(searchQuery, null);
					}

				});
		assertHeroProductsOnTop(result, false);
	}



}
