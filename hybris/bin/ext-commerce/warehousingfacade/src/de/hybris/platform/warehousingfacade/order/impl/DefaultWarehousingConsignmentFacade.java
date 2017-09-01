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
package de.hybris.platform.warehousingfacade.order.impl;


import static de.hybris.platform.servicelayer.util.ServicesUtil.validateIfSingleResult;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.google.common.collect.Sets;

import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.commercefacades.order.data.ConsignmentData;
import de.hybris.platform.commercefacades.order.data.ConsignmentEntryData;
import de.hybris.platform.commerceservices.model.PickUpDeliveryModeModel;
import de.hybris.platform.commerceservices.search.dao.PagedGenericDao;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.PaginationData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.ordermanagementfacade.OmsBaseFacade;
import de.hybris.platform.ordermanagementfacade.search.dao.impl.SearchByStatusPagedGenericDao;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.ConsignmentProcessModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.processengine.model.BusinessProcessParameterModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;
import de.hybris.platform.util.localization.Localization;
import de.hybris.platform.warehousing.constants.WarehousingConstants;
import de.hybris.platform.warehousing.data.allocation.DeclineEntries;
import de.hybris.platform.warehousing.data.allocation.DeclineEntry;
import de.hybris.platform.warehousing.enums.DeclineReason;
import de.hybris.platform.warehousing.model.PackagingInfoModel;
import de.hybris.platform.warehousing.process.WarehousingBusinessProcessService;
import de.hybris.platform.warehousing.shipping.service.WarehousingShippingService;
import de.hybris.platform.warehousing.sourcing.filter.SourcingFilterProcessor;
import de.hybris.platform.warehousing.stock.services.WarehouseStockService;
import de.hybris.platform.warehousingfacade.order.WarehousingConsignmentFacade;
import de.hybris.platform.warehousingfacade.order.data.ConsignmentReallocationData;
import de.hybris.platform.warehousingfacade.order.data.DeclineEntryData;
import de.hybris.platform.warehousingfacade.order.data.PackagingInfoData;
import de.hybris.platform.warehousingfacade.storelocator.data.WarehouseData;


/**
 * Default implementation of {@link WarehousingConsignmentFacade}.
 */
public class DefaultWarehousingConsignmentFacade extends OmsBaseFacade implements WarehousingConsignmentFacade
{
	private Converter<ConsignmentModel, ConsignmentData> consignmentConverter;
	private Converter<ConsignmentEntryModel, ConsignmentEntryData> consignmentEntryConverter;
	private Converter<WarehouseModel, WarehouseData> warehouseConverter;
	private Converter<PackagingInfoModel, PackagingInfoData> packagingInfoConverter;
	private Converter<PackagingInfoData, PackagingInfoModel> reversePackagingInfoConverter;
	private PagedGenericDao<ConsignmentModel> consignmentPagedGenericDao;
	private GenericDao<ConsignmentModel> consignmentGenericDao;
	private PagedGenericDao<ConsignmentModel> consignmentEntryPagedDao;
	private SearchByStatusPagedGenericDao<ConsignmentModel> consignmentSearchByStatusPagedDao;
	private EnumerationService enumerationService;
	private WarehousingShippingService warehousingShippingService;
	private SourcingFilterProcessor sourcingFilterProcessor;
	private WarehouseService warehouseService;
	private WarehouseStockService warehouseStockService;
	private WarehousingBusinessProcessService<ConsignmentModel> consignmentBusinessProcessService;
	private List<ConsignmentStatus> reallocableConsignmentStatusList;

	
	protected static final String CONSIGNMENT_ACTION_EVENT_NAME = "ConsignmentActionEvent";
	protected static final String REALLOCATE_CONSIGNMENT_CHOICE = "reallocateConsignment";
	protected static final String DECLINE_ENTRIES = "declineEntries";



	@Override
	public SearchPageData<ConsignmentData> getConsignments(final PageableData pageableData)
	{
		return convertSearchPageData(getConsignmentPagedGenericDao().find(pageableData), getConsignmentConverter());
	}

	@Override
	public SearchPageData<ConsignmentData> getConsignmentsByStatuses(final PageableData pageableData,
			final Set<ConsignmentStatus> consignmentStatusSet)
	{
		final Map<String, Object> params = new HashMap<>();
		params.put(ConsignmentModel.STATUS, consignmentStatusSet);
		return convertSearchPageData(getConsignmentSearchByStatusPagedDao().find(params, pageableData), getConsignmentConverter());
	}

	@Override
	public SearchPageData<ConsignmentEntryData> getConsignmentEntriesForConsignmentCode(final String code,
			final PageableData pageableData)
	{
		final ConsignmentModel consignment = getConsignmentModelForCode(code);

		final Map<String, ConsignmentModel> consignmentEntryParams = new HashMap<>();
		consignmentEntryParams.put(ConsignmentEntryModel.CONSIGNMENT, consignment);
		return convertSearchPageData(getConsignmentEntryPagedDao().find(consignmentEntryParams, pageableData),
				getConsignmentEntryConverter());
	}

	@Override
	public ConsignmentData getConsignmentForCode(final String code)
	{
		return getConsignmentConverter().convert(getConsignmentModelForCode(code));
	}

	@Override
	public List<ConsignmentStatus> getConsignmentStatuses()
	{
		return getEnumerationService().getEnumerationValues(ConsignmentStatus._TYPECODE);
	}

	@Override
	public List<DeclineReason> getDeclineReasons()
	{
		return getEnumerationService().getEnumerationValues(DeclineReason._TYPECODE);
	}

	@Override
	public SearchPageData<WarehouseData> getSourcingLocationsForConsignmentCode(final String code, final PageableData pageableData)
	{
		Assert.notNull(code, Localization.getLocalizedString("warehousingfacade.consignments.validation.null.code"));

		final ConsignmentModel consignmentModel = getConsignmentModelForCode(code);
		final Set<WarehouseModel> locations = Sets.newHashSet();
		getSourcingFilterProcessor().filterLocations(consignmentModel.getOrder(), locations);
		List<WarehouseModel> locationsList = new ArrayList<>(locations);
		Collections.sort(locationsList,
				(warehouseModel1, warehouseModel2) -> warehouseModel1.getCode().compareTo(warehouseModel2.getCode()));

		SearchPageData<WarehouseModel> searchPageData = new SearchPageData<>();
		searchPageData.setPagination(createPaginationData(pageableData, locations.size()));
		searchPageData.setResults(getSublistOfSourcingLocations(pageableData, locationsList));

		return convertSearchPageData(searchPageData, getWarehouseConverter());
	}

	@Override
	public void confirmShipConsignment(final String code)
	{
		Assert.notNull(code, Localization.getLocalizedString("warehousingfacade.consignments.validation.null.code"));
		Assert.isTrue(isConsignmentConfirmable(code),
				String.format(Localization.getLocalizedString("warehousingfacade.consignments.error.confirmable.consignment"), code));

		final ConsignmentModel consignmentModel = getConsignmentModelForCode(code);
		Assert.isTrue(!(consignmentModel.getDeliveryMode() instanceof PickUpDeliveryModeModel),
				String.format(Localization.getLocalizedString("warehousingfacade.consignments.error.ship.consignment"),
						consignmentModel.getCode()));

		getWarehousingShippingService().confirmShipConsignment(consignmentModel);
	}

	@Override
	public void confirmPickupConsignment(final String code)
	{
		Assert.notNull(code, Localization.getLocalizedString("warehousingfacade.consignments.validation.null.code"));
		Assert.isTrue(isConsignmentConfirmable(code),
				String.format(Localization.getLocalizedString("warehousingfacade.consignments.error.confirmable.consignment"), code));

		final ConsignmentModel consignmentModel = getConsignmentModelForCode(code);
		Assert.isTrue((consignmentModel.getDeliveryMode() instanceof PickUpDeliveryModeModel),
				String.format(Localization.getLocalizedString("warehousingfacade.consignments.error.pickup.consignment"),
						consignmentModel.getCode()));

		getWarehousingShippingService().confirmPickupConsignment(consignmentModel);
	}

	@Override
	public boolean isConsignmentConfirmable(final String code)
	{
		final ConsignmentModel consignmentModel = getConsignmentModelForCode(code);
		return getWarehousingShippingService().isConsignmentConfirmable(consignmentModel);
	}

	@Override
	public PackagingInfoData getConsignmentPackagingInformation(final String code)
	{
		Assert.notNull(code,
				Localization.getLocalizedString("warehousingfacade.consignments.packaginginfo.get.validation.null.code"));

		final ConsignmentModel consignmentModel = getConsignmentModelForCode(code);
		return getPackagingInfoConverter().convert(consignmentModel.getPackagingInfo());
	}

	@Override
	public ConsignmentData updateConsignmentPackagingInformation(final String code, final PackagingInfoData packagingInfoData)
	{
		Assert.notNull(code,
				Localization.getLocalizedString("warehousingfacade.consignments.packaginginfo.update.validation.null.code"));
		Assert.notNull(packagingInfoData,
				Localization.getLocalizedString("warehousingfacade.consignments.packaginginfo.update.validation.null.packaginginfo"));

		final ConsignmentModel consignmentModel = getConsignmentModelForCode(code);
		final PackagingInfoModel newPackagingInfo = getModelService().create(PackagingInfoModel.class);
		getReversePackagingInfoConverter().convert(packagingInfoData, newPackagingInfo);
		newPackagingInfo.setConsignment(consignmentModel);
		consignmentModel.setPackagingInfo(newPackagingInfo);

		getModelService().save(consignmentModel);
		getModelService().save(newPackagingInfo);

		return getConsignmentConverter().convert(consignmentModel);
	}

	@Override
	public void reallocateConsignment(final String consignmentCode, final ConsignmentReallocationData consignmentReallocationData)
	{
		validateParameterNotNull(consignmentCode,
				Localization.getLocalizedString("warehousingfacade.consignments.validation.null.code"));
		validateParameterNotNull(consignmentReallocationData, Localization
				.getLocalizedString("warehousingfacade.consignments.reallocation.validation.null.consignmentReallocationData"));
		Assert.isTrue(CollectionUtils.isNotEmpty(consignmentReallocationData.getDeclineEntries()),
				Localization.getLocalizedString("warehousingfacade.consignments.reallocation.validation.nothing.to.decline"));

		final ConsignmentModel consignment = getConsignmentModelForCode(consignmentCode);
		Assert.state(getReallocableConsignmentStatusList().contains(consignment.getStatus()), String.format(
				Localization.getLocalizedString("warehousingfacade.consignments.reallocation.validation.invalid.consignment.status"),
				consignment.getStatus()));

		final String consignmentProcessCode = consignment.getCode() + WarehousingConstants.CONSIGNMENT_PROCESS_CODE_SUFFIX;
		final Optional<ConsignmentProcessModel> myConsignmentProcess = consignment.getConsignmentProcesses().stream()
				.filter(consignmentProcess -> consignmentProcessCode.equals(consignmentProcess.getCode())).findFirst();
		Assert.isTrue(myConsignmentProcess.isPresent(),
				String.format(Localization.getLocalizedString("warehousingfacade.consignments.validation.null.process"),
						consignment.getCode()));

		final List<DeclineEntry> declineEntries = new ArrayList<>();
		for (final DeclineEntryData declineEntryData : consignmentReallocationData.getDeclineEntries())
		{
			boolean isConsignmentEntryPresent = false;
			for (final ConsignmentEntryModel consignmentEntryModel : consignment.getConsignmentEntries())
			{
				if (null != consignmentEntryModel.getOrderEntry().getProduct().getCode() && consignmentEntryModel.getOrderEntry()
						.getProduct().getCode().equalsIgnoreCase(declineEntryData.getProductCode()))
				{
					isConsignmentEntryPresent = true;
					final DeclineEntry declineEntry = populateDeclineEntry(declineEntryData, consignmentReallocationData,
							consignmentEntryModel);
					declineEntries.add(declineEntry);
				}
			}
			Assert.isTrue(isConsignmentEntryPresent, String.format(Localization
							.getLocalizedString("warehousingfacade.consignments.reallocation.validation.no.consignmententry.for.declineentry"),
					declineEntryData.getProductCode()));
		}

		buildDeclineParam(myConsignmentProcess.get(), declineEntries);
		getConsignmentBusinessProcessService()
				.triggerChoiceEvent(consignment, CONSIGNMENT_ACTION_EVENT_NAME, REALLOCATE_CONSIGNMENT_CHOICE);

	}

	/**
	 * Finds {@link ConsignmentModel} for the given {@link ConsignmentModel#CODE}
	 *
	 * @param code
	 * 		the consignment's code
	 * @return the requested consignment for the given code
	 */
	protected ConsignmentModel getConsignmentModelForCode(final String code)
	{
		final Map<String, Object> params = new HashMap<>();
		params.put(ConsignmentModel.CODE, code);

		List<ConsignmentModel> consignments = getConsignmentGenericDao().find(params);
		validateIfSingleResult(consignments,
				String.format(Localization.getLocalizedString("warehousingfacade.consignments.validation.missing.code"), code),
				String.format(Localization.getLocalizedString("warehousingfacade.consignments.validation.multiple.code"), code));

		return consignments.get(0);
	}

	/**
	 * Creates a {@link de.hybris.platform.commerceservices.search.pagedata.PaginationData} based on the received PageableData
	 *
	 * @param pageableData
	 * 			contains pageable information
	 * @param totalResults
	 * 			the total number of results returned
	 * @return pagination data object
	 */
	protected PaginationData createPaginationData(final PageableData pageableData, final int totalResults)
	{
		PaginationData paginationData = new PaginationData();
		paginationData.setPageSize(pageableData.getPageSize());
		paginationData.setSort(pageableData.getSort());
		paginationData.setTotalNumberOfResults(totalResults);

		// Calculate the number of pages
		paginationData.setNumberOfPages((int) Math.ceil(((double) paginationData.getTotalNumberOfResults())
				/ paginationData.getPageSize()));

		// Work out the current page, fixing any invalid page values
		paginationData.setCurrentPage(Math.max(0, Math.min(paginationData.getNumberOfPages(), pageableData.getCurrentPage())));

		return paginationData;
	}

	/**
	 * Gets a sub list based on the pageable data object from the list containing all warehouses.
	 *
	 * @param pageableData
	 * 			the object which will filter the list with its page related information
	 * @param locations
	 * 			contains all available sourcing locations.
	 * @return the sub list which met the pageable data criteria
	 */
	protected List<WarehouseModel> getSublistOfSourcingLocations(final PageableData pageableData,
			final List<WarehouseModel> locations)
	{
		final int fromIndex = pageableData.getCurrentPage() == 0 ? 0 : pageableData.getCurrentPage() * pageableData.getPageSize();
		int toIndex = pageableData.getCurrentPage() == 0 ?
				pageableData.getPageSize() :
				(pageableData.getCurrentPage() + 1) * pageableData.getPageSize();
		toIndex = toIndex > locations.size() ? locations.size() : toIndex;

		return fromIndex > toIndex ? Collections.<WarehouseModel>emptyList() : locations.subList(fromIndex, toIndex);
	}

	/**
	 * Populates and returns {@link DeclineEntry} from given {@link DeclineEntryData}
	 *
	 * @param declineEntryData
	 * 		the given {@link DeclineEntryData} to prepare {@link DeclineEntry}
	 * @param consignmentReallocationData
	 * 		the {@link ConsignmentReallocationData} to check if {@link ConsignmentReallocationData#globalComment}, {@link ConsignmentReallocationData#globalReallocationWarehouseCode} and {@link ConsignmentReallocationData#getGlobalReason()} are present
	 * @param consignmentEntryModel
	 * 		the {@link ConsignmentEntryModel}
	 * @return the {@link DeclineEntry} populated from the given params
	 */
	protected DeclineEntry populateDeclineEntry(final DeclineEntryData declineEntryData,
			final ConsignmentReallocationData consignmentReallocationData, final ConsignmentEntryModel consignmentEntryModel)
	{
		validateDeclineEntryDataToReallocate(consignmentReallocationData, declineEntryData, consignmentEntryModel);

		final DeclineEntry declineEntry = new DeclineEntry();
		declineEntry.setQuantity(declineEntryData.getQuantity());
		declineEntry.setConsignmentEntry(consignmentEntryModel);

		final String warehouseCode = declineEntryData.getReallocationWarehouseCode() != null ?
				declineEntryData.getReallocationWarehouseCode() :
				consignmentReallocationData.getGlobalReallocationWarehouseCode();
		if (!StringUtils.isEmpty(warehouseCode))
		{
			final WarehouseModel reallocationWarehouse = getWarehouseService().getWarehouseForCode(warehouseCode);
			final Long availabilityAtWarehouse = getWarehouseStockService()
					.getStockLevelForProductCodeAndWarehouse(declineEntryData.getProductCode(), reallocationWarehouse);
			Assert.isTrue(availabilityAtWarehouse != 0, String.format(Localization.getLocalizedString(
					"warehousingfacade.consignments.reallocation.validation.declineentry.warehouse.no.availability"),
					declineEntryData.getProductCode(), reallocationWarehouse.getCode()));
			declineEntry.setReallocationWarehouse(reallocationWarehouse);
		}

		final String comment = declineEntryData.getComment() != null ?
				declineEntryData.getComment() :
				consignmentReallocationData.getGlobalComment();
		declineEntry.setNotes(comment);

		final DeclineReason declineReason =
				declineEntryData.getReason() != null ? declineEntryData.getReason() : consignmentReallocationData.getGlobalReason();
		declineEntry.setReason(declineReason);

		return declineEntry;
	}

	/**
	 * Validation for {@link DeclineEntryData} to be reallocated against given {@link ConsignmentEntryModel}.
	 *
	 * @param consignmentReallocationData
	 * 		the {@link ConsignmentReallocationData} to be validated
	 * @param declineEntryData
	 * 		the {@link DeclineEntryData} to be validated
	 * @param consignmentEntry
	 * 		the {@link ConsignmentEntryModel}, against which given declineEntryData needs to be validated
	 */
	protected void validateDeclineEntryDataToReallocate(final ConsignmentReallocationData consignmentReallocationData,
			final DeclineEntryData declineEntryData, final ConsignmentEntryModel consignmentEntry)
	{
		validateParameterNotNull(consignmentReallocationData,
				Localization.getLocalizedString("warehousingfacade.consignments.reallocation.validation.null.consignmentReallocationData"));
		validateParameterNotNull(declineEntryData,
				Localization.getLocalizedString("warehousingfacade.consignments.reallocation.validation.null.declineEntryData"));
		validateParameterNotNull(consignmentEntry,
				Localization.getLocalizedString("warehousingfacade.consignments.reallocation.validation.null.consignmentEntry"));
		validateParameterNotNull(declineEntryData.getQuantity(),
				Localization.getLocalizedString("warehousingfacade.consignments.reallocation.validation.null.declineentry.quantity"));

		Assert.isTrue(declineEntryData.getQuantity() <= consignmentEntry.getQuantityPending(), String.format(Localization
						.getLocalizedString(
								"warehousingfacade.consignments.reallocation.validation.declineentry.quantity.greater.than.pending"),
				consignmentEntry.getQuantityPending(), declineEntryData.getQuantity()));
		Assert.isTrue(declineEntryData.getQuantity() > 0, Localization
				.getLocalizedString("warehousingfacade.consignments.reallocation.validation.declineentry.quantity.less.than.zero"));

		final DeclineReason declineReason =
				declineEntryData.getReason() != null ? declineEntryData.getReason() : consignmentReallocationData.getGlobalReason();
		validateParameterNotNull(declineReason,
				Localization.getLocalizedString("warehousingfacade.consignments.reallocation.null.declinereason"));

		Assert.isTrue(!(consignmentEntry.getConsignment().getDeliveryMode() instanceof PickUpDeliveryModeModel),
				Localization.getLocalizedString("warehousingfacade.consignments.reallocation.validation.deliverymode.not.pickup"));
	}

	/**
	 * Build and save the context parameter for decline entries and set it into the given process
	 *
	 * @param processModel
	 *           the process model for which the context parameters has to be register
	 * @param entriesToReallocate
	 *           the entries to be reallocated
	 */
	protected void buildDeclineParam(final ConsignmentProcessModel processModel,
			final List<DeclineEntry> entriesToReallocate)
	{
		cleanDeclineParam(processModel);

		final DeclineEntries declinedEntries = new DeclineEntries();
		declinedEntries.setEntries(entriesToReallocate);

		final BusinessProcessParameterModel declineParam = new BusinessProcessParameterModel();
		declineParam.setName(DECLINE_ENTRIES);
		declineParam.setValue(declinedEntries);

		declineParam.setProcess(processModel);
		processModel.setContextParameters(Collections.singleton(declineParam));
		getModelService().save(processModel);
	}

	/**
	 * Removes the old decline entries from {@link ConsignmentProcessModel#CONTEXTPARAMETERS}(if any exists), before attempting to decline
	 *
	 * @param processModel
	 * 		the {@link ConsignmentProcessModel} for the consignment to be declined
	 */
	protected void cleanDeclineParam(final ConsignmentProcessModel processModel)
	{
		final Collection<BusinessProcessParameterModel> contextParams = new ArrayList<>();
		processModel.getContextParameters().forEach(param -> contextParams.add(param));
		if(CollectionUtils.isNotEmpty(contextParams))
		{
			final Optional<BusinessProcessParameterModel> declineEntriesParamOptional = contextParams.stream()
					.filter(param -> param.getName().equals(DECLINE_ENTRIES)).findFirst();
			if(declineEntriesParamOptional.isPresent())
			{
				final BusinessProcessParameterModel declineEntriesParam = declineEntriesParamOptional.get();
				contextParams.remove(declineEntriesParam);
				getModelService().remove(declineEntriesParam);
			}
		}
	}
	
	@Required
	public void setEnumerationService(final EnumerationService enumerationService)
	{
		this.enumerationService = enumerationService;
	}

	protected EnumerationService getEnumerationService()
	{
		return enumerationService;
	}

	protected GenericDao<ConsignmentModel> getConsignmentGenericDao()
	{
		return consignmentGenericDao;
	}

	@Required
	public void setConsignmentGenericDao(final GenericDao<ConsignmentModel> consignmentGenericDao)
	{
		this.consignmentGenericDao = consignmentGenericDao;
	}

	@Required
	public void setConsignmentPagedGenericDao(final PagedGenericDao<ConsignmentModel> consignmentPagedGenericDao)
	{
		this.consignmentPagedGenericDao = consignmentPagedGenericDao;
	}

	protected PagedGenericDao<ConsignmentModel> getConsignmentPagedGenericDao()
	{
		return consignmentPagedGenericDao;
	}

	@Required
	public void setConsignmentConverter(final Converter<ConsignmentModel, ConsignmentData> consignmentConverter)
	{
		this.consignmentConverter = consignmentConverter;
	}

	protected Converter<ConsignmentModel, ConsignmentData> getConsignmentConverter()
	{
		return consignmentConverter;
	}

	@Required
	public void setWarehouseConverter(final Converter<WarehouseModel, WarehouseData> warehouseConverter)
	{
		this.warehouseConverter = warehouseConverter;
	}

	protected Converter<WarehouseModel, WarehouseData> getWarehouseConverter()
	{
		return warehouseConverter;
	}

	@Required
	public void setPackagingInfoConverter(final Converter<PackagingInfoModel, PackagingInfoData> packagingInfoConverter)
	{
		this.packagingInfoConverter = packagingInfoConverter;
	}

	protected Converter<PackagingInfoModel, PackagingInfoData> getPackagingInfoConverter()
	{
		return packagingInfoConverter;
	}

	@Required
	public void setReversePackagingInfoConverter(
			final Converter<PackagingInfoData, PackagingInfoModel> reversePackagingInfoConverter)
	{
		this.reversePackagingInfoConverter = reversePackagingInfoConverter;
	}

	protected Converter<PackagingInfoData, PackagingInfoModel> getReversePackagingInfoConverter()
	{
		return reversePackagingInfoConverter;
	}

	@Required
	public void setConsignmentSearchByStatusPagedDao(
			final SearchByStatusPagedGenericDao<ConsignmentModel> consignmentSearchByStatusPagedDao)
	{
		this.consignmentSearchByStatusPagedDao = consignmentSearchByStatusPagedDao;
	}

	protected SearchByStatusPagedGenericDao<ConsignmentModel> getConsignmentSearchByStatusPagedDao()
	{
		return consignmentSearchByStatusPagedDao;
	}

	@Required
	public void setConsignmentEntryPagedDao(final PagedGenericDao consignmentEntryPagedDao)
	{
		this.consignmentEntryPagedDao = consignmentEntryPagedDao;
	}

	protected PagedGenericDao getConsignmentEntryPagedDao()
	{
		return consignmentEntryPagedDao;
	}

	@Required
	public void setConsignmentEntryConverter(
			final Converter<ConsignmentEntryModel, ConsignmentEntryData> consignmentEntryConverter)
	{
		this.consignmentEntryConverter = consignmentEntryConverter;
	}

	protected Converter<ConsignmentEntryModel, ConsignmentEntryData> getConsignmentEntryConverter()
	{
		return consignmentEntryConverter;
	}

	@Required
	public void setWarehousingShippingService( final WarehousingShippingService warehousingShippingService)
	{
		this.warehousingShippingService = warehousingShippingService;
	}

	protected WarehousingShippingService getWarehousingShippingService()
	{
		return warehousingShippingService;
	}

	@Required
	public void setSourcingFilterProcessor(SourcingFilterProcessor sourcingFilterProcessor)
	{
		this.sourcingFilterProcessor = sourcingFilterProcessor;
	}

	protected SourcingFilterProcessor getSourcingFilterProcessor()
	{
		return sourcingFilterProcessor;
	}

	public WarehouseService getWarehouseService() {
		return warehouseService;
	}
	
	@Required
	public void setWarehouseService(WarehouseService warehouseService) {
		this.warehouseService = warehouseService;
	}

	public WarehousingBusinessProcessService<ConsignmentModel> getConsignmentBusinessProcessService() {
		return consignmentBusinessProcessService;
	}
	
	@Required
	public void setConsignmentBusinessProcessService(
			WarehousingBusinessProcessService<ConsignmentModel> consignmentBusinessProcessService) {
		this.consignmentBusinessProcessService = consignmentBusinessProcessService;
	}

	protected WarehouseStockService getWarehouseStockService()
	{
		return warehouseStockService;
	}

	@Required
	public void setWarehouseStockService(final WarehouseStockService warehouseStockService)
	{
		this.warehouseStockService = warehouseStockService;
	}

	protected List<ConsignmentStatus> getReallocableConsignmentStatusList()
	{
		return reallocableConsignmentStatusList;
	}

	@Required
	public void setReallocableConsignmentStatusList(final List<ConsignmentStatus> reallocableConsignmentStatusList)
	{
		this.reallocableConsignmentStatusList = reallocableConsignmentStatusList;
	}
}
