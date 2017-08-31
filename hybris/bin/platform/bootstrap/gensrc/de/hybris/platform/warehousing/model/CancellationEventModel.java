/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 25 Aug 2017 4:31:18 PM                      ---
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
package de.hybris.platform.warehousing.model;

import de.hybris.bootstrap.annotations.Accessor;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.servicelayer.model.ItemModelContext;
import de.hybris.platform.warehousing.model.InventoryEventModel;

/**
 * Generated model class for type CancellationEvent first defined at extension warehousing.
 * <p>
 * A cancellation inventory event.
 */
@SuppressWarnings("all")
public class CancellationEventModel extends InventoryEventModel
{
	/**<i>Generated model type code constant.</i>*/
	public final static String _TYPECODE = "CancellationEvent";
	
	/** <i>Generated constant</i> - Attribute key of <code>CancellationEvent.reason</code> attribute defined at extension <code>warehousing</code>. */
	public static final String REASON = "reason";
	
	
	/**
	 * <i>Generated constructor</i> - Default constructor for generic creation.
	 */
	public CancellationEventModel()
	{
		super();
	}
	
	/**
	 * <i>Generated constructor</i> - Default constructor for creation with existing context
	 * @param ctx the model context to be injected, must not be null
	 */
	public CancellationEventModel(final ItemModelContext ctx)
	{
		super(ctx);
	}
	
	/**
	 * <i>Generated constructor</i> - Constructor with all mandatory attributes.
	 * @deprecated Since 4.1.1 Please use the default constructor without parameters
	 * @param _reason initial attribute declared by type <code>CancellationEvent</code> at extension <code>warehousing</code>
	 * @param _stockLevel initial attribute declared by type <code>InventoryEvent</code> at extension <code>warehousing</code>
	 */
	@Deprecated
	public CancellationEventModel(final String _reason, final StockLevelModel _stockLevel)
	{
		super();
		setReason(_reason);
		setStockLevel(_stockLevel);
	}
	
	/**
	 * <i>Generated constructor</i> - for all mandatory and initial attributes.
	 * @deprecated Since 4.1.1 Please use the default constructor without parameters
	 * @param _owner initial attribute declared by type <code>Item</code> at extension <code>core</code>
	 * @param _reason initial attribute declared by type <code>CancellationEvent</code> at extension <code>warehousing</code>
	 * @param _stockLevel initial attribute declared by type <code>InventoryEvent</code> at extension <code>warehousing</code>
	 */
	@Deprecated
	public CancellationEventModel(final ItemModel _owner, final String _reason, final StockLevelModel _stockLevel)
	{
		super();
		setOwner(_owner);
		setReason(_reason);
		setStockLevel(_stockLevel);
	}
	
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CancellationEvent.reason</code> attribute defined at extension <code>warehousing</code>. 
	 * @return the reason - Specifies the reason for this cancellation.
	 */
	@Accessor(qualifier = "reason", type = Accessor.Type.GETTER)
	public String getReason()
	{
		return getPersistenceContext().getPropertyValue(REASON);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>CancellationEvent.reason</code> attribute defined at extension <code>warehousing</code>. 
	 *  
	 * @param value the reason - Specifies the reason for this cancellation.
	 */
	@Accessor(qualifier = "reason", type = Accessor.Type.SETTER)
	public void setReason(final String value)
	{
		getPersistenceContext().setPropertyValue(REASON, value);
	}
	
}
