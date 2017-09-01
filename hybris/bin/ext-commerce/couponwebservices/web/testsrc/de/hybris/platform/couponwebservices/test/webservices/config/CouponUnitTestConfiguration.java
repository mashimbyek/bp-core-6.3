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
package de.hybris.platform.couponwebservices.test.webservices.config;

import static org.mockito.Mockito.when;

import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.couponwebservices.facades.CouponCodeGenerationWsFacade;
import de.hybris.platform.couponwebservices.facades.CouponRedemptionWsFacade;
import de.hybris.platform.couponwebservices.facades.impl.DefaultMultiCodeCouponWsFacades;
import de.hybris.platform.couponwebservices.facades.impl.DefaultSingleCodeCouponWsFacades;
import de.hybris.platform.couponwebservices.validator.MultiCodeCouponWsDTOValidator;
import de.hybris.platform.couponwebservices.validator.SingleCodeCouponWsDTOValidator;
import de.hybris.platform.webservicescommons.mapping.DataMapper;

import java.util.Optional;

import org.hamcrest.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@ComponentScan("de.hybris.platform.couponwebservices.controllers")
@EnableWebMvc
public class CouponUnitTestConfiguration
{
	public static final String COUPON_ID_OK = "COUPON_ID_OK";
	public static final String COUPON_ID_KO = "COUPON_ID_KO";
	public static final String COUPON_MEDIA_CODE = "MEDIA_CODE";
	public static final String COUPON_GENERATED_CODE = "COUPON_GENERATED_CODE";

	@Mock
	private CouponCodeGenerationWsFacade couponCodeGenerationWsFacade;
	@Mock
	private DefaultSingleCodeCouponWsFacades singleCodeCouponWsFacades;
	@Mock
	private DefaultMultiCodeCouponWsFacades multiCodeCouponWsFacades;
	@Mock
	private CouponRedemptionWsFacade couponRedemptionWsFacade;
	@Mock
	private SingleCodeCouponWsDTOValidator singleCodeCouponWsDTOValidator;
	@Mock
	private MultiCodeCouponWsDTOValidator multiCodeCouponWsDTOValidator;
	@Mock
	private DataMapper dataMapper;

	@Mock
	private MediaModel couponCodesMediaModel;

	public CouponUnitTestConfiguration()
	{
		MockitoAnnotations.initMocks(this);
		defineMockBehaviour();
	}

	@Bean
	public CouponCodeGenerationWsFacade couponCodeGenerationWsFacade()
	{
		return couponCodeGenerationWsFacade;
	}

	@Bean
	public DefaultSingleCodeCouponWsFacades singleCodeCouponWsFacades()
	{
		return singleCodeCouponWsFacades;
	}

	@Bean
	public DefaultMultiCodeCouponWsFacades multiCodeCouponWsFacades()
	{
		return multiCodeCouponWsFacades;
	}

	@Bean
	public CouponRedemptionWsFacade couponRedemptionWsFacade()
	{
		return couponRedemptionWsFacade;
	}

	@Bean
	public SingleCodeCouponWsDTOValidator singleCodeCouponWsDTOValidator()
	{
		return singleCodeCouponWsDTOValidator;
	}

	@Bean
	public MultiCodeCouponWsDTOValidator multiCodeCouponWsDTOValidator()
	{
		return multiCodeCouponWsDTOValidator;
	}

	@Bean
	public DataMapper dataMapper()
	{
		return dataMapper;
	}

	private void defineMockBehaviour()
	{
		when(couponCodeGenerationWsFacade.generateCouponCodes(Mockito.eq(COUPON_ID_OK), Mockito.anyInt()))
				.thenReturn(Optional.of(couponCodesMediaModel));
		when(couponCodeGenerationWsFacade.generateCouponCodes(Mockito.argThat(Matchers.not(COUPON_ID_OK)), Mockito.anyInt()))
				.thenReturn(Optional.empty());
		when(couponCodeGenerationWsFacade.getCouponCodes(Mockito.eq(COUPON_ID_OK), Mockito.eq(COUPON_MEDIA_CODE)))
				.thenReturn(COUPON_GENERATED_CODE.getBytes());
		when(couponCodesMediaModel.getCode()).thenReturn(COUPON_MEDIA_CODE);
	}

}
