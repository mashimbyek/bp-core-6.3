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
package de.hybris.platform.droolsruleengineservices.compiler.impl;

import static com.google.common.collect.Lists.newArrayList;
import static de.hybris.platform.ruleengine.constants.RuleEngineConstants.DEFAULT_DROOLS_DATE_FORMAT;
import static de.hybris.platform.ruleengine.constants.RuleEngineConstants.DROOLS_DATE_FORMAT_KEY;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.droolsruleengineservices.compiler.DroolsRuleActionsGenerator;
import de.hybris.platform.droolsruleengineservices.compiler.DroolsRuleConditionsGenerator;
import de.hybris.platform.droolsruleengineservices.compiler.DroolsRuleMetadataGenerator;
import de.hybris.platform.ruleengine.RuleEngineActionResult;
import de.hybris.platform.ruleengine.RuleEngineService;
import de.hybris.platform.ruleengine.model.DroolsRuleModel;
import de.hybris.platform.ruleengineservices.compiler.RuleCompilerContext;
import de.hybris.platform.ruleengineservices.compiler.RuleCompilerException;
import de.hybris.platform.ruleengineservices.compiler.RuleIr;
import de.hybris.platform.ruleengineservices.compiler.RuleIrAction;
import de.hybris.platform.ruleengineservices.compiler.RuleIrCondition;
import de.hybris.platform.ruleengineservices.compiler.RuleIrVariablesContainer;
import de.hybris.platform.ruleengineservices.model.SourceRuleModel;
import de.hybris.platform.ruleengineservices.rule.services.RuleParametersService;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.configuration.Configuration;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultDroolsRuleTargetCodeGeneratorTest
{
	public static final String RULE_UUID = "7be85ae9-8f69-4d51-a3b4-a4b08b457798";
	public static final String RULE_CODE = "rule_code";
	public static final String RULE_NAME = "rule_name";

	@Rule
	public ExpectedException expectedException = ExpectedException.none(); //NOPMD

	@Mock
	private CatalogUnawareMediaModel mediaModel;

	@Mock
	private SourceRuleModel sourceRule;

	@Mock
	private DroolsRuleModel droolsRule;

	@Mock
	private RuleCompilerContext compilerContext;

	@Mock
	private RuleIrAction action;

	@Mock
	private RuleIrCondition condition;

	@Mock
	private ModelService modelService;

	@Mock
	private RuleEngineService platformRuleEngineService;

	@Mock
	private DroolsRuleConditionsGenerator droolsRuleConditionsGenerator;

	@Mock
	private DroolsRuleActionsGenerator droolsRuleActionsGenerator;

	@Mock
	private ConfigurationService configurationService;
	@Mock
	private Configuration configuration;
	@Mock
	private RuleParametersService ruleParametersService;
	@Mock
	private DroolsRuleMetadataGenerator droolsRuleMetadataGenerator;

	@Mock
	private CommonI18NService commonI18NService;
	@InjectMocks
	private DefaultDroolsRuleTargetCodeGenerator droolsRuleTargetCodeGenerator;

	@Before
	public void setUp()
	{
		final RuleEngineActionResult result = new RuleEngineActionResult();
		result.setActionFailed(false);

		given(modelService.create(DroolsRuleModel.class)).willReturn(droolsRule);
		given(modelService.create(CatalogUnawareMediaModel.class)).willReturn(mediaModel);

		given(commonI18NService.getAllLanguages()).willReturn(newArrayList());

		given(sourceRule.getUuid()).willReturn(RULE_UUID);
		given(sourceRule.getCode()).willReturn(RULE_CODE);
		given(sourceRule.getName()).willReturn(RULE_NAME);
		given(sourceRule.getStartDate()).willReturn(new Date());
		given(sourceRule.getEndDate()).willReturn(new Date());

		given(compilerContext.getRule()).willReturn(sourceRule);
		given(platformRuleEngineService.getRuleForUuid(RULE_UUID)).willReturn(droolsRule);
		given(platformRuleEngineService.updateEngineRule(droolsRule)).willReturn(result);
		given(configurationService.getConfiguration()).willReturn(configuration);

	}

	@Test
	public void nullTest() throws RuleCompilerException
	{
		// expect
		expectedException.expect(IllegalArgumentException.class);

		// when
		droolsRuleTargetCodeGenerator.generate(compilerContext, null);
	}

	@Test
	public void emptyActionsTest() throws Exception
	{
		// given
		final RuleIr ruleIr = new RuleIr();
		ruleIr.setVariablesContainer(new RuleIrVariablesContainer());
		ruleIr.setConditions(Collections.singletonList(condition));
		ruleIr.setActions(emptyList());

		// expect
		expectedException.expect(UnknownIdentifierException.class);

		// when
		droolsRuleTargetCodeGenerator.generate(compilerContext, ruleIr);
	}

	@Test
	public void testGetFormattedDateString()
	{
		when(configuration.getString(DROOLS_DATE_FORMAT_KEY,
					 DEFAULT_DROOLS_DATE_FORMAT)).thenReturn("dd-MMM-yyyy HH:mm:ss");

		Calendar calendar = getCalendarForLocale(Locale.getDefault());

		String formattedDateString = droolsRuleTargetCodeGenerator.getFormattedDateString(calendar.getTime());
		assertThat(formattedDateString).isEqualTo("01-Jan-2016 00:00:00");

		Locale.setDefault(new Locale("pl", "PL"));
		formattedDateString = droolsRuleTargetCodeGenerator.getFormattedDateString(calendar.getTime());
		assertThat(formattedDateString).isEqualTo("01-Jan-2016 00:00:00");
	}

	private Calendar getCalendarForLocale(final Locale locale)
	{
		final Calendar calendar = Calendar.getInstance(locale);
		calendar.set(Calendar.YEAR, 2016);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar;
	}

}
