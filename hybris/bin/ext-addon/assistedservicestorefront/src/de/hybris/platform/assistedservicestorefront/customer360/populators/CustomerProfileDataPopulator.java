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
package de.hybris.platform.assistedservicestorefront.customer360.populators;

import de.hybris.platform.assistedservicestorefront.customer360.CustomerProfileData;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;


/**
 * CustomerModel -> CustomerProfileData populator
 *
 */
public class CustomerProfileDataPopulator implements Populator<CustomerModel, CustomerProfileData>
{
	private CustomerAccountService customerAccountService;
	private Converter<AddressModel, AddressData> addressConverter;
	private Converter<CreditCardPaymentInfoModel, CCPaymentInfoData> creditCardPaymentInfoConverter;
	private int paymentMethodsListSize;

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final CustomerModel userModel, final CustomerProfileData customerProfileData) throws ConversionException
	{

		if (userModel.getDefaultShipmentAddress() != null)
		{
			customerProfileData.setDeliveryAddress(getAddressConverter().convert(userModel.getDefaultShipmentAddress()));
			if (userModel.getDefaultShipmentAddress().getPhone1() != null)
			{
				customerProfileData.setPhone1(userModel.getDefaultShipmentAddress().getPhone1());
			}
		}

		if (userModel.getDefaultPaymentAddress() != null)
		{
			customerProfileData.setBillingAddress(getAddressConverter().convert(userModel.getDefaultPaymentAddress()));
			if (userModel.getDefaultPaymentAddress().getPhone1() != null && customerProfileData.getPhone1() != null
					&& !customerProfileData.getPhone1().equals(userModel.getDefaultPaymentAddress().getPhone1()))
			{
				customerProfileData.setPhone2(userModel.getDefaultPaymentAddress().getPhone1());
			}
		}

		customerProfileData.setPaymentInfoList(getPaymentInfoList(userModel));
	}

	protected List<CCPaymentInfoData> getPaymentInfoList(final CustomerModel userModel)
	{
		final PaymentInfoModel defaultPaymentInfoModel = userModel.getDefaultPaymentInfo();
		List<CCPaymentInfoData> ccPaymentInfoData = null;
		if (defaultPaymentInfoModel != null)
		{
			List<CreditCardPaymentInfoModel> creditCardPaymentInfos = new ArrayList<>();
			creditCardPaymentInfos.addAll(getCustomerAccountService().getCreditCardPaymentInfos(userModel, true));
			creditCardPaymentInfos.sort((p1, p2) -> {
				if (p1.getCode().equals(defaultPaymentInfoModel.getCode()))
				{
					return -1;
				}
				if (p2.getCode().equals(defaultPaymentInfoModel.getCode()))
				{
					return 1;
				}
				return p1.getCreationtime().compareTo(p2.getCreationtime());
			});
			if (creditCardPaymentInfos.size() > getPaymentMethodsListSize())
			{
				creditCardPaymentInfos = creditCardPaymentInfos.subList(0, getPaymentMethodsListSize());
			}
			ccPaymentInfoData = getCreditCardPaymentInfoConverter().convertAll(creditCardPaymentInfos);
			ccPaymentInfoData.get(0).setDefaultPaymentInfo(true);
		}
		return ccPaymentInfoData;
	}

	protected Converter<AddressModel, AddressData> getAddressConverter()
	{
		return addressConverter;
	}

	@Required
	public void setAddressConverter(final Converter<AddressModel, AddressData> addressConverter)
	{
		this.addressConverter = addressConverter;
	}

	protected CustomerAccountService getCustomerAccountService()
	{
		return customerAccountService;
	}

	@Required
	public void setCustomerAccountService(final CustomerAccountService customerAccountService)
	{
		this.customerAccountService = customerAccountService;
	}

	protected Converter<CreditCardPaymentInfoModel, CCPaymentInfoData> getCreditCardPaymentInfoConverter()
	{
		return creditCardPaymentInfoConverter;
	}

	@Required
	public void setCreditCardPaymentInfoConverter(
			final Converter<CreditCardPaymentInfoModel, CCPaymentInfoData> creditCardPaymentInfoConverter)
	{
		this.creditCardPaymentInfoConverter = creditCardPaymentInfoConverter;
	}

	protected int getPaymentMethodsListSize()
	{
		return paymentMethodsListSize;
	}

	@Required
	public void setPaymentMethodsListSize(final int paymentMethodsListSize)
	{
		this.paymentMethodsListSize = paymentMethodsListSize;
	}

}
