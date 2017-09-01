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
package de.hybris.platform.couponwebservices.test.webservices;

import static de.hybris.platform.couponwebservices.test.webservices.config.CouponUnitTestConfiguration.COUPON_GENERATED_CODE;
import static de.hybris.platform.couponwebservices.test.webservices.config.CouponUnitTestConfiguration.COUPON_ID_KO;
import static de.hybris.platform.couponwebservices.test.webservices.config.CouponUnitTestConfiguration.COUPON_ID_OK;
import static de.hybris.platform.couponwebservices.test.webservices.config.CouponUnitTestConfiguration.COUPON_MEDIA_CODE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.couponwebservices.CouponCodeGenerationWsException;
import de.hybris.platform.couponwebservices.test.webservices.config.CouponUnitTestConfiguration;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;


@UnitTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration()
@ContextConfiguration(classes = CouponUnitTestConfiguration.class)
public class CouponCodeGenerationUnitTest
{
	private static final String COUPONSERVICES_ROOT_URI = "/couponservices";
	private static final String COUPONCODES_ROOT_URI = "/couponcodes";

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void setUp()
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testGenerateCouponCodesOk() throws Exception
	{
		mockMvc//
				.perform(put(COUPONSERVICES_ROOT_URI + "/multicodecoupon/generate/{couponId}/{batchSize}", COUPON_ID_OK, 10))//
				.andExpect(status().isCreated()).andExpect(content().string(""))
				.andExpect(header().string("Location", "http://localhost/couponcodes/" + COUPON_ID_OK + "/" + COUPON_MEDIA_CODE));
	}

	@Test
	public void testGenerateCouponCodesKo() throws Exception
	{
		expectedException.expect(NestedServletException.class);
		expectedException.expectCause(Matchers.any(CouponCodeGenerationWsException.class));
		expectedException.expectMessage("No generated coupon codes found in the system");

		mockMvc//
				.perform(put(COUPONSERVICES_ROOT_URI + "/multicodecoupon/generate/{couponId}/{batchSize}", COUPON_ID_KO, 10));
	}

	@Test
	public void testGetGeneratedCouponCodesOk() throws Exception
	{
		mockMvc//
				.perform(get(COUPONCODES_ROOT_URI + "/{couponId}/{mediaCode}", COUPON_ID_OK, COUPON_MEDIA_CODE))//
				.andExpect(status().isOk()).andExpect(content().string(COUPON_GENERATED_CODE));
	}

	@Test
	public void testGetGeneratedCouponCodesNotFound() throws Exception
	{
		expectedException.expect(NestedServletException.class);
		expectedException.expectCause(Matchers.any(CouponCodeGenerationWsException.class));
		expectedException.expectMessage("No data was found for generated coupon codes: octet byte array is null or empty");

		mockMvc//
				.perform(get(COUPONCODES_ROOT_URI + "/{couponId}/{mediaCode}", COUPON_ID_KO, COUPON_MEDIA_CODE))//
				.andExpect(status().isNotFound());
	}
}
