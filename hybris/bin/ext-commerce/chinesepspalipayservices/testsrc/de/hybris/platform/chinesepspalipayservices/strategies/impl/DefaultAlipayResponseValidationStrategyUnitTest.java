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
package de.hybris.platform.chinesepspalipayservices.strategies.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.chinesepspalipayservices.alipay.AlipayConfiguration;
import de.hybris.platform.chinesepspalipayservices.alipay.AlipayUtil;
import de.hybris.platform.chinesepspalipayservices.alipay.HttpProtocolHandler;
import de.hybris.platform.chinesepspalipayservices.data.HttpRequest;
import de.hybris.platform.chinesepspalipayservices.data.HttpResponse;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@UnitTest
@RunWith(PowerMockRunner.class)
@PrepareForTest(
{ AlipayUtil.class, HttpProtocolHandler.class })
public class DefaultAlipayResponseValidationStrategyUnitTest
{
	private DefaultAlipayResponseValidationStrategy defaultAlipayResponseValidationStrategy;

	@Mock
	private AlipayConfiguration alipayConfiguration;
	@Mock
	private HttpResponse response;
	@Mock
	private HttpProtocolHandler httpProtocolHandler;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		defaultAlipayResponseValidationStrategy = new DefaultAlipayResponseValidationStrategy();
		defaultAlipayResponseValidationStrategy.setAlipayConfiguration(alipayConfiguration);

		PowerMockito.mockStatic(AlipayUtil.class);
		PowerMockito.mockStatic(HttpProtocolHandler.class);
	}

	@Test
	public void testValidateResponse()
	{
		String partner = "20880217298746149";
		String key = "sveitc3mpw8e4hkbs4k8irqhx4bxxxxx";
		String trustGateway = "true";
		String result = "true";
		String verifyUrl = "https://electronics.local:9002/yacceleratorstorefront/checkout/multi/alipay/mock/gateway.do/notify.verify?";
		String actualSign = "test_sign";
		Map<String, String> params = new HashMap<>();
		params.put("sign", "test_sign");
		params.put("notify_id", "test_notify_id");

		params.put("filtered", "test");
		Map<String, String> filteredParams = new HashMap<>();
		filteredParams.put("sign", "sign_sent");
		filteredParams.put("notify_id", "test_notify_id");

		given(alipayConfiguration.getWebPartner()).willReturn(partner);
		given(alipayConfiguration.getWebKey()).willReturn(key);
		given(alipayConfiguration.getTrustGateway()).willReturn(trustGateway);
		given(alipayConfiguration.getHttpsVerifyUrl()).willReturn(verifyUrl);
		given(httpProtocolHandler.execute(Mockito.any(HttpRequest.class))).willReturn(response);
		given(response.getStringResult()).willReturn(result);
		PowerMockito.when(AlipayUtil.paraFilter(params)).thenReturn(filteredParams);
		PowerMockito.when(AlipayUtil.buildMysign(filteredParams, key, "MD5")).thenReturn(actualSign);
		PowerMockito.when(HttpProtocolHandler.getInstance(trustGateway)).thenReturn(httpProtocolHandler);

		assertTrue(defaultAlipayResponseValidationStrategy.validateResponse(params));
	}


}
