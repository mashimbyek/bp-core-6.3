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
package de.hybris.platform.ordermanagementfacade.returns.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import de.hybris.platform.basecommerce.enums.CancelReason;
import de.hybris.platform.basecommerce.enums.ReturnStatus;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.event.CreateReturnEvent;
import de.hybris.platform.commerceservices.impersonation.ImpersonationContext;
import de.hybris.platform.commerceservices.impersonation.ImpersonationService;
import de.hybris.platform.commerceservices.search.dao.PagedGenericDao;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.order.OrderService;
import de.hybris.platform.ordermanagementfacade.OmsBaseFacade;
import de.hybris.platform.ordermanagementfacade.returns.OmsReturnFacade;
import de.hybris.platform.ordermanagementfacade.returns.data.CancelReturnRequestData;
import de.hybris.platform.ordermanagementfacade.returns.data.ReturnEntryData;
import de.hybris.platform.ordermanagementfacade.returns.data.ReturnRequestData;
import de.hybris.platform.refund.RefundService;
import de.hybris.platform.returns.OrderReturnException;
import de.hybris.platform.returns.OrderReturnRecordsHandlerException;
import de.hybris.platform.returns.ReturnActionResponse;
import de.hybris.platform.returns.ReturnCallbackService;
import de.hybris.platform.returns.ReturnService;
import de.hybris.platform.returns.model.RefundEntryModel;
import de.hybris.platform.returns.model.ReturnEntryModel;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.util.localization.Localization;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateIfSingleResult;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;


/**
 * Order management default Return Facade implementation for the {@link OmsReturnFacade}
 */
public class DefaultOmsReturnFacade extends OmsBaseFacade implements OmsReturnFacade
{
	protected static final Logger LOGGER = LoggerFactory.getLogger(DefaultOmsReturnFacade.class);

	private Converter<ReturnRequestModel, ReturnRequestData> returnConverter;
	private Converter<ReturnRequestModel, ReturnRequestData> returnHistoryConverter;
	private Converter<ReturnEntryModel, ReturnEntryData> returnEntryConverter;
	private PagedGenericDao<ReturnRequestModel> returnPagedGenericDao;
	private PagedGenericDao<ReturnEntryModel> returnEntryPagedGenericDao;
	private GenericDao<ReturnRequestModel> returnGenericDao;
	private GenericDao<OrderModel> orderGenericDao;
	private EnumerationService enumerationService;
	private ImpersonationService impersonationService;
	private ReturnCallbackService returnCallbackService;
	private ReturnService returnService;
	private RefundService refundService;
	private OrderService orderService;
	private EventService eventService;
	private UserService userService;
	private BaseStoreService baseStoreService;
	private CustomerAccountService customerAccountService;


	@Override
	public SearchPageData<ReturnRequestData> getReturns(PageableData pageableData)
	{
		SearchPageData<ReturnRequestModel> returnSearchPageData = getReturnPagedGenericDao().find(pageableData);
		return convertSearchPageData(returnSearchPageData, getReturnConverter());
	}

	@Override
	public SearchPageData<ReturnRequestData> getPagedReturnRequestsByCurrentUser(final PageableData pageableData,
			final ReturnStatus... returnStatuses)
	{
		validateParameterNotNull(pageableData, "PageableData cannot be null");
		final CustomerModel customer = (CustomerModel) getUserService().getCurrentUser();
		final BaseStoreModel currentBaseStore = getBaseStoreService().getCurrentBaseStore();
		final SearchPageData<ReturnRequestModel> returnRequests = getCustomerAccountService()
				.getReturnRequestsByCustomerAndStore(customer, currentBaseStore, returnStatuses, pageableData);

		return convertSearchPageData(returnRequests, getReturnHistoryConverter());
	}

	@Override
	public SearchPageData<ReturnRequestData> getReturnsByStatuses(PageableData pageableData, Set<ReturnStatus> returnStatusSet)
	{
		final Map<String, Object> params = new HashMap<>();
		params.put(ReturnRequestModel.STATUS, returnStatusSet);
		return convertSearchPageData(getReturnPagedGenericDao().find(params, pageableData), getReturnConverter());
	}

	@Override
	public ReturnRequestData getReturnForReturnCode(String code)
	{
		return getReturnConverter().convert(getReturnRequestModelForCode(code));
	}

	@Override
	public List<CancelReason> getCancelReasons()
	{
		return getEnumerationService().getEnumerationValues(CancelReason._TYPECODE);
	}

	@Override
	public List<ReturnStatus> getReturnStatuses()
	{
		return getEnumerationService().getEnumerationValues(ReturnStatus._TYPECODE);
	}

	@Override
	public SearchPageData<ReturnEntryData> getReturnEntriesForReturnCode(String code, PageableData pageableData)
	{
		final ReturnRequestModel returnReq = getReturnRequestModelForCode(code);

		final Map<String, ReturnRequestModel> returnEntryParams = new HashMap<>();
		returnEntryParams.put(ReturnEntryModel.RETURNREQUEST, returnReq);
		return convertSearchPageData(getReturnEntryPagedGenericDao().find(returnEntryParams, pageableData),
				getReturnEntryConverter());
	}

	@Override
	public void approveReturnRequest(final String code)
	{
		final ReturnRequestModel returnRequestModel = getReturnRequestModelForCode(code);
		try
		{
			if (returnRequestModel.getStatus().equals(ReturnStatus.APPROVAL_PENDING))
			{
				getReturnCallbackService().onReturnApprovalResponse(new ReturnActionResponse(returnRequestModel));
			}
			else
			{
				throw new IllegalStateException(
						String.format(Localization.getLocalizedString("ordermanagementfacade.approvereturnrequest.error.wrongstatus"),
								ReturnStatus.APPROVAL_PENDING));
			}

		}
		catch (OrderReturnException e) //NOSONAR
		{
			LOGGER.error(String.format("Error happened during approval for the return request [%s]",
					returnRequestModel.getRMA())); //NOSONAR
		}
	}

	@Override
	public void cancelReturnRequest(final CancelReturnRequestData cancelReturnRequestData)
	{
		// TODO: this must be refactored to account for the reason and notes in the CancelReturnRequestData after OMSE-1565 is done
		Assert.notNull(cancelReturnRequestData,
				Localization.getLocalizedString("ordermanagementfacade.returns.validation.multiple.cancelreturnrequestdata"));
		final ReturnRequestModel returnRequestModel = getReturnRequestModelForCode(cancelReturnRequestData.getCode());
		Assert.isTrue(isReturnCancellable(returnRequestModel.getStatus()),
				String.format(Localization.getLocalizedString("ordermanagementfacade.cancelreturnrequest.error.wrongstatus"),
						returnRequestModel.getStatus()));
		try
		{
			getReturnCallbackService().onReturnCancelResponse(new ReturnActionResponse(returnRequestModel));
		}
		catch (final OrderReturnException e)  //NOSONAR
		{
			LOGGER.error(
					String.format("Error happened during cancelling of return request [%s]", returnRequestModel.getRMA()));  //NOSONAR
		}
	}

	@Override
	public void requestManualPaymentReversalForReturnRequest(final String code)
	{
		Assert.notNull(code, Localization.getLocalizedString("ordermanagementfacade.returns.paymentreverse.validation.null.code"));
		final ReturnRequestModel returnRequestModel = getReturnRequestModelForCode(code);
		try
		{
			if (ReturnStatus.PAYMENT_REVERSAL_FAILED.equals(returnRequestModel.getStatus()))
			{
				getReturnService().requestManualPaymentReversalForReturnRequest(returnRequestModel);
			}
			else
			{
				throw new IllegalStateException(
						String.format(Localization.getLocalizedString("ordermanagementfacade.paymentreverse.error.wrongstatus"),
								ReturnStatus.PAYMENT_REVERSAL_FAILED));
			}
		}
		catch (final OrderReturnException e) //NOSONAR
		{
			LOGGER.error(String.format("Error happened during manual payment reversal for the return request [%s]",
					returnRequestModel.getRMA())); //NOSONAR
		}
	}

	@Override
	public void requestManualTaxReversalForReturnRequest(final String code)
	{
		validateParameterNotNull(code,
				Localization.getLocalizedString("ordermanagementfacade.returns.taxreverse.validation.null.code"));
		final ReturnRequestModel returnRequestModel = getReturnRequestModelForCode(code);
		try
		{
			if (ReturnStatus.TAX_REVERSAL_FAILED.equals(returnRequestModel.getStatus()))
			{
				getReturnService().requestManualTaxReversalForReturnRequest(returnRequestModel);
			}
			else
			{
				throw new IllegalStateException(
						String.format(Localization.getLocalizedString("ordermanagementfacade.taxreverse.error.wrongstatus"),
								ReturnStatus.TAX_REVERSAL_FAILED));
			}
		}
		catch (final OrderReturnException e) //NOSONAR
		{
			LOGGER.error(String.format("Error happened during manual tax reversal for the return request [%s]",
					returnRequestModel.getRMA())); //NOSONAR
		}
	}

	@Override
	public ReturnRequestData createReturnRequest(final ReturnRequestData returnRequestData)
	{
		validateReturnRequestData(returnRequestData);
		final String orderCode = returnRequestData.getOrder().getCode();
		final Map<String, String> orderParams = new HashMap<>();
		orderParams.put(OrderModel.CODE, orderCode);
		final List<OrderModel> orders = discardOrderSnapshot(getOrderGenericDao().find(orderParams));

		validateIfSingleResult(orders,
				String.format(Localization.getLocalizedString("ordermanagementfacade.orders.validation.missing.code"), orderCode),
				String.format(Localization.getLocalizedString("ordermanagementfacade.orders.validation.multiple.code"), orderCode));
		final OrderModel order = orders.get(0);

		final ImpersonationContext context = new ImpersonationContext();
		context.setSite(order.getSite());
		final ReturnRequestModel returnRequestModel = getImpersonationService()
				.executeInContext(context, () -> createReturnRequestInContext(order, returnRequestData));

		return getReturnConverter().convert(returnRequestModel);
	}

	/**
	 * Creates {@link ReturnRequestModel} in the {@link ImpersonationContext}
	 *
	 * @param order
	 * 		the {@link OrderModel} for which returnRequest needs to be created
	 * @param returnRequestData
	 * 		the {@link ReturnRequestData} containing required data to create {@link ReturnRequestModel}
	 * @return the newly created {@link ReturnRequestModel}
	 */
	protected ReturnRequestModel createReturnRequestInContext(final OrderModel order, final ReturnRequestData returnRequestData)
	{
		final ReturnRequestModel returnRequest = getReturnService().createReturnRequest(order);
		returnRequest.setRefundDeliveryCost(canRefundDeliveryCost(order.getCode(), returnRequestData.getRefundDeliveryCost()));
		getModelService().save(returnRequest);

		returnRequestData.getEntries().forEach(returnEntryData -> {
			final AbstractOrderEntryModel orderEntry = getOrderService()
					.getEntryForNumber(order, returnEntryData.getOrderEntry().getEntryNumber());
			Assert.notNull(orderEntry, Localization.getLocalizedString("ordermanagementfacade.returns.validation.null.orderentry"));

			final RefundEntryModel refundEntryToBeCreated = getReturnService()
					.createRefund(returnRequest, orderEntry, returnEntryData.getNotes(), returnEntryData.getExpectedQuantity(),
							returnEntryData.getAction(), returnEntryData.getRefundReason());
			refundEntryToBeCreated.setAmount(returnEntryData.getRefundAmount() != null ?
					returnEntryData.getRefundAmount().getValue() :
					calculateRefundEntryAmount(orderEntry.getBasePrice(), returnEntryData.getExpectedQuantity()));
			getModelService().save(refundEntryToBeCreated);
		});

		// Recalculate the subTotal after updating a refund amount
		returnRequest.setSubtotal(
				returnRequest.getReturnEntries().stream().filter(returnEntry -> returnEntry instanceof RefundEntryModel)
						.map(refundEntry -> ((RefundEntryModel) refundEntry).getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add));
		getModelService().save(returnRequest);

		try
		{
			getRefundService().apply(returnRequest.getOrder(), returnRequest);
		}
		catch (final OrderReturnRecordsHandlerException e) //NOSONAR
		{
			LOGGER.info("Return record already in progress for Order: " + order.getCode()); //NOSONAR
		}
		catch (final IllegalStateException ise) //NOSONAR
		{
			LOGGER.info("Order " + order.getCode() + " Return record already in progress"); //NOSONAR
		}
		final CreateReturnEvent createReturnEvent = new CreateReturnEvent();
		createReturnEvent.setReturnRequest(returnRequest);
		getEventService().publishEvent(createReturnEvent);

		return returnRequest;
	}

	/**
	 * Checks whether the return can be cancelled
	 *
	 * @param status
	 * 		current status of the return request
	 * @return whether the return request is cancellable
	 */
	protected boolean isReturnCancellable(final ReturnStatus status)
	{
		final List<ReturnStatus> cancellableStatuses = Arrays
				.asList(ReturnStatus.APPROVAL_PENDING, ReturnStatus.WAIT, ReturnStatus.PAYMENT_REVERSAL_FAILED);
		return cancellableStatuses.contains(status);
	}

	/**
	 * Validates for null check and mandatory fields in returnRequestData
	 *
	 * @param returnRequestData
	 * 		returnRequest to be validated
	 */
	protected void validateReturnRequestData(final ReturnRequestData returnRequestData)
	{
		Assert.notNull(returnRequestData,
				Localization.getLocalizedString("ordermanagementfacade.returns.validation.null.returnrequestdata"));
		Assert.notNull(returnRequestData.getOrder(),
				Localization.getLocalizedString("ordermanagementfacade.returns.validation.null.order"));
		Assert.isTrue(Objects.nonNull(returnRequestData.getEntries()) && CollectionUtils.isNotEmpty(returnRequestData.getEntries()),
				Localization.getLocalizedString("ordermanagementfacade.returns.validation.null.returnentries"));

		final Boolean refundDeliveryCostRequested = returnRequestData.getRefundDeliveryCost();
		if (refundDeliveryCostRequested != null && refundDeliveryCostRequested)
		{
			Assert.isTrue(canRefundDeliveryCost(returnRequestData.getOrder().getCode(), returnRequestData.getRefundDeliveryCost()),
					String.format(Localization.getLocalizedString("ordermanagementfacade.returns.error.deliverycost"),
							returnRequestData.getOrder().getCode()));
		}

		returnRequestData.getEntries().forEach(entry -> validateReturnEntryData(entry));
	}

	/**
	 * Validates for null check and mandatory fields in returnEntryData
	 *
	 * @param returnEntryData
	 * 		returnEntry to be validated
	 */
	protected void validateReturnEntryData(final ReturnEntryData returnEntryData)
	{
		Assert.notNull(returnEntryData.getExpectedQuantity(),
				Localization.getLocalizedString("ordermanagementfacade.returns.validation.null.expectedquantity"));
		Assert.notNull(returnEntryData.getAction(),
				Localization.getLocalizedString("ordermanagementfacade.returns.validation.null.action"));
		Assert.notNull(returnEntryData.getRefundReason(),
				Localization.getLocalizedString("ordermanagementfacade.returns.validation.null.refundreason"));
		validateOrderEntryForReturnEntry(returnEntryData.getOrderEntry());
	}

	/**
	 * Validates for null check and mandatory fields in returnEntryData
	 *
	 * @param orderEntry
	 * 		orderEntry to be validated
	 */
	protected void validateOrderEntryForReturnEntry(final OrderEntryData orderEntry)
	{
		Assert.notNull(orderEntry, Localization.getLocalizedString("ordermanagementfacade.returns.validation.null.orderentry"));
		Assert.notNull(orderEntry.getEntryNumber(),
				Localization.getLocalizedString("ordermanagementfacade.returns.validation.null.orderentrynumber"));
	}

	/**
	 * Evaluates refundAmount for the {@link RefundEntryModel} to be created
	 *
	 * @param basePrice
	 * 		the basePrice for the product to be refunded
	 * @param expectedQuantity
	 * 		expectedQuantity for the product to be refunded
	 * @return the amount in BigDecimal to be refunded for the {@link RefundEntryModel}
	 */
	protected BigDecimal calculateRefundEntryAmount(final Double basePrice, final Long expectedQuantity)
	{
		return BigDecimal.valueOf(basePrice * expectedQuantity);
	}

	/**
	 * Evaluates if deliveryCost should be refunded for the requested {@link ReturnRequestModel} to be created
	 *
	 * @param orderCode
	 * 		the orderCode's code for the requested returnRequest to be created
	 * @param isDeliveryCostRequested
	 * 		is deliveryCost requested in the request
	 * @return the boolean to indicate if deliveryCost should be refunded
	 */
	protected Boolean canRefundDeliveryCost(final String orderCode, final Boolean isDeliveryCostRequested)
	{
		if (isDeliveryCostRequested != null && isDeliveryCostRequested)
		{
			final List<ReturnRequestModel> returnRequestsForOrder = getReturnService().getReturnRequests(orderCode);
			if (CollectionUtils.isNotEmpty(returnRequestsForOrder))
			{
				return returnRequestsForOrder.stream().noneMatch(returnReq -> returnReq.getRefundDeliveryCost().booleanValue()
						&& returnReq.getStatus() != ReturnStatus.CANCELED);
			}
		}
		return Boolean.FALSE;
	}

	/**
	 * Finds {@link ReturnRequestModel} for the given {@value de.hybris.platform.returns.model.ReturnRequestModel#CODE}
	 *
	 * @param code
	 * 		the returnRequest's code
	 * @return the requested return for the given code
	 */
	protected ReturnRequestModel getReturnRequestModelForCode(final String code)
	{

		final Map<String, String> params = new HashMap<>();
		params.put(ReturnRequestModel.CODE, code);

		final List<ReturnRequestModel> resultSet = getReturnGenericDao().find(params);
		validateIfSingleResult(resultSet,
				String.format(Localization.getLocalizedString("ordermanagementfacade.returns.validation.missing.code"), code),
				String.format(Localization.getLocalizedString("ordermanagementfacade.returns.validation.missing.code"), code));

		return resultSet.get(0);
	}

	@Required
	public void setReturnPagedGenericDao(PagedGenericDao<ReturnRequestModel> returnPagedGenericDao)
	{
		this.returnPagedGenericDao = returnPagedGenericDao;
	}

	protected PagedGenericDao<ReturnRequestModel> getReturnPagedGenericDao()
	{
		return returnPagedGenericDao;
	}

	@Required
	public void setReturnConverter(Converter<ReturnRequestModel, ReturnRequestData> returnConverter)
	{
		this.returnConverter = returnConverter;
	}

	protected Converter<ReturnRequestModel, ReturnRequestData> getReturnConverter()
	{
		return returnConverter;
	}

	@Required
	public void setReturnHistoryConverter(final Converter<ReturnRequestModel, ReturnRequestData> returnHistoryConverter)
	{
		this.returnHistoryConverter = returnHistoryConverter;
	}

	protected Converter<ReturnRequestModel, ReturnRequestData> getReturnHistoryConverter()
	{
		return returnHistoryConverter;
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

	protected GenericDao<ReturnRequestModel> getReturnGenericDao()
	{
		return returnGenericDao;
	}

	@Required
	public void setReturnGenericDao(GenericDao<ReturnRequestModel> returnGenericDao)
	{
		this.returnGenericDao = returnGenericDao;
	}

	protected PagedGenericDao<ReturnEntryModel> getReturnEntryPagedGenericDao()
	{
		return returnEntryPagedGenericDao;
	}

	@Required
	public void setReturnEntryPagedGenericDao(PagedGenericDao<ReturnEntryModel> returnEntryPagedGenericDao)
	{
		this.returnEntryPagedGenericDao = returnEntryPagedGenericDao;
	}

	protected Converter<ReturnEntryModel, ReturnEntryData> getReturnEntryConverter()
	{
		return returnEntryConverter;
	}

	@Required
	public void setReturnEntryConverter(Converter<ReturnEntryModel, ReturnEntryData> returnEntryConverter)
	{
		this.returnEntryConverter = returnEntryConverter;
	}

	protected ReturnService getReturnService()
	{
		return returnService;
	}

	@Required
	public void setReturnService(final ReturnService returnService)
	{
		this.returnService = returnService;
	}

	protected ReturnCallbackService getReturnCallbackService()
	{
		return returnCallbackService;
	}

	@Required
	public void setReturnCallbackService(final ReturnCallbackService returnCallbackService)
	{
		this.returnCallbackService = returnCallbackService;
	}

	protected GenericDao<OrderModel> getOrderGenericDao()
	{
		return orderGenericDao;
	}

	@Required
	public void setOrderGenericDao(final GenericDao<OrderModel> orderGenericDao)
	{
		this.orderGenericDao = orderGenericDao;
	}

	protected OrderService getOrderService()
	{
		return orderService;
	}

	@Required
	public void setOrderService(final OrderService orderService)
	{
		this.orderService = orderService;
	}

	protected EventService getEventService()
	{
		return eventService;
	}

	@Required
	public void setEventService(final EventService eventService)
	{
		this.eventService = eventService;
	}

	protected RefundService getRefundService()
	{
		return refundService;
	}

	@Required
	public void setRefundService(final RefundService refundService)
	{
		this.refundService = refundService;
	}

	protected ImpersonationService getImpersonationService()
	{
		return impersonationService;
	}

	@Required
	public void setImpersonationService(final ImpersonationService impersonationService)
	{
		this.impersonationService = impersonationService;
	}

	protected UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	protected BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	@Required
	public void setBaseStoreService(BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	protected CustomerAccountService getCustomerAccountService()
	{
		return customerAccountService;
	}

	@Required
	public void setCustomerAccountService(CustomerAccountService customerAccountService)
	{
		this.customerAccountService = customerAccountService;
	}
}
