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
package de.hybris.platform.consignmenttrackingmockaddon.setup;

import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.consignmenttrackingmockaddon.constants.ConsignmenttrackingmockaddonConstants;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetup.Process;
import de.hybris.platform.core.initialization.SystemSetup.Type;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;
import de.hybris.platform.core.initialization.SystemSetupParameterMethod;

import java.util.ArrayList;
import java.util.List;


@SystemSetup(extension = ConsignmenttrackingmockaddonConstants.EXTENSIONNAME)
public class ConsignmentTrackingMockSystemSetup extends AbstractSystemSetup
{
	private static final String IMPORT_MOCK_CARRIER = "Import Mock Carrier";

	@Override
	@SystemSetupParameterMethod
	public List<SystemSetupParameter> getInitializationOptions()
	{
		final List<SystemSetupParameter> params = new ArrayList<SystemSetupParameter>();
		params.add(createBooleanSystemSetupParameter(IMPORT_MOCK_CARRIER, "Import Mock Carrier", true));
		return params;
	}

	@SystemSetup(type = Type.PROJECT, process = Process.ALL)
	public void createEssentialData(final SystemSetupContext context)
	{
		final String extensionName = context.getExtensionName();
		final String fileLocation = "/%s/import/common/common-addon-extra.impex";
		getSetupImpexService().importImpexFile(String.format(fileLocation, extensionName), false);
	}
}
