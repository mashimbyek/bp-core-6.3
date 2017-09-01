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
package de.hybris.platform.ruleengine.init.impl;

import static de.hybris.platform.ruleengine.impl.DefaultPlatformRuleEngineService.SWAPPING_IS_BLOCKING;
import static de.hybris.platform.ruleengine.init.ConcurrentMapFactory.WORKER_MAP_CONCURRENCY_LEVEL;
import static de.hybris.platform.ruleengine.init.ConcurrentMapFactory.WORKER_MAP_INITIAL_CAPACITY;
import static de.hybris.platform.ruleengine.init.ConcurrentMapFactory.WORKER_MAP_LOAD_FACTOR;
import static de.hybris.platform.ruleengine.init.impl.DefaultRuleEngineKieModuleSwapper.WORKER_PRE_DESTROY_TIMEOUT;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.Tenant;
import de.hybris.platform.ruleengine.RuleEngineActionResult;
import de.hybris.platform.ruleengine.cache.RuleEngineCacheService;
import de.hybris.platform.ruleengine.cache.KIEModuleCacheBuilder;
import de.hybris.platform.ruleengine.dao.RulesModuleDao;
import de.hybris.platform.ruleengine.impl.KieContainerListener;
import de.hybris.platform.ruleengine.init.ConcurrentMapFactory;
import de.hybris.platform.ruleengine.model.DroolsKIEModuleModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.function.Supplier;

import org.apache.commons.configuration.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.builder.Results;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.runtime.KieContainer;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultRuleEngineKieModuleSwapperUnitTest
{

	private static final String MODULE_NAME = "MODULE_NAME";
	private static final String DEPLOYED_VERSION = "DEPLOYED_VERSION";

	@InjectMocks
	private DefaultRuleEngineKieModuleSwapper moduleSwapper;
	@InjectMocks
	private ConcurrentMapFactory concurrentMapFactory;
	@Mock
	private ConfigurationService configurationService;
	@Mock
	private KieServices kieServices;
	@Mock
	private KieFileSystem kfs;
	@Mock
	private KieModuleModel kieModuleModel;
	@Mock
	private KieBuilder kieBuilder;
	@Mock
	private Results kieBuilderResults;
	@Mock
	private KieModule kieModule;
	@Mock
	private KieRepository kieRepository;
	@Mock
	private Tenant currentTenant;
	@Mock
	private KieContainer kieContainer;
	@Mock
	private RuleEngineActionResult ruleEngineActionResult;
	@Mock
	private KIEModuleCacheBuilder cache;
	@Mock
	private KieContainerListener kieContainerListener;
	@Mock
	private DroolsKIEModuleModel droolsModule;
	@Mock
	private ReleaseId releaseId;
	@Mock
	private Configuration configuration;
	@Mock
	private RulesModuleDao rulesModuleDao;
	@Mock
	private RuleEngineCacheService ruleEngineCacheService;

	@Before
	public void setUp()
	{
		moduleSwapper.setConcurrentMapFactory(concurrentMapFactory);
		moduleSwapper.setRuleEngineCacheService(ruleEngineCacheService);
		when(configurationService.getConfiguration()).thenReturn(configuration);
		when(configuration.getLong(eq(WORKER_PRE_DESTROY_TIMEOUT), anyLong())).thenReturn(5000L);
		when(configuration.getInt(eq(WORKER_MAP_INITIAL_CAPACITY), anyInt())).thenReturn(3);
		when(configuration.getFloat(eq(WORKER_MAP_LOAD_FACTOR), anyFloat())).thenReturn(0.75F);
		when(configuration.getInt(eq(WORKER_MAP_CONCURRENCY_LEVEL), anyInt())).thenReturn(2);
		when(configuration.getBoolean(eq(SWAPPING_IS_BLOCKING), anyBoolean())).thenReturn(false);

		moduleSwapper.setup();
		when(kieServices.newReleaseId(anyString(), anyString(), anyString())).thenReturn(releaseId);
		when(kieServices.newKieFileSystem()).thenReturn(kfs);
		when(kieServices.newKieModuleModel()).thenReturn(kieModuleModel);
		when(kieServices.newKieBuilder(kfs)).thenReturn(kieBuilder);
		when(kieServices.getRepository()).thenReturn(kieRepository);
		when(kieServices.newKieContainer(releaseId)).thenReturn(kieContainer);
		when(kieBuilder.getKieModule()).thenReturn(kieModule);
		when(kieBuilder.getResults()).thenReturn(kieBuilderResults);
		when(kieModule.getReleaseId()).thenReturn(releaseId);
		when(kieContainer.getReleaseId()).thenReturn(releaseId);
		when(releaseId.getVersion()).thenReturn(DEPLOYED_VERSION);
		when(droolsModule.getName()).thenReturn(MODULE_NAME);
		when(releaseId.toExternalForm()).thenReturn("EXTERNAL_FORM");
		when(rulesModuleDao.findByName(MODULE_NAME)).thenReturn(droolsModule);
		when(ruleEngineCacheService.createKIEModuleCacheBuilder(any())).thenReturn(cache);
	}

	@Test
	public void testSwitchKieModuleExecutePostTasks()
	{
		final LinkedList<Supplier<Object>> postTaskList = Lists.newLinkedList();
		postTaskList.addAll(asList(() -> "task1", () -> "task2"));
		final List<Object> resultsAccumulator = moduleSwapper.switchKieModule(droolsModule, ruleEngineActionResult,
				kieContainerListener, postTaskList);
		verify(kieContainerListener).onSuccess(kieContainer, cache);
		assertThat(resultsAccumulator).isNotNull().hasSize(2).containsSequence("task1", "task2");
	}

	@Test
	public void testSwitchKieModuleAsyncExecutePostTasksImmediate()
	{
		final List<Object> resultsAccumulator = Lists.newArrayList();

		final LinkedList<Supplier<Object>> postTaskList = Lists.newLinkedList();
		postTaskList.addAll(asList(() -> "task1", () -> "task2"));
		when(currentTenant.createAndRegisterBackgroundThread(any(Runnable.class), any(ThreadFactory.class)))
				.thenReturn(createTenantAwareThread(resultsAccumulator, postTaskList));
		moduleSwapper.switchKieModuleAsync(MODULE_NAME, ruleEngineActionResult, kieContainerListener, resultsAccumulator,
				postTaskList);
		assertThat(resultsAccumulator).isNotNull().hasSize(0);
	}

	@Test
	public void testSwitchKieModuleAsyncExecutePostTasksWhenCompleted() throws Exception
	{
		final List<Object> resultsAccumulator = Lists.newArrayList();

		final LinkedList<Supplier<Object>> postTaskList = Lists.newLinkedList();
		postTaskList.addAll(asList(() -> "task1", () -> "task2"));
		when(currentTenant.createAndRegisterBackgroundThread(any(Runnable.class), any(ThreadFactory.class)))
				.thenReturn(createTenantAwareThread(resultsAccumulator, postTaskList));
		moduleSwapper.switchKieModuleAsync(MODULE_NAME, ruleEngineActionResult, kieContainerListener, resultsAccumulator,
				postTaskList);
		Thread.sleep(500);
		verify(kieContainerListener).onSuccess(kieContainer, cache);
		assertThat(resultsAccumulator).isNotNull().hasSize(2).containsSequence("task1", "task2");
	}

	private Thread createTenantAwareThread(final List<Object> resultsAccumulator, final LinkedList<Supplier<Object>> postTaskList)
	{
		return new Thread(moduleSwapper.switchKieModuleRunnableTask(MODULE_NAME, ruleEngineActionResult, kieContainerListener,
				resultsAccumulator, postTaskList));
	}

}
