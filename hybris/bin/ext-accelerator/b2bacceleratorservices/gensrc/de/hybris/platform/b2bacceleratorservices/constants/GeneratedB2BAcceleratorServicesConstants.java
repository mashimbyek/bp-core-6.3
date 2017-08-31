/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 10 Aug 2017 10:54:06 AM                     ---
 * ----------------------------------------------------------------
 *  
 * [y] hybris Platform
 *  
 * Copyright (c) 2000-2016 SAP SE
 * All rights reserved.
 *  
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 *  
 */
package de.hybris.platform.b2bacceleratorservices.constants;

/**
 * @deprecated use constants in Model classes instead
 */
@Deprecated
@SuppressWarnings({"unused","cast","PMD"})
public class GeneratedB2BAcceleratorServicesConstants
{
	public static final String EXTENSIONNAME = "b2bacceleratorservices";
	public static class TC
	{
		public static final String CHECKOUTPAYMENTTYPE = "CheckoutPaymentType".intern();
		public static final String ORDERTHRESHOLDDISCOUNTPERCENTAGEPROMOTION = "OrderThresholdDiscountPercentagePromotion".intern();
		public static final String PRODUCTPRICEDISCOUNTPROMOTIONBYPAYMENTTYPE = "ProductPriceDiscountPromotionByPaymentType".intern();
		public static final String PRODUCTTHRESHOLDPRICEDISCOUNTPROMOTION = "ProductThresholdPriceDiscountPromotion".intern();
		public static final String REPLENISHMENTPROCESS = "ReplenishmentProcess".intern();
	}
	public static class Attributes
	{
		public static class AbstractOrder
		{
			public static final String PAYMENTTYPE = "paymentType".intern();
			public static final String PURCHASEORDERNUMBER = "purchaseOrderNumber".intern();
		}
		public static class CartToOrderCronJob
		{
			public static final String ORDERS = "orders".intern();
		}
		public static class Category
		{
			public static final String STOCKLEVELTHRESHOLD = "stockLevelThreshold".intern();
		}
		public static class CMSSite
		{
			public static final String DEFAULTSTOCKLEVELTHRESHOLD = "defaultStockLevelThreshold".intern();
		}
		public static class Order
		{
			public static final String SCHEDULINGCRONJOB = "schedulingCronJob".intern();
		}
	}
	public static class Enumerations
	{
		public static class CheckoutFlowEnum
		{
			public static final String SINGLE = "SINGLE".intern();
		}
		public static class CheckoutPaymentType
		{
			public static final String CARD = "CARD".intern();
			public static final String ACCOUNT = "ACCOUNT".intern();
		}
	}
	public static class Relations
	{
		public static final String ORDER2CARTTOORDERCRONJOB = "Order2CartToOrderCronJob".intern();
	}
	
	protected GeneratedB2BAcceleratorServicesConstants()
	{
		// private constructor
	}
	
	
}
