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
package de.hybris.platform.ruleengineservices.util;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.order.calculation.domain.OrderCharge;
import de.hybris.order.calculation.money.Currency;
import de.hybris.order.calculation.money.Money;
import de.hybris.order.calculation.money.Percentage;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class OrderUtilsTest
{
	private static final String USD = "USD";
	private static final String EUR = "EUR";

	@Mock
	private CommonI18NService commonI18NService;

	private OrderUtils orderUtils;
	private Currency currency;
	private CurrencyModel currencyModel;
	private CurrencyModel currencyModelEUR;

	@Before
	public void setUp()
	{
		orderUtils = new OrderUtils();
		orderUtils.setCommonI18NService(commonI18NService);

		currency = new Currency(USD, 2);
		currencyModel = new CurrencyModel();
		currencyModel.setIsocode(USD);
		currencyModel.setDigits(2);
		when(commonI18NService.getCurrency(USD)).thenReturn(currencyModel);

		currencyModelEUR = new CurrencyModel();
		currencyModelEUR.setIsocode(EUR);
		currencyModelEUR.setDigits(2);
		when(commonI18NService.getCurrency(EUR)).thenReturn(currencyModelEUR);
	}

	@Test
	public void testCreateShippingChargeAbsolute()
	{
		final BigDecimal value = new BigDecimal("100");
		final OrderCharge orderCharge = orderUtils.createShippingCharge(currency, true, value);
		assertThat(orderCharge.getAmount()).isNotNull().isInstanceOf(Money.class);
		final Money money = (Money) orderCharge.getAmount();
		assertThat(money.getAmount()).isEqualByComparingTo(value);
		assertThat(money.getCurrency()).isEqualTo(currency);
	}

	@Test
	public void testCreateShippingChargePercentage()
	{
		final BigDecimal value = new BigDecimal("10");
		final OrderCharge orderCharge = orderUtils.createShippingCharge(currency, false, value);
		assertThat(orderCharge.getAmount()).isNotNull().isInstanceOf(Percentage.class);
		final Percentage percentage = (Percentage) orderCharge.getAmount();
		assertThat(percentage.getRate()).isEqualByComparingTo(value);
	}

	@Test
	public void testApplyRounding()
	{
		final BigDecimal value = new BigDecimal("100.4456");
		final BigDecimal roundedValue = orderUtils.applyRounding(value, USD);
		assertThat(roundedValue).isEqualByComparingTo(new BigDecimal("100.45"));
	}

	@Test
	public void testApplyRoundingNoDigits()
	{
		currencyModel.setDigits(null);

		final BigDecimal value = new BigDecimal("100.5456");
		final BigDecimal roundedValue = orderUtils.applyRounding(value, USD);
		assertThat(roundedValue).isEqualByComparingTo(new BigDecimal("101"));
	}

	@Test
	public void testApplyRoundingNoPrice()
	{
		final BigDecimal roundedValue = orderUtils.applyRounding(null, USD);
		assertThat(roundedValue).isNull();
	}

	@Test
	public void testConvertCurrencyNoConverter()
	{
		final BigDecimal value = new BigDecimal("100");
		final BigDecimal convertedValue = orderUtils.convertCurrency(USD, EUR, value);
		assertThat(convertedValue).isEqualByComparingTo(new BigDecimal("0"));
	}

	@Test
	public void testConvertCurrencyWithConverter()
	{
		final double conversionRateUS = 1d;
		final double conversionRateEUR = 1.12345678d;

		currencyModel.setConversion(Double.valueOf(conversionRateUS));
		currencyModelEUR.setConversion(Double.valueOf(conversionRateEUR));

		final BigDecimal value = new BigDecimal("100");
		when(commonI18NService.convertAndRoundCurrency(Mockito.eq(conversionRateUS), Mockito.eq(conversionRateEUR),
				Mockito.eq(currencyModelEUR.getDigits()), Mockito.anyDouble())).thenReturn(value.doubleValue() * conversionRateEUR);

		final BigDecimal convertedValue = orderUtils.convertCurrency(USD, EUR, value);
		assertThat(convertedValue).isEqualByComparingTo(new BigDecimal("112.35"));
	}

}
