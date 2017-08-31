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
package cn.alipay.payment.util;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;

import org.apache.commons.codec.digest.DigestUtils;

import de.hybris.platform.chinaaccelerator.services.alipay.PaymentConstants;




public class AlipayMd5Encrypt
{

	public static String md5(final String text)
	{

		return DigestUtils.md5Hex(getContentBytes(text, PaymentConstants.Basic.INPUT_CHARSET));

	}

	/**
	 * @param content
	 * @param charset
	 * @return
	 * @throws SignatureException
	 * @throws UnsupportedEncodingException
	 */
	private static byte[] getContentBytes(final String content, final String charset)
	{
		if (charset == null || "".equals(charset))
		{
			return content.getBytes();
		}

		try
		{
			return content.getBytes(charset);
		}
		catch (final UnsupportedEncodingException e)
		{
			throw new RuntimeException("MD5 Error : " + charset);
		}
	}

}
