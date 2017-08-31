

package b2bacceleratorservices;

import java.util.*;
import java.io.Serializable;
import de.hybris.platform.util.*;
import de.hybris.platform.core.*;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.type.*;
import de.hybris.platform.persistence.type.*;
import de.hybris.platform.persistence.enumeration.*;
import de.hybris.platform.persistence.property.PersistenceManager;
import de.hybris.platform.persistence.*;

/**
 * Generated by hybris Platform.
 */
@SuppressWarnings({"cast","unused","boxing","null", "PMD"})
public class GeneratedTypeInitializer extends AbstractTypeInitializer
{
	/**
	 * Generated by hybris Platform.
	 */
	public GeneratedTypeInitializer( ManagerEJB manager, Map params )
	{
		super( manager, params );
	}


	/**
	 * Generated by hybris Platform.
	 */
	@Override
	protected void performRemoveObjects( ManagerEJB manager, Map params ) throws JaloBusinessException
	{
		// no-op by now
	}

	/**
	 * Generated by hybris Platform.
	 */
	@Override
	protected final void performCreateTypes( final ManagerEJB manager, Map params ) throws JaloBusinessException
	{
		// performCreateTypes
	
	
		createItemType(
			"ReplenishmentProcess",
			"StoreFrontCustomerProcess",
			de.hybris.platform.b2bacceleratorservices.jalo.process.ReplenishmentProcess.class,
			null,
			false,
			null,
			false
		);
	
		createItemType(
			"OrderThresholdDiscountPercentagePromotion",
			"OrderPromotion",
			de.hybris.platform.b2bacceleratorservices.jalo.promotions.OrderThresholdDiscountPercentagePromotion.class,
			null,
			false,
			null,
			false
		);
	
		createItemType(
			"ProductPriceDiscountPromotionByPaymentType",
			"ProductPromotion",
			de.hybris.platform.b2bacceleratorservices.jalo.promotions.ProductPriceDiscountPromotionByPaymentType.class,
			null,
			false,
			null,
			false
		);
	
		createItemType(
			"ProductThresholdPriceDiscountPromotion",
			"ProductPromotion",
			de.hybris.platform.b2bacceleratorservices.jalo.promotions.ProductThresholdPriceDiscountPromotion.class,
			null,
			false,
			null,
			false
		);
	
		createRelationType(
			"Order2CartToOrderCronJob",
			null,
			true
		);
	
		createEnumerationType(
			"CheckoutPaymentType",
			null
		);
	
	}

	/**
	 * Generated by hybris Platform.
	 */
	@Override
	protected final void performModifyTypes( final ManagerEJB manager, Map params ) throws JaloBusinessException
	{
		// performModifyTypes
	

	
	
				single_createattr_AbstractOrder_purchaseOrderNumber();
			
				single_createattr_AbstractOrder_paymentType();
			
				single_createattr_ReplenishmentProcess_cartToOrderCronJob();
			
				single_createattr_OrderThresholdDiscountPercentagePromotion_thresholdTotals();
			
				single_createattr_OrderThresholdDiscountPercentagePromotion_percentageDiscount();
			
				single_createattr_OrderThresholdDiscountPercentagePromotion_messageFired();
			
				single_createattr_OrderThresholdDiscountPercentagePromotion_messageCouldHaveFired();
			
				single_createattr_ProductPriceDiscountPromotionByPaymentType_productDiscountPrice();
			
				single_createattr_ProductPriceDiscountPromotionByPaymentType_messageFired();
			
				single_createattr_ProductPriceDiscountPromotionByPaymentType_messageCouldHaveFired();
			
				single_createattr_ProductPriceDiscountPromotionByPaymentType_paymentType();
			
				single_createattr_ProductThresholdPriceDiscountPromotion_productThresholdPrice();
			
				single_createattr_ProductThresholdPriceDiscountPromotion_productPriceDiscount();
			
				single_createattr_ProductThresholdPriceDiscountPromotion_messageFired();
			
				single_createattr_ProductThresholdPriceDiscountPromotion_messageCouldHaveFired();
			
				single_createattr_CMSSite_defaultStockLevelThreshold();
			
				single_createattr_Category_stockLevelThreshold();
			
		createRelationAttributes(
			"Order2CartToOrderCronJob", 
			false, 

			"schedulingCronJob", 
			"CartToOrderCronJob", 
			true,
			de.hybris.platform.jalo.type.AttributeDescriptor.READ_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.WRITE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.OPTIONAL_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.INITIAL_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.REMOVE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.SEARCH_FLAG, 
			false,
			false,
			CollectionType.COLLECTION,
			"orders", 
			"Order", 
			true,
			de.hybris.platform.jalo.type.AttributeDescriptor.READ_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.WRITE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.OPTIONAL_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.INITIAL_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.REMOVE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.SEARCH_FLAG, 
			true,
			false,
			CollectionType.COLLECTION
		);
	

	}

	
	public void single_createattr_AbstractOrder_purchaseOrderNumber() throws JaloBusinessException
	{
		
						Map sqlColumnDefinitions = null;
					
				createPropertyAttribute(
					"AbstractOrder", 
					"purchaseOrderNumber",  
					null,
					"java.lang.String",
					de.hybris.platform.jalo.type.AttributeDescriptor.READ_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.WRITE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.OPTIONAL_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.REMOVE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.SEARCH_FLAG,
					null,
					sqlColumnDefinitions
				);
			
	}
	
	public void single_createattr_AbstractOrder_paymentType() throws JaloBusinessException
	{
		
						Map sqlColumnDefinitions = null;
					
				createPropertyAttribute(
					"AbstractOrder", 
					"paymentType",  
					null,
					"CheckoutPaymentType",
					de.hybris.platform.jalo.type.AttributeDescriptor.READ_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.WRITE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.OPTIONAL_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.REMOVE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.SEARCH_FLAG,
					null,
					sqlColumnDefinitions
				);
			
	}
	
	public void single_createattr_ReplenishmentProcess_cartToOrderCronJob() throws JaloBusinessException
	{
		
						Map sqlColumnDefinitions = null;
					
				createPropertyAttribute(
					"ReplenishmentProcess", 
					"cartToOrderCronJob",  
					null,
					"CartToOrderCronJob",
					de.hybris.platform.jalo.type.AttributeDescriptor.READ_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.WRITE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.OPTIONAL_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.REMOVE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.SEARCH_FLAG,
					null,
					sqlColumnDefinitions
				);
			
	}
	
	public void single_createattr_OrderThresholdDiscountPercentagePromotion_thresholdTotals() throws JaloBusinessException
	{
		
						Map sqlColumnDefinitions = null;
					
				createPropertyAttribute(
					"OrderThresholdDiscountPercentagePromotion", 
					"thresholdTotals",  
					null,
					"PromotionPriceRowCollectionType",
					de.hybris.platform.jalo.type.AttributeDescriptor.READ_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.WRITE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.OPTIONAL_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.PARTOF_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.REMOVE_FLAG,
					null,
					sqlColumnDefinitions
				);
			
	}
	
	public void single_createattr_OrderThresholdDiscountPercentagePromotion_percentageDiscount() throws JaloBusinessException
	{
		
						Map sqlColumnDefinitions = null;
					
				createPropertyAttribute(
					"OrderThresholdDiscountPercentagePromotion", 
					"percentageDiscount",  
					null,
					"java.lang.Double",
					de.hybris.platform.jalo.type.AttributeDescriptor.READ_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.WRITE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.OPTIONAL_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.REMOVE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.SEARCH_FLAG,
					null,
					sqlColumnDefinitions
				);
			
	}
	
	public void single_createattr_OrderThresholdDiscountPercentagePromotion_messageFired() throws JaloBusinessException
	{
		
						Map sqlColumnDefinitions = new HashMap();
						
							sqlColumnDefinitions.put(
								de.hybris.platform.persistence.property.PersistenceManager.NO_DATABASE,
								"HYBRIS.LONG_STRING"
							);
						
				createPropertyAttribute(
					"OrderThresholdDiscountPercentagePromotion", 
					"messageFired",  
					null,
					"localized:java.lang.String",
					de.hybris.platform.jalo.type.AttributeDescriptor.READ_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.WRITE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.OPTIONAL_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.REMOVE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.SEARCH_FLAG,
					null,
					sqlColumnDefinitions
				);
			
	}
	
	public void single_createattr_OrderThresholdDiscountPercentagePromotion_messageCouldHaveFired() throws JaloBusinessException
	{
		
						Map sqlColumnDefinitions = new HashMap();
						
							sqlColumnDefinitions.put(
								de.hybris.platform.persistence.property.PersistenceManager.NO_DATABASE,
								"HYBRIS.LONG_STRING"
							);
						
				createPropertyAttribute(
					"OrderThresholdDiscountPercentagePromotion", 
					"messageCouldHaveFired",  
					null,
					"localized:java.lang.String",
					de.hybris.platform.jalo.type.AttributeDescriptor.READ_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.WRITE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.OPTIONAL_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.REMOVE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.SEARCH_FLAG,
					null,
					sqlColumnDefinitions
				);
			
	}
	
	public void single_createattr_ProductPriceDiscountPromotionByPaymentType_productDiscountPrice() throws JaloBusinessException
	{
		
						Map sqlColumnDefinitions = null;
					
				createPropertyAttribute(
					"ProductPriceDiscountPromotionByPaymentType", 
					"productDiscountPrice",  
					null,
					"PromotionPriceRowCollectionType",
					de.hybris.platform.jalo.type.AttributeDescriptor.READ_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.WRITE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.OPTIONAL_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.PARTOF_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.REMOVE_FLAG,
					null,
					sqlColumnDefinitions
				);
			
	}
	
	public void single_createattr_ProductPriceDiscountPromotionByPaymentType_messageFired() throws JaloBusinessException
	{
		
						Map sqlColumnDefinitions = new HashMap();
						
							sqlColumnDefinitions.put(
								de.hybris.platform.persistence.property.PersistenceManager.NO_DATABASE,
								"HYBRIS.LONG_STRING"
							);
						
				createPropertyAttribute(
					"ProductPriceDiscountPromotionByPaymentType", 
					"messageFired",  
					null,
					"localized:java.lang.String",
					de.hybris.platform.jalo.type.AttributeDescriptor.READ_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.WRITE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.OPTIONAL_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.REMOVE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.SEARCH_FLAG,
					null,
					sqlColumnDefinitions
				);
			
	}
	
	public void single_createattr_ProductPriceDiscountPromotionByPaymentType_messageCouldHaveFired() throws JaloBusinessException
	{
		
						Map sqlColumnDefinitions = new HashMap();
						
							sqlColumnDefinitions.put(
								de.hybris.platform.persistence.property.PersistenceManager.NO_DATABASE,
								"HYBRIS.LONG_STRING"
							);
						
				createPropertyAttribute(
					"ProductPriceDiscountPromotionByPaymentType", 
					"messageCouldHaveFired",  
					null,
					"localized:java.lang.String",
					de.hybris.platform.jalo.type.AttributeDescriptor.READ_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.WRITE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.OPTIONAL_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.REMOVE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.SEARCH_FLAG,
					null,
					sqlColumnDefinitions
				);
			
	}
	
	public void single_createattr_ProductPriceDiscountPromotionByPaymentType_paymentType() throws JaloBusinessException
	{
		
						Map sqlColumnDefinitions = null;
					
				createPropertyAttribute(
					"ProductPriceDiscountPromotionByPaymentType", 
					"paymentType",  
					null,
					"CheckoutPaymentType",
					de.hybris.platform.jalo.type.AttributeDescriptor.READ_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.WRITE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.OPTIONAL_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.REMOVE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.SEARCH_FLAG,
					null,
					sqlColumnDefinitions
				);
			
	}
	
	public void single_createattr_ProductThresholdPriceDiscountPromotion_productThresholdPrice() throws JaloBusinessException
	{
		
						Map sqlColumnDefinitions = null;
					
				createPropertyAttribute(
					"ProductThresholdPriceDiscountPromotion", 
					"productThresholdPrice",  
					null,
					"PromotionPriceRowCollectionType",
					de.hybris.platform.jalo.type.AttributeDescriptor.READ_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.WRITE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.OPTIONAL_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.PARTOF_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.REMOVE_FLAG,
					null,
					sqlColumnDefinitions
				);
			
	}
	
	public void single_createattr_ProductThresholdPriceDiscountPromotion_productPriceDiscount() throws JaloBusinessException
	{
		
						Map sqlColumnDefinitions = null;
					
				createPropertyAttribute(
					"ProductThresholdPriceDiscountPromotion", 
					"productPriceDiscount",  
					null,
					"PromotionPriceRowCollectionType",
					de.hybris.platform.jalo.type.AttributeDescriptor.READ_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.WRITE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.OPTIONAL_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.PARTOF_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.REMOVE_FLAG,
					null,
					sqlColumnDefinitions
				);
			
	}
	
	public void single_createattr_ProductThresholdPriceDiscountPromotion_messageFired() throws JaloBusinessException
	{
		
						Map sqlColumnDefinitions = new HashMap();
						
							sqlColumnDefinitions.put(
								de.hybris.platform.persistence.property.PersistenceManager.NO_DATABASE,
								"HYBRIS.LONG_STRING"
							);
						
				createPropertyAttribute(
					"ProductThresholdPriceDiscountPromotion", 
					"messageFired",  
					null,
					"localized:java.lang.String",
					de.hybris.platform.jalo.type.AttributeDescriptor.READ_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.WRITE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.OPTIONAL_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.REMOVE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.SEARCH_FLAG,
					null,
					sqlColumnDefinitions
				);
			
	}
	
	public void single_createattr_ProductThresholdPriceDiscountPromotion_messageCouldHaveFired() throws JaloBusinessException
	{
		
						Map sqlColumnDefinitions = new HashMap();
						
							sqlColumnDefinitions.put(
								de.hybris.platform.persistence.property.PersistenceManager.NO_DATABASE,
								"HYBRIS.LONG_STRING"
							);
						
				createPropertyAttribute(
					"ProductThresholdPriceDiscountPromotion", 
					"messageCouldHaveFired",  
					null,
					"localized:java.lang.String",
					de.hybris.platform.jalo.type.AttributeDescriptor.READ_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.WRITE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.OPTIONAL_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.REMOVE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.SEARCH_FLAG,
					null,
					sqlColumnDefinitions
				);
			
	}
	
	public void single_createattr_CMSSite_defaultStockLevelThreshold() throws JaloBusinessException
	{
		
						Map sqlColumnDefinitions = null;
					
				createPropertyAttribute(
					"CMSSite", 
					"defaultStockLevelThreshold",  
					null,
					"java.lang.Integer",
					de.hybris.platform.jalo.type.AttributeDescriptor.READ_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.WRITE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.OPTIONAL_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.REMOVE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.SEARCH_FLAG,
					null,
					sqlColumnDefinitions
				);
			
	}
	
	public void single_createattr_Category_stockLevelThreshold() throws JaloBusinessException
	{
		
						Map sqlColumnDefinitions = null;
					
				createPropertyAttribute(
					"Category", 
					"stockLevelThreshold",  
					null,
					"java.lang.Integer",
					de.hybris.platform.jalo.type.AttributeDescriptor.READ_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.WRITE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.OPTIONAL_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.REMOVE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.SEARCH_FLAG,
					null,
					sqlColumnDefinitions
				);
			
	}
	


	/**
	 * Generated by hybris Platform.
	 */
	@Override
	protected final void performCreateObjects( final ManagerEJB manager, Map params ) throws JaloBusinessException
	{
		// performCreateObjects
	
	
		createEnumerationValues(
			"CheckoutPaymentType",
			true,
			Arrays.asList( new String[] {
			
				"CARD",
				"ACCOUNT"
			} )
		);
	
		createEnumerationValues(
			"CheckoutFlowEnum",
			false,
			Arrays.asList( new String[] {
			
				"SINGLE"
			} )
		);
	
		single_setRelAttributeProperties_Order2CartToOrderCronJob_source();
	
		single_setRelAttributeProperties_Order2CartToOrderCronJob_target();
	
		connectRelation(
			"Order2CartToOrderCronJob", 
			false, 
			"schedulingCronJob", 
			"CartToOrderCronJob", 
			true,
			de.hybris.platform.jalo.type.AttributeDescriptor.READ_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.WRITE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.OPTIONAL_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.INITIAL_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.REMOVE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.SEARCH_FLAG, 
			"orders", 
			"Order", 
			true,
			de.hybris.platform.jalo.type.AttributeDescriptor.READ_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.WRITE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.OPTIONAL_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.INITIAL_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.REMOVE_FLAG|de.hybris.platform.jalo.type.AttributeDescriptor.SEARCH_FLAG, 
			true,
			true
		);
	
				{
				Map customPropsMap = new HashMap();
				
				changeMetaType(
					"AbstractOrder",
					null,
					customPropsMap
				);
				}
			
			single_setAttributeProperties_AbstractOrder_purchaseOrderNumber();
		
			single_setAttributeProperties_AbstractOrder_paymentType();
		
				{
				Map customPropsMap = new HashMap();
				
				setItemTypeProperties(
					"ReplenishmentProcess",
					false,
					true,
					true,
					null,
					customPropsMap
				);
				}
			
			single_setAttributeProperties_ReplenishmentProcess_cartToOrderCronJob();
		
				{
				Map customPropsMap = new HashMap();
				
				setItemTypeProperties(
					"OrderThresholdDiscountPercentagePromotion",
					false,
					true,
					true,
					null,
					customPropsMap
				);
				}
			
			single_setAttributeProperties_OrderThresholdDiscountPercentagePromotion_thresholdTotals();
		
			single_setAttributeProperties_OrderThresholdDiscountPercentagePromotion_percentageDiscount();
		
			single_setAttributeProperties_OrderThresholdDiscountPercentagePromotion_messageFired();
		
			single_setAttributeProperties_OrderThresholdDiscountPercentagePromotion_messageCouldHaveFired();
		
				{
				Map customPropsMap = new HashMap();
				
				setItemTypeProperties(
					"ProductPriceDiscountPromotionByPaymentType",
					false,
					true,
					true,
					null,
					customPropsMap
				);
				}
			
			single_setAttributeProperties_ProductPriceDiscountPromotionByPaymentType_productDiscountPrice();
		
			single_setAttributeProperties_ProductPriceDiscountPromotionByPaymentType_messageFired();
		
			single_setAttributeProperties_ProductPriceDiscountPromotionByPaymentType_messageCouldHaveFired();
		
			single_setAttributeProperties_ProductPriceDiscountPromotionByPaymentType_paymentType();
		
				{
				Map customPropsMap = new HashMap();
				
				setItemTypeProperties(
					"ProductThresholdPriceDiscountPromotion",
					false,
					true,
					true,
					null,
					customPropsMap
				);
				}
			
			single_setAttributeProperties_ProductThresholdPriceDiscountPromotion_productThresholdPrice();
		
			single_setAttributeProperties_ProductThresholdPriceDiscountPromotion_productPriceDiscount();
		
			single_setAttributeProperties_ProductThresholdPriceDiscountPromotion_messageFired();
		
			single_setAttributeProperties_ProductThresholdPriceDiscountPromotion_messageCouldHaveFired();
		
				{
				Map customPropsMap = new HashMap();
				
				changeMetaType(
					"CMSSite",
					null,
					customPropsMap
				);
				}
			
			single_setAttributeProperties_CMSSite_defaultStockLevelThreshold();
		
				{
				Map customPropsMap = new HashMap();
				
				changeMetaType(
					"Category",
					null,
					customPropsMap
				);
				}
			
			single_setAttributeProperties_Category_stockLevelThreshold();
		
				setDefaultProperties(
					"CheckoutPaymentType",
					true,
					true,
					null
				);
			
				changeMetaType(
					"CheckoutFlowEnum",
					null,
					null
				);
			
	}


		
						public void single_setAttributeProperties_AbstractOrder_purchaseOrderNumber() throws JaloBusinessException
						{
							
							
							
							Map customPropsMap = new HashMap();
							
							setAttributeProperties(
								"AbstractOrder", 
								"purchaseOrderNumber",
								false, 
								null,
								null,
								null,
								true,
								true,
								null,
								customPropsMap,
								null
							);
						}
					
						public void single_setAttributeProperties_AbstractOrder_paymentType() throws JaloBusinessException
						{
							
							
							
							Map customPropsMap = new HashMap();
							
							setAttributeProperties(
								"AbstractOrder", 
								"paymentType",
								false, 
								null,
								null,
								null,
								true,
								true,
								null,
								customPropsMap,
								null
							);
						}
					
						public void single_setAttributeProperties_ReplenishmentProcess_cartToOrderCronJob() throws JaloBusinessException
						{
							
							
							
							Map customPropsMap = new HashMap();
							
							setAttributeProperties(
								"ReplenishmentProcess", 
								"cartToOrderCronJob",
								false, 
								null,
								null,
								null,
								true,
								true,
								null,
								customPropsMap,
								null
							);
						}
					
						public void single_setAttributeProperties_OrderThresholdDiscountPercentagePromotion_thresholdTotals() throws JaloBusinessException
						{
							
							
							
							Map customPropsMap = new HashMap();
							
							setAttributeProperties(
								"OrderThresholdDiscountPercentagePromotion", 
								"thresholdTotals",
								false, 
								null,
								null,
								null,
								true,
								true,
								null,
								customPropsMap,
								null
							);
						}
					
						public void single_setAttributeProperties_OrderThresholdDiscountPercentagePromotion_percentageDiscount() throws JaloBusinessException
						{
							
							
							
							Map customPropsMap = new HashMap();
							
							setAttributeProperties(
								"OrderThresholdDiscountPercentagePromotion", 
								"percentageDiscount",
								false, 
								Double.valueOf(10.0D),
								"Double.valueOf(10.0D)",
								null,
								true,
								true,
								null,
								customPropsMap,
								null
							);
						}
					
						public void single_setAttributeProperties_OrderThresholdDiscountPercentagePromotion_messageFired() throws JaloBusinessException
						{
							
							
							
							Map customPropsMap = new HashMap();
							
							setAttributeProperties(
								"OrderThresholdDiscountPercentagePromotion", 
								"messageFired",
								false, 
								null,
								null,
								null,
								true,
								true,
								null,
								customPropsMap,
								null
							);
						}
					
						public void single_setAttributeProperties_OrderThresholdDiscountPercentagePromotion_messageCouldHaveFired() throws JaloBusinessException
						{
							
							
							
							Map customPropsMap = new HashMap();
							
							setAttributeProperties(
								"OrderThresholdDiscountPercentagePromotion", 
								"messageCouldHaveFired",
								false, 
								null,
								null,
								null,
								true,
								true,
								null,
								customPropsMap,
								null
							);
						}
					
						public void single_setAttributeProperties_ProductPriceDiscountPromotionByPaymentType_productDiscountPrice() throws JaloBusinessException
						{
							
							
							
							Map customPropsMap = new HashMap();
							
							setAttributeProperties(
								"ProductPriceDiscountPromotionByPaymentType", 
								"productDiscountPrice",
								false, 
								null,
								null,
								null,
								true,
								true,
								null,
								customPropsMap,
								null
							);
						}
					
						public void single_setAttributeProperties_ProductPriceDiscountPromotionByPaymentType_messageFired() throws JaloBusinessException
						{
							
							
							
							Map customPropsMap = new HashMap();
							
							setAttributeProperties(
								"ProductPriceDiscountPromotionByPaymentType", 
								"messageFired",
								false, 
								null,
								null,
								null,
								true,
								true,
								null,
								customPropsMap,
								null
							);
						}
					
						public void single_setAttributeProperties_ProductPriceDiscountPromotionByPaymentType_messageCouldHaveFired() throws JaloBusinessException
						{
							
							
							
							Map customPropsMap = new HashMap();
							
							setAttributeProperties(
								"ProductPriceDiscountPromotionByPaymentType", 
								"messageCouldHaveFired",
								false, 
								null,
								null,
								null,
								true,
								true,
								null,
								customPropsMap,
								null
							);
						}
					
						public void single_setAttributeProperties_ProductPriceDiscountPromotionByPaymentType_paymentType() throws JaloBusinessException
						{
							
							
							
							Map customPropsMap = new HashMap();
							
							setAttributeProperties(
								"ProductPriceDiscountPromotionByPaymentType", 
								"paymentType",
								false, 
								null,
								null,
								null,
								true,
								true,
								null,
								customPropsMap,
								null
							);
						}
					
						public void single_setAttributeProperties_ProductThresholdPriceDiscountPromotion_productThresholdPrice() throws JaloBusinessException
						{
							
							
							
							Map customPropsMap = new HashMap();
							
							setAttributeProperties(
								"ProductThresholdPriceDiscountPromotion", 
								"productThresholdPrice",
								false, 
								null,
								null,
								null,
								true,
								true,
								null,
								customPropsMap,
								null
							);
						}
					
						public void single_setAttributeProperties_ProductThresholdPriceDiscountPromotion_productPriceDiscount() throws JaloBusinessException
						{
							
							
							
							Map customPropsMap = new HashMap();
							
							setAttributeProperties(
								"ProductThresholdPriceDiscountPromotion", 
								"productPriceDiscount",
								false, 
								null,
								null,
								null,
								true,
								true,
								null,
								customPropsMap,
								null
							);
						}
					
						public void single_setAttributeProperties_ProductThresholdPriceDiscountPromotion_messageFired() throws JaloBusinessException
						{
							
							
							
							Map customPropsMap = new HashMap();
							
							setAttributeProperties(
								"ProductThresholdPriceDiscountPromotion", 
								"messageFired",
								false, 
								null,
								null,
								null,
								true,
								true,
								null,
								customPropsMap,
								null
							);
						}
					
						public void single_setAttributeProperties_ProductThresholdPriceDiscountPromotion_messageCouldHaveFired() throws JaloBusinessException
						{
							
							
							
							Map customPropsMap = new HashMap();
							
							setAttributeProperties(
								"ProductThresholdPriceDiscountPromotion", 
								"messageCouldHaveFired",
								false, 
								null,
								null,
								null,
								true,
								true,
								null,
								customPropsMap,
								null
							);
						}
					
						public void single_setAttributeProperties_CMSSite_defaultStockLevelThreshold() throws JaloBusinessException
						{
							
							
							
							Map customPropsMap = new HashMap();
							
							setAttributeProperties(
								"CMSSite", 
								"defaultStockLevelThreshold",
								false, 
								null,
								null,
								null,
								true,
								true,
								null,
								customPropsMap,
								null
							);
						}
					
						public void single_setAttributeProperties_Category_stockLevelThreshold() throws JaloBusinessException
						{
							
							
							
							Map customPropsMap = new HashMap();
							
							setAttributeProperties(
								"Category", 
								"stockLevelThreshold",
								false, 
								null,
								null,
								null,
								true,
								true,
								null,
								customPropsMap,
								null
							);
						}
					
								public void single_setRelAttributeProperties_Order2CartToOrderCronJob_source() throws JaloBusinessException
								{
									
									Map customPropsMap = new HashMap();
									

									setAttributeProperties(
										"Order", 
										"schedulingCronJob",
										false, 
										null,
										null,
										null,
										true,
										true,
										null,
										customPropsMap,
										null
									);
								}
							
								public void single_setRelAttributeProperties_Order2CartToOrderCronJob_target() throws JaloBusinessException
								{
									
									Map customPropsMap = new HashMap();
									

									setAttributeProperties(
										"CartToOrderCronJob", 
										"orders",
										false, 
										null,
										null,
										null,
										true,
										true,
										null,
										customPropsMap,
										null
									);
								}
							
}

	