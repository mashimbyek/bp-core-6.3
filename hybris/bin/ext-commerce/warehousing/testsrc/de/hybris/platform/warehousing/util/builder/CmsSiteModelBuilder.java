/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 *
 */
package de.hybris.platform.warehousing.util.builder;

import com.google.common.collect.Lists;
import de.hybris.platform.cms2.model.contents.ContentCatalogModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.store.BaseStoreModel;
import java.util.List;


public class CmsSiteModelBuilder
{
	private final CMSSiteModel model;

	private CmsSiteModelBuilder()
	{
		model = new CMSSiteModel();
	}

	private CMSSiteModel getModel()
	{
		return this.model;
	}

	public static CmsSiteModelBuilder aModel()
	{
		return new CmsSiteModelBuilder();
	}

	public CMSSiteModel build()
	{
		return getModel();
	}

	public CmsSiteModelBuilder withUid(final String uid)
	{
		getModel().setActive(true);
		getModel().setUid(uid);
		return this;
	}

	public CmsSiteModelBuilder withChannel(final SiteChannel channel)
	{
		getModel().setChannel(channel);
		return this;
	}

	public CmsSiteModelBuilder withStores(final BaseStoreModel... stores)
	{
		getModel().setStores(Lists.newArrayList(stores));
		return this;
	}

	public CmsSiteModelBuilder withContentCatalogs(final List<ContentCatalogModel> contentCatalogModels)
	{
		getModel().setContentCatalogs(contentCatalogModels);
		return this;
	}

}
