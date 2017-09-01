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
package de.hybris.platform.warehousing.util.models;

import de.hybris.platform.warehousing.enums.AsnStatus;
import de.hybris.platform.warehousing.model.AdvancedShippingNoticeEntryModel;
import de.hybris.platform.warehousing.model.AdvancedShippingNoticeModel;
import de.hybris.platform.warehousing.util.builder.AsnModelBuilder;
import de.hybris.platform.warehousing.util.dao.impl.AsnDaoImpl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;


public class Asns extends AbstractItems<AdvancedShippingNoticeModel>
{
	public static final String INTERNAL_ID = "B0001";
	public static final String EXTERNAL_ID = "EXT123";
	public static final AsnStatus STATUS = AsnStatus.CREATED;

	private AsnDaoImpl asnDao;
	private PointsOfService pointsOfService;
	private Warehouses warehouses;

	public AdvancedShippingNoticeModel EXT123()
	{
		return getOrCreateAsn(INTERNAL_ID, new Date(), Collections.emptyList());
	}

	public AdvancedShippingNoticeModel EXT123(final Date date)
	{
		return getOrCreateAsn(INTERNAL_ID, date, Collections.emptyList());
	}

	public AdvancedShippingNoticeModel EXT123(final Date date, final List<AdvancedShippingNoticeEntryModel> asnEntries)
	{
		return getOrCreateAsn(INTERNAL_ID, date, asnEntries);
	}

	protected AdvancedShippingNoticeModel getOrCreateAsn(final String internalId, final Date releaseDate,
			final List<AdvancedShippingNoticeEntryModel> asnEntries)
	{
		return getOrSaveAndReturn(() -> getAsnDao().getByCode(internalId),
				() -> AsnModelBuilder.aModel().withInternalId(INTERNAL_ID).withExternalId(EXTERNAL_ID).withStatus(STATUS)
						.withPoS(getPointsOfService().Boston()).withWarehouse(getWarehouses().Boston()).withReleaseDate(releaseDate)
						.withAsnEntries(asnEntries)
						.build());
	}

	protected AsnDaoImpl getAsnDao()
	{
		return asnDao;
	}

	@Required
	public void setAsnDao(final AsnDaoImpl asnDao)
	{
		this.asnDao = asnDao;
	}

	protected PointsOfService getPointsOfService()
	{
		return pointsOfService;
	}

	@Required
	public void setPointsOfService(final PointsOfService pointsOfService)
	{
		this.pointsOfService = pointsOfService;
	}

	protected Warehouses getWarehouses()
	{
		return warehouses;
	}

	@Required
	public void setWarehouses(final Warehouses warehouses)
	{
		this.warehouses = warehouses;
	}
}
