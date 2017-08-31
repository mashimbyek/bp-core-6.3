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
 *
 */
package de.hybris.platform.assistedservicestorefront.customer360.populator;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.assistedservicestorefront.customer360.CustomerProfileData;
import de.hybris.platform.assistedservicestorefront.customer360.populators.CustomerProfileDataPopulator;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;

@UnitTest
public class CustomerProfileDataPopulatorTest
{

    @Mock
    private Converter<AddressModel, AddressData> addressConverter;

    @InjectMocks
    private CustomerProfileDataPopulator customerProfileDataPopulator;

    @Before
    public void setup()
    {
        customerProfileDataPopulator = new CustomerProfileDataPopulator();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getModelTest()
    {
        final String ph1 = "uid@user.uid";

        final CustomerModel customerModel = Mockito.mock(CustomerModel.class);
        final AddressModel shipAddressModel = Mockito.mock(AddressModel.class);
        final AddressData addressData = Mockito.mock(AddressData.class);

        Mockito.when(customerModel.getDefaultShipmentAddress()).thenReturn(shipAddressModel);
        Mockito.when(shipAddressModel.getPhone1()).thenReturn(ph1);

        Mockito.when(customerModel.getDefaultPaymentAddress()).thenReturn(null);

        Mockito.when(addressConverter.convert(Mockito.anyObject())).thenReturn(addressData);
        Mockito.when(customerModel.getDefaultPaymentInfo()).thenReturn(null);

        final CustomerProfileData profileData = new CustomerProfileData();
        customerProfileDataPopulator.populate(customerModel, profileData);

        Assert.assertEquals(ph1, profileData.getPhone1());
        Assert.assertEquals(addressData, profileData.getDeliveryAddress());
    }
}

