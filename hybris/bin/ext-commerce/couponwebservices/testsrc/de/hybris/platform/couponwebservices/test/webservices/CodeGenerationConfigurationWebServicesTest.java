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
package de.hybris.platform.couponwebservices.test.webservices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.commerceservices.search.dao.PagedGenericDao;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.couponservices.model.CodeGenerationConfigurationModel;
import de.hybris.platform.couponwebservices.constants.CouponwebservicesConstants;
import de.hybris.platform.oauth2.constants.OAuth2Constants;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.webservicescommons.dto.error.ErrorListWsDTO;
import de.hybris.platform.webservicescommons.dto.error.ErrorWsDTO;
import de.hybris.platform.webservicescommons.testsupport.client.WebservicesAssert;
import de.hybris.platform.webservicescommons.testsupport.client.WsRequestBuilder;
import de.hybris.platform.webservicescommons.testsupport.client.WsSecuredRequestBuilder;
import de.hybris.platform.webservicescommons.testsupport.server.NeedsEmbeddedServer;

import java.util.Optional;

import javax.annotation.Resource;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;


@NeedsEmbeddedServer(webExtensions =
{ CouponwebservicesConstants.EXTENSIONNAME, OAuth2Constants.EXTENSIONNAME })
@IntegrationTest
public class CodeGenerationConfigurationWebServicesTest extends ServicelayerTest
{
	public static final String OAUTH_CLIENT_ID = "coupon_user";
	public static final String OAUTH_CLIENT_PASS = "secret";

	private static final String BASE_URI = "couponservices";
	private static final String URI = BASE_URI + "/codegenerationconfiguration";

	private WsRequestBuilder wsRequestBuilder;
	private WsSecuredRequestBuilder wsSecuredRequestBuilder;

	@Resource(name = "codeGenerationConfigurationPagedGenericDao")
	private PagedGenericDao<CodeGenerationConfigurationModel> codeGenerationConfigurationPagedGenericDao;

	@Resource
	private ModelService modelService;

	@Before
	public void setUp() throws Exception
	{
		wsRequestBuilder = new WsRequestBuilder()//
				.extensionName(CouponwebservicesConstants.EXTENSIONNAME);

		wsSecuredRequestBuilder = new WsSecuredRequestBuilder()//
				.extensionName(CouponwebservicesConstants.EXTENSIONNAME)//
				.client(OAUTH_CLIENT_ID, OAUTH_CLIENT_PASS)//
				.grantClientCredentials();

		createCoreData();
		createDefaultUsers();
		importCsv("/couponwebservices/test/coupon-test-data.impex", "utf-8");
	}

	@Test
	public void testGetCodeGenerationConfigurationsWithoutAuthorization()
	{
		final Response result = wsRequestBuilder//
				.path(URI)//
				.path("list")//
				.build()//
				.accept(MediaType.APPLICATION_XML)//
				.get();
		result.bufferEntity();
		WebservicesAssert.assertResponse(Status.UNAUTHORIZED, Optional.empty(), result);
	}

	@Test
	public void testGetCodeGenerationConfigurationsUsingClientCredentials()
	{
		final Response result = wsSecuredRequestBuilder//
				.path(URI)//
				.path("list")//
				.build()//
				.accept(MediaType.APPLICATION_XML)//
				.get();
		result.bufferEntity();
		WebservicesAssert.assertResponse(Status.OK, Optional.empty(), result);
	}

	@Test
	public void testGetCodeGenerationConfigurationWithoutAuthorization()
	{
		final Response result = wsRequestBuilder//
				.path(URI)//
				.path("get")//
				.path("default-configuration")//
				.build()//
				.accept(MediaType.APPLICATION_XML)//
				.get();
		result.bufferEntity();
		WebservicesAssert.assertResponse(Status.UNAUTHORIZED, Optional.empty(), result);
	}

	@Test
	public void testGetCodeGenerationConfigurationUsingClientCredentials()
	{
		final Response result = wsSecuredRequestBuilder//
				.path(URI)//
				.path("get")//
				.path("default-configuration")//
				.build()//
				.accept(MediaType.APPLICATION_XML)//
				.get();
		result.bufferEntity();
		WebservicesAssert.assertResponse(Status.OK, Optional.empty(), result);
	}

	/**
	 * The test should detect the fail with 1 error:</br>
	 * errors" : [ {</br>
	 * "message" : "No Code Generation Configuration found for name [INVALID_CODE_GENERATION_CONFIGURATION]",</br>
	 * "reason" : "invalid",</br>
	 * "subject" : "codeGenerationConfiguration",</br>
	 * "type" : "CodeGenerationConfigurationNotFoundError"</br>
	 * } ]</br>
	 **/
	@Test
	public void testInvalidCodeGenerationConfigurationNameError()
	{
		final Response response = wsSecuredRequestBuilder//
				.path(URI)//
				.path("get")//
				.path("INVALID_CODE_GENERATION_CONFIGURATION")//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.get();
		response.bufferEntity();
		WebservicesAssert.assertResponse(Status.NOT_FOUND, Optional.empty(), response);
		final ErrorListWsDTO errors = response.readEntity(ErrorListWsDTO.class);
		assertNotNull(errors);
		assertNotNull(errors.getErrors());
		assertEquals(1, errors.getErrors().size());
		final ErrorWsDTO error = errors.getErrors().get(0);
		assertEquals("invalid", error.getReason());
		assertEquals("codeGenerationConfiguration", error.getSubject());
	}

	@Test
	public void testGetCodeGenerationConfigurationUsingWrongMediaType()
	{
		final Response result = wsSecuredRequestBuilder//
				.path(URI)//
				.path("get")//
				.path("default-configuration")//
				.build()//
				.accept(MediaType.APPLICATION_XHTML_XML)//
				.get();
		result.bufferEntity();
		WebservicesAssert.assertResponse(Status.NOT_ACCEPTABLE, Optional.empty(), result);
	}

	@Test
	public void testGetCodeGenerationConfigurationsUsingWrongMediaType()
	{
		final Response result = wsSecuredRequestBuilder//
				.path(URI)//
				.path("list")//
				.build()//
				.accept(MediaType.APPLICATION_XHTML_XML)//
				.get();
		result.bufferEntity();
		WebservicesAssert.assertResponse(Status.NOT_ACCEPTABLE, Optional.empty(), result);
	}

	@Test
	public void testGetCodeGenerationConfigurationsWhenNoConfigurationExists()
	{
		final PageableData pageableData = new PageableData();
		pageableData.setSort("asc");
		pageableData.setPageSize(100);
		pageableData.setCurrentPage(0);
		final SearchPageData<CodeGenerationConfigurationModel> codeGenerationConfigurationSearchPageData = codeGenerationConfigurationPagedGenericDao
				.find(pageableData);
		modelService.removeAll(codeGenerationConfigurationSearchPageData.getResults());
		modelService.saveAll();
		final Response result = wsSecuredRequestBuilder//
				.path(URI)//
				.path("list")//
				.build()//
				.accept(MediaType.APPLICATION_JSON)//
				.get();
		result.bufferEntity();
		WebservicesAssert.assertResponse(Status.NOT_FOUND, Optional.empty(), result);
		final ErrorListWsDTO errors = result.readEntity(ErrorListWsDTO.class);
		assertNotNull(errors);
		assertNotNull(errors.getErrors());
		assertEquals(1, errors.getErrors().size());
		final ErrorWsDTO error = errors.getErrors().get(0);
		assertEquals("No Records", error.getReason());
	}

}
