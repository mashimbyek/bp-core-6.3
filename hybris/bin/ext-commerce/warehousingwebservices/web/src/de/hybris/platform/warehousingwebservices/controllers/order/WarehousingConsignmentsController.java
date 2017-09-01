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
package de.hybris.platform.warehousingwebservices.controllers.order;

import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.commercefacades.order.data.ConsignmentData;
import de.hybris.platform.commercefacades.order.data.ConsignmentEntryData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.commercewebservicescommons.dto.order.ConsignmentWsDTO;
import de.hybris.platform.warehousing.enums.DeclineReason;
import de.hybris.platform.warehousingfacade.constants.WarehousingfacadeConstants;
import de.hybris.platform.warehousingfacade.order.WarehousingConsignmentFacade;
import de.hybris.platform.warehousingfacade.order.data.ConsignmentReallocationData;
import de.hybris.platform.warehousingfacade.order.data.ConsignmentStatusDataList;
import de.hybris.platform.warehousingfacade.order.data.DeclineReasonDataList;
import de.hybris.platform.warehousingfacade.order.data.PackagingInfoData;
import de.hybris.platform.warehousingfacade.storelocator.data.WarehouseData;
import de.hybris.platform.warehousingwebservices.controllers.WarehousingBaseController;
import de.hybris.platform.warehousingwebservices.dto.order.ConsignmentReallocationWsDTO;
import de.hybris.platform.warehousingwebservices.dto.order.ConsignmentEntrySearchPageWsDto;
import de.hybris.platform.warehousingwebservices.dto.order.ConsignmentSearchPageWsDto;
import de.hybris.platform.warehousingwebservices.dto.order.ConsignmentStatusListWsDTO;
import de.hybris.platform.warehousingwebservices.dto.order.DeclineReasonListWsDTO;
import de.hybris.platform.warehousingwebservices.dto.order.PackagingInfoWsDTO;
import de.hybris.platform.warehousingwebservices.dto.store.WarehouseSearchPageWsDto;
import de.hybris.platform.webservicescommons.errors.exceptions.WebserviceValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * WebResource exposing {@link de.hybris.platform.warehousingfacade.order.WarehousingConsignmentFacade}
 * http://host:port/warehousingwebservices/consignments
 */
@Controller
@RequestMapping(value = "/consignments")
public class WarehousingConsignmentsController extends WarehousingBaseController
{
	@Resource
	private WarehousingConsignmentFacade warehousingConsignmentFacade;

	@Resource(name = "packagingInfoDTOValidator")
	private Validator packagingInfoDTOValidator;

	@Resource(name = "consignmentReallocationValidator")
	private Validator consignmentReallocationValidator;

	@Resource(name = "declineEntryValidator")
	private Validator declineEntryValidator;

	/**
	 * Request to get all consignments in the system
	 *
	 * @param fields
	 * 		defaulted to DEFAULT but can be FULL or BASIC
	 * @param currentPage
	 * 		number of the current page
	 * @param pageSize
	 * 		number of items in a page
	 * @param sort
	 * 		sorting the results ascending or descending
	 * @return list of consignments
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ConsignmentSearchPageWsDto getConsignments(
			@RequestParam(required = false, defaultValue = DEFAULT_FIELD_SET) final String fields,
			@RequestParam(required = false, defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
			@RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
			@RequestParam(required = false, defaultValue = DEFAULT_SORT) final String sort)
	{
		final PageableData pageableData = createPageable(currentPage, pageSize, sort);
		final SearchPageData<ConsignmentData> consignmentSearchPageData = warehousingConsignmentFacade
				.getConsignments(pageableData);
		return dataMapper.map(consignmentSearchPageData, ConsignmentSearchPageWsDto.class, fields);
	}

	/**
	 * Request to get Consignment for the given code
	 *
	 * @param fields
	 * 		defaulted to DEFAULT but can be FULL or BASIC
	 * @param code
	 * 		code to get the required consignment
	 * @return consignment details for the given code
	 */
	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	@ResponseBody
	public ConsignmentWsDTO getConsignmentForCode(@PathVariable final String code,
			@RequestParam(required = false, defaultValue = DEFAULT_FIELD_SET) final String fields)
	{
		final ConsignmentData consignment = warehousingConsignmentFacade.getConsignmentForCode(code);
		return dataMapper.map(consignment, ConsignmentWsDTO.class, fields);
	}

	/**
	 * Request to get all sourcing locations for the given {@value de.hybris.platform.ordersplitting.model.ConsignmentModel#CODE}
	 *
	 * @param code
	 * 		consignment's code for the requested sourcing location
	 * @param fields
	 * 		defaulted to DEFAULT but can be FULL or BASIC
	 * @param currentPage
	 * 		number of the current page
	 * @param pageSize
	 * 		number of items in a page
	 * @param sort
	 * 		sorting the results ascending or descending
	 * @return list of locations compliant to the above conditions
	 */
	@RequestMapping(value = "/{code}/sourcing-locations", method = RequestMethod.GET)
	@ResponseBody
	public WarehouseSearchPageWsDto getSourcingLocationsForConsignmentCode(@PathVariable final String code,
			@RequestParam(required = false, defaultValue = DEFAULT_FIELD_SET) final String fields,
			@RequestParam(required = false, defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
			@RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
			@RequestParam(required = false, defaultValue = DEFAULT_SORT) final String sort)
	{
		final PageableData pageableData = createPageable(currentPage, pageSize, sort);
		final SearchPageData<WarehouseData> warehouseSearchPageData = warehousingConsignmentFacade
				.getSourcingLocationsForConsignmentCode(code, pageableData);

		return dataMapper.map(warehouseSearchPageData, WarehouseSearchPageWsDto.class, fields);
	}

	/**
	 * Request to get all consignments with certain consignment status(es)
	 *
	 * @param consignmentStatuses
	 * 		a list of valid consignment statuses separated by ","
	 * @param fields
	 * 		defaulted to DEFAULT but can be FULL or BASIC
	 * @param currentPage
	 * 		number of the current page
	 * @param pageSize
	 * 		number of items in a page
	 * @param sort
	 * 		sorting the results ascending or descending
	 * @return list of consignments that complies with conditions above
	 * @throws WebserviceValidationException
	 * 		in case of passing a wrong {@link ConsignmentStatus}
	 */
	@RequestMapping(value = "status/{consignmentStatuses}", method = RequestMethod.GET)
	@ResponseBody
	public ConsignmentSearchPageWsDto getConsignmentsByStatus(@PathVariable final String consignmentStatuses,
			@RequestParam(required = false, defaultValue = DEFAULT_FIELD_SET) final String fields,
			@RequestParam(required = false, defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
			@RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
			@RequestParam(required = false, defaultValue = DEFAULT_SORT) final String sort) throws WebserviceValidationException
	{
		final Set<ConsignmentStatus> statusSet = extractConsignmentStatuses(consignmentStatuses);
		final PageableData pageableData = createPageable(currentPage, pageSize, sort);
		final SearchPageData<ConsignmentData> consignmentSearchPageData = warehousingConsignmentFacade
				.getConsignmentsByStatuses(pageableData, statusSet);
		return dataMapper.map(consignmentSearchPageData, ConsignmentSearchPageWsDto.class, fields);
	}

	/**
	 * Request to get all {@link ConsignmentStatus} in the system
	 *
	 * @return list of consignment statuses
	 */
	@RequestMapping(value = "/statuses", method = RequestMethod.GET)
	@ResponseBody
	public ConsignmentStatusListWsDTO getConsignmentStatuses()
	{
		final List<ConsignmentStatus> consignmentStatuses = warehousingConsignmentFacade.getConsignmentStatuses();
		final ConsignmentStatusDataList consignmentStatusList = new ConsignmentStatusDataList();
		consignmentStatusList.setStatuses(consignmentStatuses);
		return dataMapper.map(consignmentStatusList, ConsignmentStatusListWsDTO.class);
	}

	/**
	 * Request to get all decline reasons available in the system.
	 *
	 * @return list of {@link DeclineReason}
	 */
	@RequestMapping(value = "/decline-reasons", method = RequestMethod.GET)
	@ResponseBody
	public DeclineReasonListWsDTO getDeclineReasons()
	{
		final List<DeclineReason> declineReasons = warehousingConsignmentFacade.getDeclineReasons();
		final DeclineReasonDataList declineReasonList = new DeclineReasonDataList();
		declineReasonList.setReasons(declineReasons);
		return dataMapper.map(declineReasonList, DeclineReasonListWsDTO.class);
	}

	/**
	 * Request to get all consignment entries for the given {@value de.hybris.platform.ordersplitting.model.ConsignmentModel#CODE}
	 *
	 * @param code
	 * 		consignment's code for the requested consignment entries
	 * @param fields
	 * 		defaulted to DEFAULT but can be FULL or BASIC
	 * @param currentPage
	 * 		number of the current page
	 * @param pageSize
	 * 		number of items in a page
	 * @param sort
	 * 		sorting the results ascending or descending
	 * @return list of consignmentEntries fulfilling the above conditions
	 */
	@RequestMapping(value = "/{code}/entries", method = RequestMethod.GET)
	@ResponseBody
	public ConsignmentEntrySearchPageWsDto getConsignmentEntriesForConsignmentCode(@PathVariable final String code,
			@RequestParam(required = false, defaultValue = DEFAULT_FIELD_SET) final String fields,
			@RequestParam(required = false, defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
			@RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
			@RequestParam(required = false, defaultValue = DEFAULT_SORT) final String sort)
	{
		final PageableData pageableData = createPageable(currentPage, pageSize, sort);
		final SearchPageData<ConsignmentEntryData> consignmentEntrySearchPageData = warehousingConsignmentFacade
				.getConsignmentEntriesForConsignmentCode(code, pageableData);
		return dataMapper.map(consignmentEntrySearchPageData, ConsignmentEntrySearchPageWsDto.class, fields);
	}

	/**
	 * Request to confirm Consignment's shipping
	 *
	 * @param code
	 * 		consignment's code for the requested consignment
	 */
	@RequestMapping(value = "{code}/confirm-shipping", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public void confirmShipConsignment(@PathVariable final String code)
	{
		warehousingConsignmentFacade.confirmShipConsignment(code);
	}

	/**
	 * Request to confirm Consignment's pickup
	 *
	 * @param code
	 * 		consignment's code for the requested consignment
	 */
	@RequestMapping(value = "{code}/confirm-pickup", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public void confirmPickupConsignment(@PathVariable final String code)
	{
		warehousingConsignmentFacade.confirmPickupConsignment(code);
	}

	/**
	 * Request to check if Consignment can be confirmed
	 *
	 * @param code
	 * 		consignment's code for the requested consignment
	 */
	@RequestMapping(value = "{code}/is-confirmable", method = RequestMethod.GET)
	@ResponseBody
	public Boolean isConsignmentConfirmable(@PathVariable final String code)
	{
		return warehousingConsignmentFacade.isConsignmentConfirmable(code);
	}

	/**
	 * Request to get {@link PackagingInfoWsDTO} for the given consignment code
	 *
	 * @param code
	 * 		code of the consignment for which to get the packaging information
	 * @return the packaging information of the consignment
	 */
	@RequestMapping(value = "/{code}/packaging-info", method = RequestMethod.GET)
	@ResponseBody
	public PackagingInfoWsDTO getPackagingInfo(@PathVariable @NotNull final String code)
	{
		final PackagingInfoData packagingInfo = warehousingConsignmentFacade.getConsignmentPackagingInformation(code);
		return dataMapper.map(packagingInfo, PackagingInfoWsDTO.class);
	}

	/**
	 * Request to update a {@link de.hybris.platform.ordersplitting.model.ConsignmentModel} with a new Packaging information.
	 *
	 * @param packagingInfo
	 * 		the packaging information to update the consignment with
	 * @param code
	 * 		code of the consignment for which to update the packaging information
	 * @param fields
	 * 		defaulted to DEFAULT but can be FULL or BASIC
	 * @return updated consignment
	 */
	@RequestMapping(value = "/{code}/packaging-info", method = RequestMethod.PUT,
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ConsignmentWsDTO updatePackagingInfo(@RequestBody final PackagingInfoWsDTO packagingInfo,
			@PathVariable @NotNull final String code,
			@RequestParam(required = false, defaultValue = DEFAULT_FIELD_SET) final String fields)
	{
		validate(packagingInfo, "packagingInfo", getPackagingInfoDTOValidator());
		final PackagingInfoData packagingInfoData = dataMapper.map(packagingInfo, PackagingInfoData.class, fields);
		final ConsignmentData consignmentData = warehousingConsignmentFacade
				.updateConsignmentPackagingInformation(code, packagingInfoData);
		return dataMapper.map(consignmentData, ConsignmentWsDTO.class, fields);
	}

	/**
	 * Request to reallocate a {@link de.hybris.platform.ordersplitting.model.ConsignmentModel} to a new warehouse.
	 *
	 * @param consignmentReallocationWsDTO
	 * 		the dto containing entries to be reallocated
	 * @param code
	 * 		code of the consignment to be reallocated
	 */
	@RequestMapping(value = "/{code}/reallocate", method = RequestMethod.POST,
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void reallocateConsignment(@RequestBody final ConsignmentReallocationWsDTO consignmentReallocationWsDTO,
			@PathVariable @NotNull final String code)
	{
		validate(consignmentReallocationWsDTO, "consignmentReallocationWsDTO", consignmentReallocationValidator);
		consignmentReallocationWsDTO.getDeclineEntries().forEach(declineEntryWsDTO -> validate(declineEntryWsDTO, "declineEntryWsDTO", declineEntryValidator));
		final ConsignmentReallocationData consignmentReallocationData = dataMapper
				.map(consignmentReallocationWsDTO, ConsignmentReallocationData.class);
		warehousingConsignmentFacade.reallocateConsignment(code, consignmentReallocationData);
	}


	/**
	 * Extract the set of {@link ConsignmentStatus} from the request
	 *
	 * @param statuses
	 * 		"," separated {@link ConsignmentStatus}
	 * @return set of {@link ConsignmentStatus}
	 * @throws WebserviceValidationException
	 * 		in case of passing a wrong {@link ConsignmentStatus}
	 */
	protected Set<ConsignmentStatus> extractConsignmentStatuses(final String statuses)
	{
		final String statusesStrings[] = statuses.split(WarehousingfacadeConstants.OPTIONS_SEPARATOR);

		final Set<ConsignmentStatus> statusesEnum = new HashSet<>();
		try
		{
			for (final String status : statusesStrings)
			{
				statusesEnum.add(ConsignmentStatus.valueOf(status));
			}
		}
		catch (final IllegalArgumentException e)  //NOSONAR
		{
			throw new WebserviceValidationException(e.getMessage()); //NOSONAR
		}
		return statusesEnum;
	}

	public void setPackagingInfoDTOValidator(final Validator packagingInfoDTOValidator)
	{
		this.packagingInfoDTOValidator = packagingInfoDTOValidator;
	}

	protected Validator getPackagingInfoDTOValidator()
	{
		return packagingInfoDTOValidator;
	}

	protected Validator getConsignmentReallocationValidator()
	{
		return consignmentReallocationValidator;
	}

	public void setConsignmentReallocationDTOValidator(final Validator consignmentReallocationValidator)
	{
		this.consignmentReallocationValidator = consignmentReallocationValidator;
	}
}
