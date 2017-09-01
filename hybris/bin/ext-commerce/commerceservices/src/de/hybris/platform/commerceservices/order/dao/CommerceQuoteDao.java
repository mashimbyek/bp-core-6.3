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
package de.hybris.platform.commerceservices.order.dao;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.enums.QuoteState;
import de.hybris.platform.core.model.order.QuoteModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;
import de.hybris.platform.store.BaseStoreModel;

import java.util.Set;


/**
 * DAO interface for handling {@link QuoteModel}
 */
public interface CommerceQuoteDao extends GenericDao<QuoteModel>
{

	/**
	 * Returns a paged list of maximum version (i.e. active quotes) of each quote for the specified user & store,
	 * filtered by accessible quote states.
	 *
	 * @param customerModel
	 *           the customer to retrieve quotes for
	 * @param store
	 *           the store to retrieve quotes for
	 * @param pageableData
	 *           the pagination settings
	 * @param quoteStates
	 *           the quote states the user can access
	 * @return the paged search result
	 * @throws IllegalArgumentException
	 *            if any of the parameters is null or the set of quote states is empty
	 */
	SearchPageData<QuoteModel> findQuotesByCustomerAndStore(CustomerModel customerModel, BaseStoreModel store,
			PageableData pageableData, Set<QuoteState> quoteStates);


	/**
	 * Returns a unique quote of maximum version (i.e. active quote) for the specified user, store & code, filtered by
	 * accessible quote states.
	 *
	 * @param customerModel
	 *           the customer to retrieve quotes for
	 * @param store
	 *           the store to retrieve quotes for
	 * @param quoteCode
	 *           the quote code to search for
	 * @param quoteStates
	 *           the quote states the user can access
	 * @return the unique quote matching the search parameters
	 * @throws IllegalArgumentException
	 *            if any of the parameters is null or the set of quote states is empty
	 * @throws ModelNotFoundException
	 *            if no results were found
	 * @throws AmbiguousIdentifierException
	 *            if more than one quote matches the search parameters
	 */
	QuoteModel findUniqueQuoteByCodeAndCustomerAndStore(CustomerModel customerModel, BaseStoreModel store, String quoteCode,
			Set<QuoteState> quoteStates);

	/**
	 * Returns the total number of quotes for the specified user and store.
	 *
	 * @param customerModel
	 *           the customer to get the quote count for
	 * @param store
	 *           the store to get the quote count for
	 * @param quoteStates
	 *           the list of states the user can access
	 * @return the quote count
	 * @throws IllegalArgumentException
	 *            if any of the parameters is null or the set of quote states is empty
	 */
	Integer getQuotesCountForCustomerAndStore(CustomerModel customerModel, BaseStoreModel store, Set<QuoteState> quoteStates);
}
