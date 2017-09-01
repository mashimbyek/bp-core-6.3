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
package de.hybris.platform.stocknotificationservices.setup;


import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.commerceservices.setup.SetupImpexService;
import de.hybris.platform.commerceservices.setup.data.ImportData;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetup.Process;
import de.hybris.platform.core.initialization.SystemSetup.Type;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;
import de.hybris.platform.core.initialization.SystemSetupParameterMethod;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.stocknotificationservices.constants.StocknotificationservicesConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;


@SystemSetup(extension = StocknotificationservicesConstants.EXTENSIONNAME)
public class StocknotificationservicesSystemSetup extends AbstractSystemSetup
{
	public static final String ELECTRONICS = "electronics";
	public static final String APPAREL = "apparel";
	public static final String POWERTOOLS = "powertools";
	private static final String IMPORT_EMAIL_CONTENT = "import Email Template";


	private SetupImpexService setupImpexService;

	private BaseSiteService baseSiteService;

	@Override
	@SystemSetupParameterMethod
	public List<SystemSetupParameter> getInitializationOptions()
	{
		final List<SystemSetupParameter> params = new ArrayList<SystemSetupParameter>();
		params.add(createBooleanSystemSetupParameter(IMPORT_EMAIL_CONTENT, "Import Email Template", true));
		return params;
	}



	@SystemSetup(process = SystemSetup.Process.ALL, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		importStockJobData(StocknotificationservicesConstants.EXTENSIONNAME);
	}

	protected void importStockJobData(final String extensionName)
	{
		getSetupImpexService().importImpexFile(String.format("/%s/import/essential/stockLevelStatusJob.impex", extensionName),
				false);
	}


	@Override
	public SetupImpexService getSetupImpexService()
	{
		return setupImpexService;
	}

	@Override
	@Required
	public void setSetupImpexService(final SetupImpexService setupImpexService)
	{
		this.setupImpexService = setupImpexService;
	}



	public BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	@Required
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}

	@SystemSetup(type = Type.PROJECT, process = Process.UPDATE)
	public void createEssentialData(final SystemSetupContext context)
	{
		final BaseSiteModel electronicsSite = getBaseSiteService().getBaseSiteForUID(ELECTRONICS);
		final BaseSiteModel powertoolsSite = getBaseSiteService().getBaseSiteForUID(POWERTOOLS);
		final List<ImportData> importData = new ArrayList<ImportData>();

		if (electronicsSite != null)
		{
			addImportData(importData, ELECTRONICS);
		}
		if (powertoolsSite != null)
		{
			addImportData(importData, POWERTOOLS);
		}
		if (getBooleanSystemSetupParameter(context, IMPORT_EMAIL_CONTENT))
		{
			updateEmailTemplate(context, importData);
		}
	}

	public void updateEmailTemplate(final SystemSetupContext context, final List<ImportData> importData)
	{
		final String extensionName = context.getExtensionName();
		for (final ImportData data : importData)
		{
			for (final String contentCatalogName : data.getContentCatalogNames())
			{
				getSetupImpexService().importImpexFile(
						String.format("/%s/import/contentCatalogs/%sContentCatalog/email-content.impex", extensionName,
								contentCatalogName), false);
			}
		}
		synchronizeContentCatalog(this, context, importData);
	}

	private void synchronizeContentCatalog(final AbstractSystemSetup systemSetup, final SystemSetupContext context,
			final List<ImportData> importData)
	{
		for (final ImportData data : importData)
		{
			for (final String contentCatalogName : data.getContentCatalogNames())
			{
				systemSetup.logInfo(context, String.format("Begin synchronizing Content Catalog [%s]", contentCatalogName));
				getSetupSyncJobService().createContentCatalogSyncJob(String.format("%sContentCatalog", contentCatalogName));
				final PerformResult syncCronJobResult = getSetupSyncJobService().executeCatalogSyncJob(
						String.format("%sContentCatalog", contentCatalogName));
				if (isSyncRerunNeeded(syncCronJobResult))
				{
					systemSetup.logInfo(context, String.format("Content Catalog [%s] sync has issues.", contentCatalogName));
				}
			}
		}
	}

	/**
	 * add importData to importDatalist
	 *
	 * @param importDataList
	 *           is used for setting parameter of ProductCatalogName,ContentCatalogName,StoreName. That is used for
	 *           import data.
	 * @param site
	 *           is parameter for chose which site's data should be import.
	 */
	protected void addImportData(final List<ImportData> importDataList, final String site)
	{
		final ImportData importData = new ImportData();

		importData.setProductCatalogName(site);
		importData.setContentCatalogNames(Arrays.asList(site));
		importData.setStoreNames(Arrays.asList(site));

		importDataList.add(importData);
	}
}
