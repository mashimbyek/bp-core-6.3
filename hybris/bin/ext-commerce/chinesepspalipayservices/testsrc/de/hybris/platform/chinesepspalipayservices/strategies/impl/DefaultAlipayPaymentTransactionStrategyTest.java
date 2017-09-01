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
package de.hybris.platform.chinesepspalipayservices.strategies.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.chinesepspalipayservices.constants.PaymentConstants;
import de.hybris.platform.chinesepspalipayservices.dao.AlipayPaymentTransactionDao;
import de.hybris.platform.chinesepspalipayservices.dao.AlipayPaymentTransactionEntryDao;
import de.hybris.platform.chinesepspalipayservices.data.AlipayRawCancelPaymentResult;
import de.hybris.platform.chinesepspalipayservices.data.AlipayRawDirectPayErrorInfo;
import de.hybris.platform.chinesepspalipayservices.data.AlipayRawDirectPayNotification;
import de.hybris.platform.chinesepspalipayservices.data.AlipayRawPaymentStatus;
import de.hybris.platform.chinesepspalipayservices.data.AlipayRefundData;
import de.hybris.platform.chinesepspalipayservices.data.AlipayRefundRequestData;
import de.hybris.platform.chinesepspalipayservices.enums.AlipayPayMethod;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.AlipayPaymentTransactionEntryModel;
import de.hybris.platform.payment.model.AlipayPaymentTransactionModel;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;



@IntegrationTest
public class DefaultAlipayPaymentTransactionStrategyTest extends ServicelayerTransactionalTest
{
	@Resource(name = "alipayPaymentTransactionStrategy")
	private DefaultAlipayPaymentTransactionStrategy defaultAlipayPaymentTransactionStrategy;

	@Resource(name = "modelService")
	private ModelService modelService;

	@Resource(name = "userService")
	private UserService userService;

	@Mock
	private KeyGenerator paymentTransactionKeyGenerator;

	@Mock
	private AlipayPaymentTransactionDao alipayPaymentTransactionDao;

	@Mock
	private AlipayPaymentTransactionEntryDao alipayPaymentTransactionEntryDao;

	@Before
	public void prepare()
	{
		MockitoAnnotations.initMocks(this);
		given(paymentTransactionKeyGenerator.generate()).willReturn("00000002");
	}

	@Test
	public void testUpdateForRefundRequest()
	{
		OrderModel orderModel = generateOrder();
		addPaymentTransaction(orderModel, PaymentTransactionType.CAPTURE, TransactionStatus.ACCEPTED);
		AlipayPaymentTransactionModel baseTransaction = (AlipayPaymentTransactionModel) orderModel.getPaymentTransactions().get(0);

		AlipayRefundRequestData alipayRefundRequestData = genereateRefundRequestData();
		DefaultAlipayPaymentTransactionStrategy spy = Mockito.spy(defaultAlipayPaymentTransactionStrategy);
		spy.setPaymentTransactionKeyGenerator(paymentTransactionKeyGenerator);
		Mockito.doReturn(Optional.of(baseTransaction)).when(spy).getPaymentTransactionWithCaptureEntry(Mockito.any(),
				Mockito.any());

		spy.updateTransactionForRefundRequest(orderModel, alipayRefundRequestData);

		Optional<AlipayPaymentTransactionEntryModel> alipayPaymentTransactionEntryModel = defaultAlipayPaymentTransactionStrategy
				.getPaymentTransactionEntry(orderModel, TransactionStatus.REVIEW, PaymentTransactionType.REFUND_STANDALONE);
		assertTrue(alipayPaymentTransactionEntryModel.isPresent());
		AlipayPaymentTransactionEntryModel result = alipayPaymentTransactionEntryModel.get();
		assertEquals("USD", result.getCurrency().getIsocode());
		assertEquals(PaymentTransactionType.REFUND_STANDALONE, result.getType());
		assertEquals(baseTransaction, result.getPaymentTransaction());
		assertEquals("000001", result.getRequestId());
		assertEquals(TransactionStatus.REVIEW.name(), result.getTransactionStatus());
		assertEquals(TransactionStatusDetails.SUCCESFULL.name() + "; Refund request: 201601011234",
				result.getTransactionStatusDetails());
		assertEquals("00000002", result.getCode());
	}

	@Test
	public void testUpdateForRefundNotification()
	{
		OrderModel orderModel = generateOrder();
		addPaymentTransaction(orderModel, PaymentTransactionType.CAPTURE, TransactionStatus.ACCEPTED);

		DefaultAlipayPaymentTransactionStrategy spy = Mockito.spy(defaultAlipayPaymentTransactionStrategy);
		spy.setPaymentTransactionKeyGenerator(paymentTransactionKeyGenerator);

		List<AlipayRefundData> alipayRefundData = new ArrayList<AlipayRefundData>();

		AlipayRefundData refundData = new AlipayRefundData();
		refundData.setAlipayCode("123456");
		refundData.setBatchNo("20060702001");
		refundData.setPayerRefundAmount(80);
		refundData.setPayerRefundStatus("SUCCESS");
		refundData.setSellerEmail("jax_chuanhang@alipay.com");
		refundData.setSellerId("2088101003147483");
		refundData.setSellerRefundAmount(0.01);
		refundData.setSellerRefundStatus("SUCCESS");

		alipayRefundData.add(refundData);

		AlipayPaymentTransactionModel transaction = new AlipayPaymentTransactionModel();
		transaction.setAlipayCode("123456");
		transaction.setRequestId("000001");
		transaction.setCode(String.valueOf(paymentTransactionKeyGenerator.generate()));
		transaction.setOrder(orderModel);

		modelService.save(transaction);

		AlipayPaymentTransactionEntryModel entry = new AlipayPaymentTransactionEntryModel();
		entry.setPaymentTransaction(transaction);
		entry.setType(PaymentTransactionType.REFUND_STANDALONE);
		entry.setTransactionStatus(TransactionStatus.REVIEW.name());
		entry.setCode(String.valueOf(paymentTransactionKeyGenerator.generate()) + "11");

		modelService.save(entry);
		modelService.refresh(transaction);

		Map<OrderModel, Boolean> refundStatus = spy.updateForRefundNotification(alipayRefundData);

		Optional<AlipayPaymentTransactionEntryModel> refundEntryOptional = spy.getPaymentTransactionEntry(orderModel,
				TransactionStatus.ACCEPTED, PaymentTransactionType.REFUND_STANDALONE);
		assertTrue(refundEntryOptional.isPresent());
		AlipayPaymentTransactionEntryModel refundEntry = refundEntryOptional.get();

		assertEquals(PaymentTransactionType.REFUND_STANDALONE, refundEntry.getType());
		assertEquals(transaction, refundEntry.getPaymentTransaction());
		assertEquals(TransactionStatus.ACCEPTED.name(), refundEntry.getTransactionStatus());
		assertEquals("SUCCESS" + "; Refund Batch No: " + "20060702001", refundEntry.getTransactionStatusDetails());
		assertEquals(String.valueOf(paymentTransactionKeyGenerator.generate()) + "11", refundEntry.getCode());

		assertEquals(refundStatus.size(), 1);
		assertTrue(refundStatus.get(orderModel));
	}

	@Test
	public void testGetPaymentTransactionWithCaptureEntrySuccessfully()
	{
		OrderModel orderModel = generateOrder();
		addPaymentTransaction(orderModel, PaymentTransactionType.CAPTURE, TransactionStatus.ACCEPTED);
		AlipayPaymentTransactionModel baseTransaction = (AlipayPaymentTransactionModel) orderModel.getPaymentTransactions().get(0);

		AlipayPaymentTransactionEntryModel entry = new AlipayPaymentTransactionEntryModel();
		entry.setPaymentTransaction(baseTransaction);
		entry.setType(PaymentTransactionType.CAPTURE);
		entry.setTransactionStatus(TransactionStatus.ACCEPTED.name());
		entry.setCode(String.valueOf(paymentTransactionKeyGenerator.generate()) + "22");
		modelService.save(entry);
		modelService.refresh(baseTransaction);

		Optional<AlipayPaymentTransactionModel> result = defaultAlipayPaymentTransactionStrategy
				.getPaymentTransactionWithCaptureEntry(orderModel, TransactionStatus.ACCEPTED);

		assertTrue(result.isPresent());
		AlipayPaymentTransactionModel resultTransaction = result.get();
		assertEquals(baseTransaction, resultTransaction);
		assertEquals("10000", resultTransaction.getAlipayCode());
		assertEquals("000001", resultTransaction.getRequestId());
	}

	@Test
	public void testGetPaymentTransactionWithCaptureEntryFailed()
	{
		OrderModel orderModel = generateOrder();
		addPaymentTransaction(orderModel, PaymentTransactionType.CAPTURE, TransactionStatus.ACCEPTED);
		AlipayPaymentTransactionModel baseTransaction = (AlipayPaymentTransactionModel) orderModel.getPaymentTransactions().get(0);
		AlipayPaymentTransactionEntryModel entry = new AlipayPaymentTransactionEntryModel();
		entry.setPaymentTransaction(baseTransaction);
		entry.setType(PaymentTransactionType.PARTIAL_CAPTURE);
		entry.setTransactionStatus(TransactionStatus.ACCEPTED.name());
		entry.setCode(String.valueOf(paymentTransactionKeyGenerator.generate()) + "33");
		modelService.save(entry);
		modelService.refresh(baseTransaction);

		Optional<AlipayPaymentTransactionModel> result = defaultAlipayPaymentTransactionStrategy
				.getPaymentTransactionWithCaptureEntry(orderModel, TransactionStatus.ACCEPTED);

		assertFalse(result.isPresent());
	}

	@Test
	public void testCreateTransacionForNewRequest()
	{
		OrderModel emptyOrder = generateOrder();
		modelService.save(emptyOrder);

		String requestUrl = "https://mapi.alipay.com/gateway.do?";

		defaultAlipayPaymentTransactionStrategy.createTransacionForNewRequest(emptyOrder, requestUrl);

		List<PaymentTransactionModel> transactionModels = emptyOrder.getPaymentTransactions();
		assertEquals(1, transactionModels.size());
		AlipayPaymentTransactionModel transaction = (AlipayPaymentTransactionModel) transactionModels.get(0);
		assertEquals("00000001", transaction.getCode());
		assertEquals("https://mapi.alipay.com/gateway.do?", transaction.getPaymentUrl());
		assertEquals(AlipayPayMethod.DIRECTPAY, transaction.getPayMethod());
		assertEquals("00000001", transaction.getRequestId());
		assertEquals(PaymentConstants.Basic.PAYMENT_PROVIDER, transaction.getPaymentProvider());
	}

	@Test
	public void testCreateTransactionEntryForNewRequest()
	{
		OrderModel emptyOrder = generateOrder();
		modelService.save(emptyOrder);

		AlipayPaymentTransactionModel transaction = new AlipayPaymentTransactionModel();
		transaction.setCode("00000012");
		transaction.setRequestId("00000012");
		transaction.setPayMethod(AlipayPayMethod.DIRECTPAY);
		List<PaymentTransactionModel> transactionModels = new ArrayList<>();
		transactionModels.add(transaction);
		emptyOrder.setPaymentTransactions(transactionModels);

		defaultAlipayPaymentTransactionStrategy.createTransactionEntryForNewRequest(emptyOrder, transaction);

		List<PaymentTransactionEntryModel> entries = emptyOrder.getPaymentTransactions().get(0).getEntries();
		assertEquals(1, entries.size());
		AlipayPaymentTransactionEntryModel entry = (AlipayPaymentTransactionEntryModel) entries.get(0);
		assertEquals(1.5, entry.getAmount().doubleValue(), 0.001);
		assertEquals("USD", entry.getCurrency().getIsocode());
		assertEquals(PaymentTransactionType.REQUEST, entry.getType());
		assertEquals(transaction, entry.getPaymentTransaction());
		assertEquals(transaction.getRequestId(), entry.getRequestId());
		assertEquals(TransactionStatus.ACCEPTED.name(), entry.getTransactionStatus());
		assertEquals(TransactionStatusDetails.SUCCESFULL.name(), entry.getTransactionStatusDetails());
	}

	@Test
	public void testSaveForStatusCheck()
	{
		AlipayRawPaymentStatus alipayRawPaymentStatus = new AlipayRawPaymentStatus();
		alipayRawPaymentStatus.setTradeNo("87914321574713");
		alipayRawPaymentStatus.setTradeStatus("TRADE_SUCCESS");
		alipayRawPaymentStatus.setTotalFee(1.5);
		alipayRawPaymentStatus.setBuyerEmail("jax_chuanhang@alipay.com");
		alipayRawPaymentStatus.setBuyerId("2264872159712354");
		alipayRawPaymentStatus.setToSellerFee(1.5);

		OrderModel orderModel = generateOrder();
		addPaymentTransaction(orderModel, PaymentTransactionType.REQUEST, TransactionStatus.ACCEPTED);
		AlipayPaymentTransactionModel baseTransaction = (AlipayPaymentTransactionModel) orderModel.getPaymentTransactions().get(0);

		DefaultAlipayPaymentTransactionStrategy spy = Mockito.spy(defaultAlipayPaymentTransactionStrategy);
		spy.setPaymentTransactionKeyGenerator(paymentTransactionKeyGenerator);

		Mockito.doReturn(baseTransaction).when(spy).getPaymentTransactionToUpdate(Matchers.eq(orderModel),
				Matchers.eq(TransactionStatus.ACCEPTED),
				Matchers.anyString());

		spy.saveForStatusCheck(orderModel, alipayRawPaymentStatus);

		assertEquals(1, orderModel.getPaymentTransactions().size());
		assertEquals(baseTransaction, orderModel.getPaymentTransactions().get(0));
		assertEquals("87914321574713", baseTransaction.getAlipayCode());

		Optional<AlipayPaymentTransactionEntryModel> option = defaultAlipayPaymentTransactionStrategy
				.getPaymentTransactionEntry(orderModel, TransactionStatus.ACCEPTED, PaymentTransactionType.CAPTURE);
		assertTrue(option.isPresent());
		AlipayPaymentTransactionEntryModel captureEntry = option.get();
		assertEquals(1.5, captureEntry.getAmount().doubleValue(), 0.001);
		assertEquals("jax_chuanhang@alipay.com", captureEntry.getPayerEmail());
		assertEquals("2264872159712354", captureEntry.getPayerId());
	}

	@Test
	public void testUpdateForCancelPayment()
	{
		OrderModel orderModel = generateOrder();
		AlipayPaymentTransactionModel baseTransaction = generateAlipayPaymentTransaction();
		AlipayPaymentTransactionEntryModel baseEntry = generateAlipayPaymentTransactionEntry(PaymentTransactionType.CAPTURE,
				TransactionStatus.ACCEPTED, String.valueOf(paymentTransactionKeyGenerator.generate()) + "44");
		baseEntry.setPaymentTransaction(baseTransaction);
		List<PaymentTransactionModel> transactions = new ArrayList<>();
		transactions.add(baseTransaction);
		orderModel.setPaymentTransactions(transactions);
		final AlipayRawCancelPaymentResult alipayRawCancelPaymentResult = new AlipayRawCancelPaymentResult();
		alipayRawCancelPaymentResult.setIsSuccess("T");

		defaultAlipayPaymentTransactionStrategy.updateForCancelPayment(orderModel, alipayRawCancelPaymentResult);

		Optional<AlipayPaymentTransactionEntryModel> option = defaultAlipayPaymentTransactionStrategy
				.getPaymentTransactionEntry(orderModel, TransactionStatus.ACCEPTED, PaymentTransactionType.CANCEL);

		assertTrue(option.isPresent());
	}

	@Test
	public void testUpdateForNotification()
	{
		OrderModel orderModel = generateOrder();
		addPaymentTransaction(orderModel, PaymentTransactionType.REQUEST, TransactionStatus.ACCEPTED);
		AlipayRawDirectPayNotification directPayNotifyResponseData = new AlipayRawDirectPayNotification();
		directPayNotifyResponseData.setTradeNo("8125764794235471");
		directPayNotifyResponseData.setTradeStatus("TRADE_FINISHED");
		directPayNotifyResponseData.setUseCoupon("T");
		directPayNotifyResponseData.setBuyerEmail("test@gmail.com");
		directPayNotifyResponseData.setBuyerId("2897165714527821");
		directPayNotifyResponseData.setTotalFee(2.0);

		DefaultAlipayPaymentTransactionStrategy spy = Mockito.spy(defaultAlipayPaymentTransactionStrategy);
		spy.setPaymentTransactionKeyGenerator(paymentTransactionKeyGenerator);

		AlipayPaymentTransactionModel transaction = (AlipayPaymentTransactionModel) orderModel.getPaymentTransactions().get(0);
		Mockito.doReturn(transaction).when(spy)
				.getPaymentTransactionToUpdate(Matchers.eq(orderModel), Matchers.any(TransactionStatus.class), Matchers.anyString());

		spy.updateForNotification(orderModel, directPayNotifyResponseData);

		Optional<AlipayPaymentTransactionEntryModel> option = defaultAlipayPaymentTransactionStrategy
				.getPaymentTransactionEntry(orderModel, TransactionStatus.ACCEPTED, PaymentTransactionType.CAPTURE);
		assertTrue(option.isPresent());
		assertEquals("8125764794235471", transaction.getAlipayCode());
		AlipayPaymentTransactionEntryModel entry = option.get();
		assertTrue(entry.getCouponUsed());
		assertEquals("test@gmail.com", entry.getPayerEmail());
		assertEquals("2897165714527821", entry.getPayerId());
		assertEquals(2.0, entry.getAdjustedAmount().doubleValue(), 0.01);
		assertEquals("Trade Status:TRADE_FINISHED", entry.getTransactionStatusDetails());
	}

	@Test
	public void testUpdateForError()
	{
		OrderModel orderModel = generateOrder();
		addPaymentTransaction(orderModel, PaymentTransactionType.REQUEST, TransactionStatus.ACCEPTED);
		AlipayRawDirectPayErrorInfo alipayRawDirectPayErrorInfo = new AlipayRawDirectPayErrorInfo();
		alipayRawDirectPayErrorInfo.setErrorCode("ILLEGAL_FEE_PARAM");
		alipayRawDirectPayErrorInfo.setBuyerEmail("test@gmail.com");
		alipayRawDirectPayErrorInfo.setBuyerId("2897165714527821");
		AlipayPaymentTransactionModel transaction = (AlipayPaymentTransactionModel) orderModel.getPaymentTransactions().get(0);
		defaultAlipayPaymentTransactionStrategy.setAlipayPaymentTransactionDao(alipayPaymentTransactionDao);
		Mockito.when(alipayPaymentTransactionDao.findTransactionByLatestRequestEntry(orderModel, true)).thenReturn(transaction);

		defaultAlipayPaymentTransactionStrategy.updateForError(orderModel, alipayRawDirectPayErrorInfo);

		Optional<AlipayPaymentTransactionEntryModel> option = defaultAlipayPaymentTransactionStrategy
				.getPaymentTransactionEntry(orderModel, TransactionStatus.ERROR, PaymentTransactionType.CAPTURE);
		assertTrue(option.isPresent());

		AlipayPaymentTransactionEntryModel entry = option.get();
		assertEquals("Error CodeILLEGAL_FEE_PARAM", entry.getTransactionStatusDetails());
		assertEquals("test@gmail.com", entry.getPayerEmail());
		assertEquals("2897165714527821", entry.getPayerId());
		assertEquals(transaction, entry.getPaymentTransaction());
	}

	private OrderModel generateOrder()
	{
		CurrencyModel currency = new CurrencyModel();
		currency.setIsocode("USD");
		OrderModel order = new OrderModel();
		order.setCode("00000001");
		order.setTotalPrice(1.5);
		order.setCurrency(currency);
		order.setDate(new Date());
		order.setUser(userService.getCurrentUser());
		return order;
	}

	private AlipayPaymentTransactionModel generateAlipayPaymentTransaction()
	{
		AlipayPaymentTransactionModel transaction = new AlipayPaymentTransactionModel();
		transaction.setPaymentUrl("testRequestUrl");
		transaction.setPayMethod(AlipayPayMethod.DIRECTPAY);
		transaction.setPaymentProvider(PaymentConstants.Basic.PAYMENT_PROVIDER);
		transaction.setAlipayCode("10000");
		transaction.setRequestId("000001");
		return transaction;
	}

	private AlipayPaymentTransactionEntryModel generateAlipayPaymentTransactionEntry(PaymentTransactionType type,
			TransactionStatus status, String code)
	{
		AlipayPaymentTransactionEntryModel entry = new AlipayPaymentTransactionEntryModel();
		entry.setType(type);
		entry.setTransactionStatus(status.name());
		entry.setCode(code);
		return entry;
	}

	private AlipayRefundRequestData genereateRefundRequestData()
	{
		AlipayRefundRequestData alipayRefundRequestData = new AlipayRefundRequestData();
		alipayRefundRequestData.setBatchNo("201601011234");
		return alipayRefundRequestData;
	}

	private void addPaymentTransaction(OrderModel orderModel, PaymentTransactionType type, TransactionStatus status)
	{
		AlipayPaymentTransactionModel baseTransaction = generateAlipayPaymentTransaction();
		SecureRandom rn = new SecureRandom();
		AlipayPaymentTransactionEntryModel baseEntry = generateAlipayPaymentTransactionEntry(type, status,
				String.valueOf(paymentTransactionKeyGenerator.generate()) + rn.nextInt(1000));
		baseEntry.setPaymentTransaction(baseTransaction);
		List<PaymentTransactionModel> transactions = new ArrayList<>();
		transactions.add(baseTransaction);
		orderModel.setPaymentTransactions(transactions);
	}
}
