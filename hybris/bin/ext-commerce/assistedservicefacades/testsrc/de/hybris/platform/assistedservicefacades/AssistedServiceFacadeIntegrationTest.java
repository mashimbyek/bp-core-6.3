/*
 *
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
package de.hybris.platform.assistedservicefacades;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.assistedserviceservices.exception.AssistedServiceAgentBadCredentialsException;
import de.hybris.platform.assistedserviceservices.exception.AssistedServiceException;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.commerceservices.order.CommerceCheckoutService;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.EmployeeModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * Additional tests for ASM Facade, for some methods, that was not involved in atdd tests.
 */
@IntegrationTest
public class AssistedServiceFacadeIntegrationTest extends ServicelayerTransactionalTest
{
	@Resource
	private UserService userService;
	@Resource
	private AssistedServiceFacade assistedServiceFacade;
	@Resource
	private BaseSiteService baseSiteService;
	@Resource
	private CartService cartService;
	@Resource
	private ProductService productService;
	@Resource
	private CommerceCartService commerceCartService;
	@Resource
	private CommerceCheckoutService commerceCheckoutService;

	private static final String ASCUSTOMER_UID = "ascustomer";
	private static final String ASAGENT_UID = "asagent";
	private static final String ASAGENT_PWD = "1234";


	@Before
	public void setUp() throws Exception
	{
		importCsv("/assistedservicefacades/test/asm.impex", "UTF-8");
		baseSiteService.setCurrentBaseSite("testSite", true);
	}

	@Test
	public void testLoginAgentSAML() throws AssistedServiceException
	{
		final EmployeeModel customer = userService.getUserForUID(ASAGENT_UID, EmployeeModel.class);
		// shouldn't throw an exception
		assistedServiceFacade.loginAssistedServiceAgentSAML(customer.getUid(), customer.getEncodedPassword());
	}


	@Test(expected = AssistedServiceAgentBadCredentialsException.class)
	public void testLoginCustomerSAMLFail() throws AssistedServiceException
	{
		// should throw an exception
		assistedServiceFacade.loginAssistedServiceAgentSAML(ASCUSTOMER_UID, "wrong password");
	}

	@Test
	public void testGetSuggestedCustomerList() throws AssistedServiceException
	{
		assistedServiceFacade.launchAssistedServiceMode();
		assistedServiceFacade.loginAssistedServiceAgent(ASAGENT_UID, ASAGENT_PWD);

		final List<CustomerData> customerListWithOne = assistedServiceFacade.getSuggestedCustomerList("bre");
		assertEquals(1, customerListWithOne.size());

		final List<CustomerData> customerListWith2 = assistedServiceFacade.getSuggestedCustomerList("joh");
		assertEquals(4, customerListWith2.size());

		final List<CustomerData> emptyCustomerList = assistedServiceFacade.getSuggestedCustomerList("afasfaf");
		assertEquals(0, emptyCustomerList.size());
	}

	@Test
	public void testLoginWithSamlCookieWithException() throws AssistedServiceException
	{
		final String cookieName = "samlPassThroughToken";

		final MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
		final Cookie[] cookies = new Cookie[1];
		cookies[0] = new Cookie(cookieName, "value");
		httpServletRequest.setCookies(cookies);

		// we passing here wrong cookie to get exception
		try
		{
			assistedServiceFacade.loginAssistedServiceAgent(httpServletRequest);
		}
		catch (final AssistedServiceException ex)
		{
			Assert.assertTrue(ex instanceof AssistedServiceAgentBadCredentialsException);
		}
	}

	@Test
	@Ignore // Ignore just for now
	public void testCreateCustomer() throws AssistedServiceException
	{
		final String customerID = "customerID";
		assistedServiceFacade.createCustomer(customerID, "John Doe");

		final UserModel customer = userService.getUserForUID(customerID);

		assertEquals(customerID, customer.getUid());
		assertEquals("John Doe", customer.getName());
	}

	@Test
	public void testEmulateCustomerAndCreateAnOrder()
			throws AssistedServiceException, CommerceCartModificationException, InvalidCartException
	{
		assistedServiceFacade.launchAssistedServiceMode();
		assistedServiceFacade.loginAssistedServiceAgent(ASAGENT_UID, ASAGENT_PWD);
		assistedServiceFacade.emulateCustomer(ASCUSTOMER_UID, null);

		assertEquals(cartService.getSessionCart().getCode(), "ascustomerCart"); // check that latest cart being picked up

		// add something to cart
		final CommerceCartParameter cartParameter = new CommerceCartParameter();
		cartParameter.setBaseSite(baseSiteService.getCurrentBaseSite());
		cartParameter.setCart(cartService.getSessionCart());
		cartParameter.setEnableHooks(true);
		cartParameter.setProduct(productService.getProductForCode("HW1210-3422"));
		cartParameter.setQuantity(4);

		commerceCartService.addToCart(cartParameter);
		final CommerceCheckoutParameter parameter = new CommerceCheckoutParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(cartService.getSessionCart());
		parameter.setSalesApplication(SalesApplication.WEB);
		final OrderModel result = commerceCheckoutService.placeOrder(parameter).getOrder();

		assertEquals(result.getPlacedBy().getUid(), ASAGENT_UID);
	}
}
