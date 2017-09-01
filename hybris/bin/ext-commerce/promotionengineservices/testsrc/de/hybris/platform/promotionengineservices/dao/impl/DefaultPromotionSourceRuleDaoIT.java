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
package de.hybris.platform.promotionengineservices.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.promotionengineservices.dao.PromotionDao;
import de.hybris.platform.promotionengineservices.model.CatForPromotionSourceRuleModel;
import de.hybris.platform.promotionengineservices.model.ExcludedCatForRuleModel;
import de.hybris.platform.promotionengineservices.model.ExcludedProductForRuleModel;
import de.hybris.platform.promotionengineservices.model.ProductForPromotionSourceRuleModel;
import de.hybris.platform.promotionengineservices.model.PromotionSourceRuleModel;
import de.hybris.platform.promotionengineservices.model.RuleBasedPromotionModel;
import de.hybris.platform.promotions.model.PromotionGroupModel;
import de.hybris.platform.ruleengineservices.rule.dao.RuleDao;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableSet;


@IntegrationTest
public class DefaultPromotionSourceRuleDaoIT extends ServicelayerTransactionalTest
{
	@Resource
	private DefaultPromotionSourceRuleDao promotionSourceRuleDao;

	@Resource
	private RuleDao ruleDao;

	@Resource
	private PromotionDao promotionDao;

	@Resource
	private ModelService modelService;

	@Before
	public void setUp() throws ImpExException
	{
		importCsv("/promotionengineservices/test/defaultPromotionSourceRuleDaoTest.impex", "utf-8");
	}

	@Test
	public void testFindAllProductForPromotionSourceRuleNoProducts()
	{
		final PromotionSourceRuleModel rule = (PromotionSourceRuleModel) ruleDao.findRuleByCode("rule1");

		final List<ProductForPromotionSourceRuleModel> productsForRule = promotionSourceRuleDao
				.findAllProductForPromotionSourceRule(rule);

		assertNotNull(productsForRule);
		assertEquals(0, productsForRule.size());
	}

	@Test
	public void testFindAllProductForPromotionSourceRule()
	{
		final PromotionSourceRuleModel rule = (PromotionSourceRuleModel) ruleDao.findRuleByCode("rule2");

		final List<ProductForPromotionSourceRuleModel> productsForRule = promotionSourceRuleDao
				.findAllProductForPromotionSourceRule(rule);

		assertNotNull(productsForRule);
		assertEquals(2, productsForRule.size());
	}

	@Test
	public void testFindAllCatForPromotionSourceRuleNoCategories()
	{
		final PromotionSourceRuleModel rule = (PromotionSourceRuleModel) ruleDao.findRuleByCode("rule1");

		final List<CatForPromotionSourceRuleModel> categoriesForRule = promotionSourceRuleDao
				.findAllCatForPromotionSourceRule(rule);

		assertNotNull(categoriesForRule);
		assertEquals(0, categoriesForRule.size());
	}

	@Test
	public void testFindAllCatForPromotionSourceRule()
	{
		final PromotionSourceRuleModel rule = (PromotionSourceRuleModel) ruleDao.findRuleByCode("rule2");

		final List<CatForPromotionSourceRuleModel> categoriesForRule = promotionSourceRuleDao
				.findAllCatForPromotionSourceRule(rule);

		assertNotNull(categoriesForRule);
		assertEquals(2, categoriesForRule.size());
	}

	@Test
	public void testFindPromotionSourceRules()
	{
		final PromotionGroupModel group = promotionDao.findPromotionGroupByCode("website1");
		final Collection<PromotionGroupModel> groups = Arrays.asList(group);
		final Set<String> categoryCodes = ImmutableSet.of("576", "brand_5");

		final List<RuleBasedPromotionModel> promotions = promotionSourceRuleDao.findPromotions(groups, "111111", categoryCodes);

		assertNotNull(promotions);
		assertEquals(1, promotions.size());
		final Set<String> codes = promotions.stream().map(p -> p.getCode()).collect(Collectors.toSet());
		assertTrue(codes.contains("promotion2"));
	}

	@Test
	public void testFindPromotionSourceRulesWithinDateRange()
	{
		final PromotionGroupModel group = promotionDao.findPromotionGroupByCode("website1");
		final Collection<PromotionGroupModel> groups = Collections.singletonList(group);

		// check that PromotionSourceRule with status PUBLISHED are found:
		final List<RuleBasedPromotionModel> publishedPromotions = promotionSourceRuleDao.findPromotions(groups, "13",
				Collections.emptySet());

		assertNotNull(publishedPromotions);
		assertEquals(1, publishedPromotions.size());
		assertTrue(publishedPromotions.stream().map(p -> p.getCode()).collect(Collectors.toSet()).contains("promotion13"));
	}

	@Test
	public void testFindPromotionSourceRulesOutOfDateRange()
	{
		final PromotionGroupModel group = promotionDao.findPromotionGroupByCode("website1");
		final Collection<PromotionGroupModel> groups = Collections.singletonList(group);

		final List<RuleBasedPromotionModel> outdatedPublishedPromotions = promotionSourceRuleDao.findPromotions(groups, "14",
				Collections.emptySet());

		assertNotNull(outdatedPublishedPromotions);
		assertEquals(0, outdatedPublishedPromotions.size());

		final List<RuleBasedPromotionModel> outdatedModifiedPromotions = promotionSourceRuleDao.findPromotions(groups, "17",
				Collections.emptySet());

		assertNotNull(outdatedModifiedPromotions);
		assertEquals(0, outdatedModifiedPromotions.size());

		// the same 2 tests + categories:
		final List<RuleBasedPromotionModel> outdatedPublishedPromotionsForCategories = promotionSourceRuleDao.findPromotions(groups,
				"14", ImmutableSet.of("581"));

		assertNotNull(outdatedPublishedPromotionsForCategories);
		assertEquals(0, outdatedPublishedPromotionsForCategories.size());

		final List<RuleBasedPromotionModel> outdatedModifiedPromotionsForCategories = promotionSourceRuleDao.findPromotions(groups,
				"17", ImmutableSet.of("584"));

		assertNotNull(outdatedModifiedPromotionsForCategories);
		assertEquals(0, outdatedModifiedPromotionsForCategories.size());
	}

	@Test
	public void testFindArchivedPromotionSourceRules()
	{
		final PromotionGroupModel group = promotionDao.findPromotionGroupByCode("website1");
		final Collection<PromotionGroupModel> groups = Collections.singletonList(group);

		final List<RuleBasedPromotionModel> promotions = promotionSourceRuleDao.findPromotions(groups, "15",
				Collections.emptySet());

		assertNotNull(promotions);
		assertEquals(0, promotions.size());

		final List<RuleBasedPromotionModel> promotionsForCategories = promotionSourceRuleDao.findPromotions(groups, "15",
				ImmutableSet.of("582"));

		assertNotNull(promotionsForCategories);
		assertEquals(0, promotionsForCategories.size());
	}

	@Test
	public void testFindUnpublishedPromotionSourceRules()
	{
		final PromotionGroupModel group = promotionDao.findPromotionGroupByCode("website1");
		final Collection<PromotionGroupModel> groups = Collections.singletonList(group);

		final List<RuleBasedPromotionModel> promotions = promotionSourceRuleDao.findPromotions(groups, "18",
				Collections.emptySet());

		assertNotNull(promotions);
		assertEquals(0, promotions.size());

		final List<RuleBasedPromotionModel> promotionsForCategories = promotionSourceRuleDao.findPromotions(groups, "18",
				ImmutableSet.of("585"));

		assertNotNull(promotionsForCategories);
		assertEquals(0, promotionsForCategories.size());
	}

	@Test
	public void testFindPromotionSourceRulesOnlyCat()
	{
		final PromotionGroupModel group = promotionDao.findPromotionGroupByCode("website1");
		final Collection<PromotionGroupModel> groups = Arrays.asList(group);
		final Set<String> categoryCodes = ImmutableSet.of("brand_5");

		final List<RuleBasedPromotionModel> promotions = promotionSourceRuleDao.findPromotions(groups, "444444", categoryCodes);

		assertNotNull(promotions);
		assertEquals(1, promotions.size());
		assertEquals("promotion2", promotions.get(0).getCode());
	}

	@Test
	public void testFindPromotionSourceRulesOnlyCatWithExcludeStorefrontDisplay()
	{
		final PromotionGroupModel group = promotionDao.findPromotionGroupByCode("website1");
		final Collection<PromotionGroupModel> groups = Arrays.asList(group);
		final Set<String> categoryCodes = ImmutableSet.of("brand_5");

		setExcludeFromStorefrontDisplayForRule("rule2");
		final List<RuleBasedPromotionModel> promotions = promotionSourceRuleDao.findPromotions(groups, "444444", categoryCodes);

		assertNotNull(promotions);
		assertEquals(0, promotions.size());
	}

	@Test
	public void testFindPromotionSourceRulesNotFound()
	{
		final PromotionGroupModel group = promotionDao.findPromotionGroupByCode("website1");
		final Collection<PromotionGroupModel> groups = Arrays.asList(group);
		final Set<String> categoryCodes = ImmutableSet.of("555");

		final List<RuleBasedPromotionModel> promotions = promotionSourceRuleDao.findPromotions(groups, "555555", categoryCodes);

		assertNotNull(promotions);
		assertEquals(0, promotions.size());
	}

	@Test
	public void testFindPromotionSourceRulesOnlyProd()
	{
		final PromotionGroupModel group = promotionDao.findPromotionGroupByCode("website1");
		final Collection<PromotionGroupModel> groups = Arrays.asList(group);

		final List<RuleBasedPromotionModel> promotions = promotionSourceRuleDao.findPromotions(groups, "111111", null);

		assertNotNull(promotions);
		assertEquals(1, promotions.size());
		final Set<String> codes = promotions.stream().map(p -> p.getCode()).collect(Collectors.toSet());
		assertTrue(codes.contains("promotion2"));
	}

	@Test
	public void testFindPromotionSourceRulesOnlyProdWithExcludeStorefrontDisplay()
	{
		final PromotionGroupModel group = promotionDao.findPromotionGroupByCode("website1");
		final Collection<PromotionGroupModel> groups = Arrays.asList(group);

		setExcludeFromStorefrontDisplayForRule("rule2");
		setExcludeFromStorefrontDisplayForRule("rule3");
		final List<RuleBasedPromotionModel> promotions = promotionSourceRuleDao.findPromotions(groups, "111111", null);

		assertNotNull(promotions);
		assertEquals(0, promotions.size());
	}

	@Test
	public void testFindPromotionSourceRulesCombinedCats()
	{
		//prod10_1 cat10_1,cat10_2 -> rule10 (promotion10)
		//prod10_2 cat10_3,cat10_4 -> rule10 (promotion10)
		//exclProd10_1 cat10_1,cat10_2 -> no
		//exclProd10_2 cat10_3,cat10_4 -> no

		final PromotionGroupModel group = promotionDao.findPromotionGroupByCode("website1");
		final Collection<PromotionGroupModel> groups = Arrays.asList(group);

		final Set<String> categoryCodes = ImmutableSet.of("cat10_1", "cat10_2");

		final List<RuleBasedPromotionModel> promotions = promotionSourceRuleDao.findPromotions(groups, "prod10_1", categoryCodes);

		assertNotNull(promotions);
		assertEquals(1, promotions.size());
		assertEquals("promotion10", promotions.get(0).getCode());
	}

	@Test
	public void testFindPromotionSourceRulesCombinedCatsWithExcludeStorefrontDisplay()
	{
		//prod10_1 cat10_1,cat10_2 -> rule10 (promotion10)
		//prod10_2 cat10_3,cat10_4 -> rule10 (promotion10)
		//exclProd10_1 cat10_1,cat10_2 -> no
		//exclProd10_2 cat10_3,cat10_4 -> no

		setExcludeFromStorefrontDisplayForRule("rule10");

		final PromotionGroupModel group = promotionDao.findPromotionGroupByCode("website1");
		final Collection<PromotionGroupModel> groups = Arrays.asList(group);

		final Set<String> categoryCodes = ImmutableSet.of("cat10_1", "cat10_2");

		final List<RuleBasedPromotionModel> promotions = promotionSourceRuleDao.findPromotions(groups, "prod10_1", categoryCodes);

		assertNotNull(promotions);
		assertEquals(0, promotions.size());
	}

	@Test
	public void testFindPromotionSourceRulesCombinedCatsWithExcludedProduct()
	{
		final PromotionGroupModel group = promotionDao.findPromotionGroupByCode("website1");
		final Collection<PromotionGroupModel> groups = Arrays.asList(group);

		final Set<String> categoryCodes = ImmutableSet.of("cat10_1", "cat10_2");

		final List<RuleBasedPromotionModel> promotions = promotionSourceRuleDao.findPromotions(groups, "exclProd10_1",
				categoryCodes);

		assertNotNull(promotions);
		assertEquals(0, promotions.size());
	}

	@Test
	public void testFindPromotionSourceRulesCatsWithExcludedProduct()
	{
		final PromotionGroupModel group = promotionDao.findPromotionGroupByCode("website1");
		final Collection<PromotionGroupModel> groups = Arrays.asList(group);

		final Set<String> categoryCodes = ImmutableSet.of("cat11_1");
		final List<RuleBasedPromotionModel> promotions = promotionSourceRuleDao.findPromotions(groups, "exclProd11_1",
				categoryCodes);

		assertNotNull(promotions);
		assertEquals(0, promotions.size());
	}

	@Test
	public void testFindPromotionsWithExcludeStorefrontDisplayChangingFlag()
	{
		// rule/promotion12 should be found
		final PromotionGroupModel group = promotionDao.findPromotionGroupByCode("website1");
		final Collection<PromotionGroupModel> groups = Arrays.asList(group);
		final Set<String> categoryCodes = ImmutableSet.of("cat12_1", "cat12_2");

		final List<RuleBasedPromotionModel> promotionsBefore = promotionSourceRuleDao.findPromotions(groups, "anyProduct",
				categoryCodes);

		assertNotNull(promotionsBefore);
		assertEquals(1, promotionsBefore.size());
		assertEquals("promotion12", promotionsBefore.get(0).getCode());

		// now set flag to TRUE and ensure the promotion is no longer returned.
		setExcludeFromStorefrontDisplayForRule("rule12");

		final List<RuleBasedPromotionModel> promotionsAfter = promotionSourceRuleDao.findPromotions(groups, "anyProduct",
				categoryCodes);

		assertNotNull(promotionsAfter);
		assertEquals(0, promotionsAfter.size());
	}

	@Test
	public void testFindLastConditionIdForRuleAlreadyExists()
	{
		final PromotionSourceRuleModel rule = (PromotionSourceRuleModel) ruleDao.findRuleByCode("rule6");
		final Integer lastConditionId = promotionSourceRuleDao.findLastConditionIdForRule(rule);

		assertEquals(2, lastConditionId.intValue());
	}

	@Test
	public void testFindLastConditionIdForRuleNotExists()
	{
		final PromotionSourceRuleModel rule = (PromotionSourceRuleModel) ruleDao.findRuleByCode("rule7");
		final Integer lastConditionId = promotionSourceRuleDao.findLastConditionIdForRule(rule);

		assertNull(lastConditionId);
	}

	@Test
	public void testFindAllExcludedCatForPromotionSourceRule()
	{
		final PromotionSourceRuleModel rule = (PromotionSourceRuleModel) ruleDao.findRuleByCode("rule8");
		final List<ExcludedCatForRuleModel> excludedCategories = promotionSourceRuleDao
				.findAllExcludedCatForPromotionSourceRule(rule);

		assertEquals(2, excludedCategories.size());

		final List<ExcludedCatForRuleModel> excludedCategoriesCat1 = excludedCategories.stream()
				.filter(ec -> ec.getCategoryCode().equals("cat1")).collect(Collectors.toList());
		assertEquals(1, excludedCategoriesCat1.size());

		final List<ExcludedCatForRuleModel> excludedCategoriesCat2 = excludedCategories.stream()
				.filter(ec -> ec.getCategoryCode().equals("cat2")).collect(Collectors.toList());
		assertEquals(1, excludedCategoriesCat2.size());
	}

	@Test
	public void testFindAllExcludedCatForPromotionSourceRuleNoExcluded()
	{
		final PromotionSourceRuleModel rule = (PromotionSourceRuleModel) ruleDao.findRuleByCode("rule9");
		final List<ExcludedCatForRuleModel> excludedCategories = promotionSourceRuleDao
				.findAllExcludedCatForPromotionSourceRule(rule);

		assertEquals(0, excludedCategories.size());
	}

	@Test
	public void testFindAllExcludedProductForPromotionSourceRule()
	{
		final PromotionSourceRuleModel rule = (PromotionSourceRuleModel) ruleDao.findRuleByCode("rule8");
		final List<ExcludedProductForRuleModel> excludedProducts = promotionSourceRuleDao
				.findAllExcludedProductForPromotionSourceRule(rule);

		assertEquals(2, excludedProducts.size());

		final List<ExcludedProductForRuleModel> excludedCategoriesCat1 = excludedProducts.stream()
				.filter(ec -> ec.getProductCode().equals("prod1")).collect(Collectors.toList());
		assertEquals(1, excludedCategoriesCat1.size());

		final List<ExcludedProductForRuleModel> excludedCategoriesCat2 = excludedProducts.stream()
				.filter(ec -> ec.getProductCode().equals("prod2")).collect(Collectors.toList());
		assertEquals(1, excludedCategoriesCat2.size());
	}

	@Test
	public void testFindAllExcludedProductForPromotionSourceRuleNoExcluded()
	{
		final PromotionSourceRuleModel rule = (PromotionSourceRuleModel) ruleDao.findRuleByCode("rule9");
		final List<ExcludedProductForRuleModel> excludedProducts = promotionSourceRuleDao
				.findAllExcludedProductForPromotionSourceRule(rule);

		assertEquals(0, excludedProducts.size());
	}

	@Test
	public void testFindPromotionSourceRulesCombinedCatsWithExpiredDates()
	{
		// prod19_1 cat19_1,cat19_2 -> rule19 (promotion19)

		final PromotionGroupModel group = promotionDao.findPromotionGroupByCode("website1");
		final Collection<PromotionGroupModel> groups = Arrays.asList(group);

		final Set<String> categoryCodes = ImmutableSet.of("cat19_1", "cat19_2");

		//promotion19 should not be found as it has start-end date in past
		final List<RuleBasedPromotionModel> promotions = promotionSourceRuleDao.findPromotions(groups, "prod19_1", categoryCodes);

		assertNotNull(promotions);
		assertEquals(0, promotions.size());

	}

	protected void setExcludeFromStorefrontDisplayForRule(final String ruleCode)
	{
		// set flag to TRUE and save rule
		final PromotionSourceRuleModel rule = (PromotionSourceRuleModel) ruleDao.findRuleByCode(ruleCode);
		rule.setExcludeFromStorefrontDisplay(Boolean.TRUE);
		modelService.save(rule);
	}
}
