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
package cn.alipay.payment.util.httpclient;

import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.Header;

import de.hybris.platform.chinaaccelerator.services.alipay.PaymentConstants;


public class HttpResponse
{

	private Header[] responseHeaders;

	private String stringResult;

	private byte[] byteResult;

	public Header[] getResponseHeaders()
	{
		return responseHeaders;
	}

	public void setResponseHeaders(final Header[] responseHeaders)
	{
		this.responseHeaders = responseHeaders;
	}

	public byte[] getByteResult()
	{
		if (byteResult != null)
		{
			return byteResult;
		}
		if (stringResult != null)
		{
			return stringResult.getBytes();
		}
		return null;
	}

	public void setByteResult(final byte[] byteResult)
	{
		this.byteResult = byteResult;
	}

	public String getStringResult() throws UnsupportedEncodingException
	{
		if (stringResult != null)
		{
			return stringResult;
		}
		if (byteResult != null)
		{
			return new String(byteResult, PaymentConstants.Basic.INPUT_CHARSET);
		}
		return null;
	}

	public void setStringResult(final String stringResult)
	{
		this.stringResult = stringResult;
	}

}
