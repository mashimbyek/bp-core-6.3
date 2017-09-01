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
 */
package de.hybris.platform.warehousingwebservices.warehousingwebservices;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.commercewebservicescommons.dto.order.ConsignmentWsDTO;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.warehousing.enums.DeclineReason;
import de.hybris.platform.warehousingwebservices.dto.order.ConsignmentReallocationWsDTO;
import de.hybris.platform.warehousingwebservices.dto.order.DeclineEntryWsDTO;
import de.hybris.platform.warehousingwebservices.warehousingwebservices.util.BaseWarehousingWebservicesIntegrationTest;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


@IntegrationTest
public class ConsignmentsControllerIntegrationTest extends BaseWarehousingWebservicesIntegrationTest
{
	private OrderModel order;

	@Before
	public void setup()
	{
		order = createConsignmentAndOrder();
	}

	@Test
	public void getAllConsignments()
	{
		//when
		Response result = getAllConsignmentsByDefault();
		//then
		assertEquals("2", getNodeByXpath(result, "count(//consignments/code)"));
		assertEquals("con_0", getNodeByXpath(result, "//consignments[code='con_0']/code"));
		assertEquals("con_1", getNodeByXpath(result, "//consignments[code='con_1']/code"));
	}

	@Test
	public void getConsignmentForCode()
	{
		//When
		final Response result = getConsignmentsForCodeByDefault("con_0");
		//then
		assertEquals("1", getNodeByXpath(result, "count(//consignment/code)"));
		assertEquals("con_0", getNodeByXpath(result, "//consignment/code"));
	}

	@Test
	public void getConsignmentStatus()
	{
		//When
		final Response result = getConsignmentStatusByDefault();
		//then
		assertEquals("8", getNodeByXpath(result, "count(//consignmentStatusList/statuses)"));
	}

	@Test
	public void getDeclineReasons()
	{
		//When
		final Response result = getDeclineReasonsByDefault();
		//then
		assertEquals("5", getNodeByXpath(result, "count(//declineReasonList/reasons)"));

		List<DeclineReason> declineReasonList = getEnumerationService().getEnumerationValues(DeclineReason._TYPECODE);
		assertTrue(
				declineReasonList.stream()
						.anyMatch(reason -> reason.name().equals(getNodeByXpath(result, "//declineReasonList/reasons"))));
	}

	@Test
	public void getConsignmentEntries()
	{
		//When
		final Response result = getConsignmentEntriesByDefault("con_0");
		//then
		assertEquals("1", getNodeByXpath(result, "count(//consignmentEntries)"));
	}

	@Test
	public void getSourcingLocations()
	{
		//When
		final Response result = getSourcingLocationsByDefault("con_0");
		//then
		assertEquals("1", getNodeByXpath(result, "count(//warehouses)"));
		assertEquals("boston", getNodeByXpath(result, "//warehouses/code"));
	}

	@Test
	public void getPackagingInfo()
	{
		//When
		final Response result = getPackagingInfoByDefault("con_0");
		//then
		assertEquals("0", getNodeByXpath(result, "//packagingInfo/height"));
		assertEquals("0", getNodeByXpath(result, "//packagingInfo/insuredValue"));
		assertEquals("0", getNodeByXpath(result, "//packagingInfo/length"));
		assertEquals("0", getNodeByXpath(result, "//packagingInfo/width"));
		assertEquals("0", getNodeByXpath(result, "//packagingInfo/grossWeight"));
		assertEquals("kg", getNodeByXpath(result, "//packagingInfo/weightUnit"));
		assertEquals("cm", getNodeByXpath(result, "//packagingInfo/dimensionUnit"));
	}

	@Test
	public void updatePackagingInfo()
	{
		//When
		final ConsignmentWsDTO result = updatePackagingInfoByDefault("con_0",
				createPackagingInfo("1", "2", "3", "4", "5", "in", "lb"));
		//then
		assertEquals("1", result.getPackagingInfo().getWidth());
		assertEquals("2", result.getPackagingInfo().getHeight());
		assertEquals("3", result.getPackagingInfo().getLength());
		assertEquals("4", result.getPackagingInfo().getGrossWeight());
		assertEquals("5", result.getPackagingInfo().getInsuredValue());
		assertEquals("in", result.getPackagingInfo().getDimensionUnit());
		assertEquals("lb", result.getPackagingInfo().getWeightUnit());
	}

	@Test
	public void reallocateConsignment()
	{
		//Given
		order.setStatus(OrderStatus.READY);
		order.getConsignments().forEach(consignment ->
		{
			consignment.setStatus(ConsignmentStatus.READY);
			getModelService().save(consignment);
		});
		getModelService().save(order);

		final ConsignmentReallocationWsDTO consignmentReallocationWsDTO = createConsignmentReallocationWsDTO();
		//When
		final Response result = postDefaultRestCall("consignments/con_0/reallocate", DEFAULT_FIELDS, consignmentReallocationWsDTO);
		//then
		assertEquals(result.getStatus(), 200);
	}

	private ConsignmentReallocationWsDTO createConsignmentReallocationWsDTO()
	{
		final ConsignmentReallocationWsDTO consignmentReallocationWsDTO = new ConsignmentReallocationWsDTO();
		final DeclineEntryWsDTO declineEntryWsDTO = new DeclineEntryWsDTO();
		declineEntryWsDTO.setQuantity(2L);
		declineEntryWsDTO.setProductCode("camera");
		declineEntryWsDTO.setReason(DeclineReason.DAMAGED.toString());
		consignmentReallocationWsDTO.setDeclineEntries(Collections.singletonList(declineEntryWsDTO));
		return consignmentReallocationWsDTO;
	}
}
