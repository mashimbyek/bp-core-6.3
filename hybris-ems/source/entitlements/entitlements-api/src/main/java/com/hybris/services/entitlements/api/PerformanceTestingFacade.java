/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 *
 */
package com.hybris.services.entitlements.api;

/**
 * This class provides logic of performance testing operations.
 */
public interface PerformanceTestingFacade
{
	/**
	 * Create set of grants in batch mode.
	 *
	 * @param loadDataSettings grant template
	 * @param firstUserOrderNumberInChunk start index of userId
	 * @param chunkSize number of grants to create
	 */
    void grantChunk(final LoadDataSettings loadDataSettings, final int firstUserOrderNumberInChunk, final int chunkSize);
}
