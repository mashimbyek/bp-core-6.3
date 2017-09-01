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
package de.hybris.platform.commercefacades.product.converters.populator;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.ModelService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


/**
 * Test suite for {@link ProductDescriptionPopulator}
 */
@UnitTest
public class ProductDescriptionPopulatorTest
{
	private static final String PRODUCT_DESCRIPTION = "proDesc";


	@Mock
	private ModelService modelService;

	private ProductDescriptionPopulator productDescriptionPopulator;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		productDescriptionPopulator = new ProductDescriptionPopulator();
		productDescriptionPopulator.setModelService(modelService);
	}


	@Test
	public void testPopulate()
	{
		final ProductModel source = mock(ProductModel.class);

		given(modelService.getAttributeValue(source, ProductModel.DESCRIPTION)).willReturn(PRODUCT_DESCRIPTION);

		final ProductData result = new ProductData();
		productDescriptionPopulator.populate(source, result);

		Assert.assertEquals(PRODUCT_DESCRIPTION, result.getDescription());
	}
}