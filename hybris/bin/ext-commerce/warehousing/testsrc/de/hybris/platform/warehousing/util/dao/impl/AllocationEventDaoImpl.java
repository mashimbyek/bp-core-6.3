/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 *
 */
package de.hybris.platform.warehousing.util.dao.impl;

import de.hybris.platform.warehousing.model.AllocationEventModel;
import org.springframework.beans.factory.annotation.Required;


public class AllocationEventDaoImpl extends AbstractWarehousingDao<AllocationEventModel>
{
	private String code;

	@Override
	protected String getQuery()
	{
		return "SELECT {a.pk} FROM {AllocationEvent as a " //
				+ "JOIN ConsignmentEntry as e ON {a.consignmentEntry} = {e.pk} " //
				+ "JOIN Consignment as c ON {e.consignment} = {c.pk}} " //
				+ "WHERE {c.code}=?code";
	}

	@Override
	public String getProperty()
	{
		return getCode();
	}

	protected String getCode()
	{
		return code;
	}

	@Required
	public void setCode(final String code)
	{
		this.code = code;
	}
}
