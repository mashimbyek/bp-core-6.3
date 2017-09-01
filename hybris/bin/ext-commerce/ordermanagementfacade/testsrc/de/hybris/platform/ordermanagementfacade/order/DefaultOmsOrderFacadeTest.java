package de.hybris.platform.ordermanagementfacade.order;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.ordermanagementfacade.order.impl.DefaultOmsOrderFacade;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.store.BaseStoreModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultOmsOrderFacadeTest
{
	@InjectMocks
	private DefaultOmsOrderFacade omsOrderFacade;
	@Mock
	private GenericDao<OrderModel> orderGenericDao;
	@Mock
	private OrderModel orderModel;
	@Mock
	private BusinessProcessService businessProcessService;
	@Mock
	private ModelService modelService;
	@Mock
	private OrderProcessModel orderProcessModel;
	@Mock
	private BaseStoreModel baseStoreModel;

	private static final String ORDER_PROCESS = "order-process";

	@Before
	public void setup()
	{
		omsOrderFacade.setOrderGenericDao(orderGenericDao);
		when(orderProcessModel.getCode()).thenReturn(ORDER_PROCESS);
	}

	@Test(expected = IllegalArgumentException.class)
	public void approveFraudulentOrder_FailureNullCode()
	{
		omsOrderFacade.approvePotentiallyFraudulentOrder(null);
	}

	@Test(expected = IllegalStateException.class)
	public void approveFraudulentOrder_FailureWrongStatus()
	{
		//Given
		when(orderModel.getStatus()).thenReturn(OrderStatus.CANCELLED);
		when(orderGenericDao.find(anyMap())).thenReturn(Arrays.asList(orderModel));

		//When
		omsOrderFacade.approvePotentiallyFraudulentOrder(anyString());
	}

	@Test
	public void approveFraudulentOrder_Success()
	{
		//Given
		when(orderModel.getStatus()).thenReturn(OrderStatus.WAIT_FRAUD_MANUAL_CHECK);
		when(orderGenericDao.find(anyMap())).thenReturn(Arrays.asList(orderModel));
		doNothing().when(modelService).save(any());
		when(orderModel.getStore()).thenReturn(baseStoreModel);
		when(orderModel.getOrderProcess()).thenReturn(Arrays.asList(orderProcessModel));
		when(baseStoreModel.getSubmitOrderProcessCode()).thenReturn(ORDER_PROCESS);

		//When
		omsOrderFacade.approvePotentiallyFraudulentOrder(anyString());

		//Verify
		verify(businessProcessService).triggerEvent(Matchers.<String>any());
	}

	@Test(expected = IllegalArgumentException.class)
	public void rejectFraudulentOrder_FailureNullCode()
	{
		omsOrderFacade.rejectPotentiallyFraudulentOrder(null);
	}

	@Test(expected = IllegalStateException.class)
	public void rejectFraudulentOrder_FailureWrongStatus()
	{
		//Given
		when(orderModel.getStatus()).thenReturn(OrderStatus.CANCELLED);
		when(orderGenericDao.find(anyMap())).thenReturn(Arrays.asList(orderModel));

		//When
		omsOrderFacade.rejectPotentiallyFraudulentOrder(anyString());
	}

	@Test
	public void rejectFraudulentOrder_Success()
	{
		//Given
		when(orderModel.getStatus()).thenReturn(OrderStatus.WAIT_FRAUD_MANUAL_CHECK);
		when(orderGenericDao.find(anyMap())).thenReturn(Arrays.asList(orderModel));
		doNothing().when(modelService).save(any());
		when(orderModel.getStore()).thenReturn(baseStoreModel);
		when(orderModel.getOrderProcess()).thenReturn(Arrays.asList(orderProcessModel));
		when(baseStoreModel.getSubmitOrderProcessCode()).thenReturn(ORDER_PROCESS);

		//When
		omsOrderFacade.rejectPotentiallyFraudulentOrder(anyString());

		//Verify
		verify(businessProcessService).triggerEvent(Matchers.<String>any());
	}
}
