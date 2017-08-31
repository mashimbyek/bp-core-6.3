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
package de.hybris.platform.chinaaccelerator.services.alipay;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;


public class AlipayServiceUtil
{
	protected static final Logger LOG = Logger.getLogger(AlipayServiceUtil.class);

	public static String printRequestParameters(final HttpServletRequest request)
	{
		final Map param = request.getParameterMap();
		if (param == null)
		{
			return "";
		}
		final Map<String, String> map = transRequestParam2Map(param);
		final StringBuilder fullStr = new StringBuilder();
		for (final String key : map.keySet())
		{
			String value = "";
			try
			{
				value = java.net.URLEncoder.encode(map.get(key), "utf-8");
			}
			catch (final UnsupportedEncodingException e)
			{
				LOG.error("UnsupportedEncodingException", e);
			}
			fullStr.append("\n").append(key).append(":").append(value).append("; ");
		}
		return fullStr.toString();
	}

	/**
	 * Convert the request parameter map into LinkedHashMap for string to string
	 *
	 * @param params
	 * @return
	 */
	public static Map<String, String> transRequestParam2Map(final Map params)
	{
		final Map<String, String> map = new LinkedHashMap<String, String>();
		for (final Object key : params.keySet())
		{
			//if(params.get(key) instanceof String[]){
			final String[] strArray = (String[]) params.get(key);
			final StringBuilder builder = new StringBuilder();
			for (final String s : strArray)
			{
				builder.append(s);
			}
			final String value = builder.toString();
			map.put((String) key, value);
			//}
		}
		return map;
	}

	// REDDRA-39	public static Map<String, String> transBean2Map(final Object obj)
	//	{
	//		final Map<String, String> map = new LinkedHashMap<String, String>();
	//		Field[] fields = obj.getClass().getDeclaredFields();
	//		for (int i = 0; i < fields.length; i++)
	//		{
	//			try
	//			{
	//				fields[i].setAccessible(true);
	//				final String key = fields[i].getName();
	//				String val = "";
	//				if (fields[i].get(obj) instanceof Float)
	//				{
	//					final Float fval = (Float) fields[i].get(obj);
	//					if (fval.floatValue() > 0)
	//					{
	//						val = String.valueOf(fval);
	//					}
	//				}
	//				else if (fields[i].get(obj) instanceof Integer)
	//				{
	//					final Integer ival = (Integer) fields[i].get(obj);
	//					if (ival.intValue() > 0)
	//					{
	//						val = String.valueOf(ival);
	//					}
	//				}
	//				else
	//				{
	//					val = (fields[i].get(obj) == null) ? "" : String.valueOf(fields[i].get(obj));
	//				}
	//				map.put(key, val);
	//			}
	//			catch (final IllegalArgumentException e)
	//			{
	//				e.printStackTrace();
	//			}
	//			catch (final IllegalAccessException e)
	//			{
	//				e.printStackTrace();
	//			}
	//		}
	//		if (obj.getClass().getSuperclass() != null)
	//		{
	//			fields = obj.getClass().getSuperclass().getDeclaredFields();
	//			for (int i = 0; i < fields.length; i++)
	//			{
	//				try
	//				{
	//					fields[i].setAccessible(true);
	//					final String key = fields[i].getName();
	//					String val = "";
	//					if (fields[i].get(obj) instanceof Float)
	//					{
	//						final Float fval = (Float) fields[i].get(obj);
	//						if (fval.floatValue() > 0)
	//						{
	//							val = String.valueOf(fval);
	//						}
	//					}
	//					else if (fields[i].get(obj) instanceof Integer)
	//					{
	//						final Integer ival = (Integer) fields[i].get(obj);
	//						if (ival.intValue() > 0)
	//						{
	//							val = String.valueOf(ival);
	//						}
	//					}
	//					else
	//					{
	//						val = (fields[i].get(obj) == null) ? "" : String.valueOf(fields[i].get(obj));
	//					}
	//					map.put(key, val);
	//				}
	//				catch (final IllegalArgumentException e)
	//				{
	//					e.printStackTrace();
	//				}
	//				catch (final IllegalAccessException e)
	//				{
	//					e.printStackTrace();
	//				}
	//			}
	//		}
	//
	//		return map;
	//	}
}
