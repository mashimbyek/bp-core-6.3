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
package de.hybris.platform.couponservices.strategies.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import de.hybris.platform.couponservices.CouponServiceException;
import de.hybris.platform.couponservices.dao.CouponDao;
import de.hybris.platform.couponservices.model.AbstractCouponModel;
import de.hybris.platform.couponservices.strategies.FindCouponStrategy;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;

import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;


/**
 * base class for the unit tests of the concrete find strategies
 */
@Ignore
public abstract class AbstractFindCouponStrategyUnitTest
{

	protected static final String COUPON_ID = "COUPON_ID";

	@Mock
	protected AbstractCouponModel abstractCoupon;

	@Mock
	protected CouponDao couponDao;


	abstract FindCouponStrategy getStrategy();

	@Before
	public void setup()
	{
		when(couponDao.findCouponById(COUPON_ID)).thenReturn(abstractCoupon);
		when(abstractCoupon.getActive()).thenReturn(Boolean.TRUE);
		when(abstractCoupon.getStartDate()).thenReturn(DateUtils.addDays(new Date(), -1));
		when(abstractCoupon.getEndDate()).thenReturn(DateUtils.addDays(new Date(), 1));
	}

	@Test
	public void testFindValidatedCoupon()
	{
		final Optional<AbstractCouponModel> foundCouponOptional = getStrategy().findValidatedCouponForCouponCode(COUPON_ID);
		assertTrue(foundCouponOptional.isPresent());
		assertEquals(abstractCoupon, foundCouponOptional.get());
	}

	@Test
	public void testFindCoupon()
	{
		final Optional<AbstractCouponModel> foundCouponOptional = getStrategy().findCouponForCouponCode(COUPON_ID);
		assertTrue(foundCouponOptional.isPresent());
		assertEquals(abstractCoupon, foundCouponOptional.get());
	}

	@Test
	public void testFindCouponNotFound()
	{
		when(couponDao.findCouponById(Matchers.anyString())).thenThrow(ModelNotFoundException.class);
		assertEquals(Optional.empty(), getStrategy().findValidatedCouponForCouponCode(COUPON_ID));
	}

	@Test(expected = CouponServiceException.class)
	public void testFindCouponNotActive()
	{
		when(abstractCoupon.getActive()).thenReturn(Boolean.FALSE);
		getStrategy().findValidatedCouponForCouponCode(COUPON_ID);
	}

	@Test(expected = CouponServiceException.class)
	public void testFindCouponNotStartDateReached()
	{
		when(abstractCoupon.getStartDate()).thenReturn(DateUtils.addDays(new Date(), 1));
		getStrategy().findValidatedCouponForCouponCode(COUPON_ID);
	}

	@Test(expected = CouponServiceException.class)
	public void testFindCouponEndDateExceeded()
	{
		when(abstractCoupon.getEndDate()).thenReturn(DateUtils.addDays(new Date(), -1));
		getStrategy().findValidatedCouponForCouponCode(COUPON_ID);
	}

}
