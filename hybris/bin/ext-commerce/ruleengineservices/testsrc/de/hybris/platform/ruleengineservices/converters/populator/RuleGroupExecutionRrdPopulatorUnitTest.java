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
package de.hybris.platform.ruleengineservices.converters.populator;

import static de.hybris.platform.ruleengineservices.constants.RuleEngineServicesConstants.DEFAULT_RULEGROUP_CODE_PROPERTY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.ruleengine.model.AbstractRuleEngineRuleModel;
import de.hybris.platform.ruleengineservices.rrd.RuleGroupExecutionRRD;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.configuration.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class RuleGroupExecutionRrdPopulatorUnitTest
{

	private static final String RULE_GROUP_CODE = "RULE_GROUP_CODE";
	private static final String DEFAULT_RULE_GROUP_CODE = "DEFAULT_RULE_GROUP_CODE";

	@InjectMocks
	private RuleGroupExecutionRrdPopulator populator;

	@Mock
	private ConfigurationService configurationService;

	@Mock
	private Configuration configuration;

	@Mock
	private AbstractRuleEngineRuleModel source;

	@Before
	public void setUp() throws Exception
	{
		populator = new RuleGroupExecutionRrdPopulator();
		populator.setConfigurationService(configurationService);
		when(configurationService.getConfiguration()).thenReturn(configuration);
		when(configuration.getString(DEFAULT_RULEGROUP_CODE_PROPERTY)).thenReturn(DEFAULT_RULE_GROUP_CODE);
		when(source.getRuleGroupCode()).thenReturn(RULE_GROUP_CODE);
	}

	@Test
	public void testPopulate()
	{
		final RuleGroupExecutionRRD target = new RuleGroupExecutionRRD();
		populator.populate(source, target);
		assertEquals(RULE_GROUP_CODE, target.getCode());
		assertTrue(MapUtils.isEmpty(target.getExecutedRules()));
	}

	@Test
	public void testDefaultRuleGroupCode()
	{
		// force default value
		when(source.getRuleGroupCode()).thenReturn(null);

		final RuleGroupExecutionRRD target = new RuleGroupExecutionRRD();
		populator.populate(source, target);
		assertEquals(DEFAULT_RULE_GROUP_CODE, target.getCode());
	}
}
