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
package de.hybris.platform.consignmenttrackingservices.daos.impl;

import de.hybris.platform.consignmenttrackingservices.daos.ConsignmentDao;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Optional;


/**
 * The default implementation of ConsignmentDao
 */
public class DefaultConsignmentDao extends DefaultGenericDao<ConsignmentModel> implements ConsignmentDao
{

	private static final String CONSIGNMENT_CODE = "consignmentCode";

	private static final String ORDER_CODE = "orderCode";

	private static final String FQL = "select {" + ConsignmentModel.PK + "} from {" + ConsignmentModel._TYPECODE + "} where {"
			+ ConsignmentModel.CODE + "} = ?consignmentCode and {" + ConsignmentModel.ORDER + "} in ({{ select {" + OrderModel.PK
			+ "} from {" + OrderModel._TYPECODE + "} where {" + OrderModel.CODE + "} = ?orderCode }})";

	public DefaultConsignmentDao()
	{
		super(ConsignmentModel._TYPECODE);
	}

	@Override
	public Optional<ConsignmentModel> findConsignmentByCode(final String orderCode, final String consignmentCode)
	{
		final FlexibleSearchQuery query = new FlexibleSearchQuery(FQL);
		query.addQueryParameter(CONSIGNMENT_CODE, consignmentCode);
		query.addQueryParameter(ORDER_CODE, orderCode);

		return Optional.ofNullable(getFlexibleSearchService().searchUnique(query));
	}

}
