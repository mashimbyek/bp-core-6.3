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
package de.hybris.platform.customerinterestsservices.setup;

import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetupParameter;
import de.hybris.platform.core.initialization.SystemSetupParameterMethod;
import de.hybris.platform.customerinterestsservices.constants.CustomerinterestsservicesConstants;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@SystemSetup(extension = CustomerinterestsservicesConstants.EXTENSIONNAME)
public class CustomerInterestSetup extends AbstractSystemSetup
{
	private static final String IMPORT_CRONJOB = "Import Cronjob";

	@Override
	@SystemSetupParameterMethod
	public List<SystemSetupParameter> getInitializationOptions()
	{
		final List<SystemSetupParameter> params = new ArrayList<>();
		params.add(createBooleanSystemSetupParameter(IMPORT_CRONJOB, IMPORT_CRONJOB, true));
		return params;
	}

	@SystemSetup(process = SystemSetup.Process.ALL, type = SystemSetup.Type.ALL)
	public void createCronJobData()
	{
		getSetupImpexService().importImpexFile(
				String.format("/%s/import/interests.impex", CustomerinterestsservicesConstants.EXTENSIONNAME), false);
	}

	private InputStream getImageStream()
	{
		return CustomerInterestSetup.class.getResourceAsStream("/customerinterestsservices/sap-hybris-platform.png");
	}
}
