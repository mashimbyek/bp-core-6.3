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
package de.hybris.platform.commerceservices.model.solrsearch.config;

import de.hybris.bootstrap.annotations.Accessor;
import de.hybris.platform.commerceservices.model.solrsearch.config.SolrSortModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.model.ItemModelContext;

/**
 * Generated model class for type SolrSortField first defined at extension commerceservices.
 * <p>
 * Extending SolrSortField type with additional attributes.
 */
@SuppressWarnings("all")
public class SolrSortFieldModel extends ItemModel
{
	/**<i>Generated model type code constant.</i>*/
	public final static String _TYPECODE = "SolrSortField";
	
	/**<i>Generated relation code constant for relation <code>SolrSort2SolrSortFieldRel</code> defining source attribute <code>sort</code> in extension <code>commerceservices</code>.</i>*/
	public final static String _SOLRSORT2SOLRSORTFIELDREL = "SolrSort2SolrSortFieldRel";
	
	/** <i>Generated constant</i> - Attribute key of <code>SolrSortField.fieldName</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String FIELDNAME = "fieldName";
	
	/** <i>Generated constant</i> - Attribute key of <code>SolrSortField.ascending</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String ASCENDING = "ascending";
	
	/** <i>Generated constant</i> - Attribute key of <code>SolrSortField.sortPOS</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String SORTPOS = "sortPOS";
	
	/** <i>Generated constant</i> - Attribute key of <code>SolrSortField.sort</code> attribute defined at extension <code>commerceservices</code>. */
	public static final String SORT = "sort";
	
	
	/**
	 * <i>Generated constructor</i> - Default constructor for generic creation.
	 */
	public SolrSortFieldModel()
	{
		super();
	}
	
	/**
	 * <i>Generated constructor</i> - Default constructor for creation with existing context
	 * @param ctx the model context to be injected, must not be null
	 */
	public SolrSortFieldModel(final ItemModelContext ctx)
	{
		super(ctx);
	}
	
	/**
	 * <i>Generated constructor</i> - Constructor with all mandatory attributes.
	 * @deprecated Since 4.1.1 Please use the default constructor without parameters
	 * @param _ascending initial attribute declared by type <code>SolrSortField</code> at extension <code>commerceservices</code>
	 * @param _fieldName initial attribute declared by type <code>SolrSortField</code> at extension <code>commerceservices</code>
	 * @param _sort initial attribute declared by type <code>SolrSortField</code> at extension <code>commerceservices</code>
	 */
	@Deprecated
	public SolrSortFieldModel(final boolean _ascending, final String _fieldName, final SolrSortModel _sort)
	{
		super();
		setAscending(_ascending);
		setFieldName(_fieldName);
		setSort(_sort);
	}
	
	/**
	 * <i>Generated constructor</i> - for all mandatory and initial attributes.
	 * @deprecated Since 4.1.1 Please use the default constructor without parameters
	 * @param _ascending initial attribute declared by type <code>SolrSortField</code> at extension <code>commerceservices</code>
	 * @param _fieldName initial attribute declared by type <code>SolrSortField</code> at extension <code>commerceservices</code>
	 * @param _owner initial attribute declared by type <code>Item</code> at extension <code>core</code>
	 * @param _sort initial attribute declared by type <code>SolrSortField</code> at extension <code>commerceservices</code>
	 */
	@Deprecated
	public SolrSortFieldModel(final boolean _ascending, final String _fieldName, final ItemModel _owner, final SolrSortModel _sort)
	{
		super();
		setAscending(_ascending);
		setFieldName(_fieldName);
		setOwner(_owner);
		setSort(_sort);
	}
	
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SolrSortField.fieldName</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the fieldName - The field name to sort with.
	 */
	@Accessor(qualifier = "fieldName", type = Accessor.Type.GETTER)
	public String getFieldName()
	{
		return getPersistenceContext().getPropertyValue(FIELDNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SolrSortField.sort</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the sort
	 */
	@Accessor(qualifier = "sort", type = Accessor.Type.GETTER)
	public SolrSortModel getSort()
	{
		return getPersistenceContext().getPropertyValue(SORT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SolrSortField.ascending</code> attribute defined at extension <code>commerceservices</code>. 
	 * @return the ascending - Is this field sorted ascending.
	 */
	@Accessor(qualifier = "ascending", type = Accessor.Type.GETTER)
	public boolean isAscending()
	{
		return toPrimitive((Boolean)getPersistenceContext().getPropertyValue(ASCENDING));
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>SolrSortField.ascending</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the ascending - Is this field sorted ascending.
	 */
	@Accessor(qualifier = "ascending", type = Accessor.Type.SETTER)
	public void setAscending(final boolean value)
	{
		getPersistenceContext().setPropertyValue(ASCENDING, toObject(value));
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>SolrSortField.fieldName</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the fieldName - The field name to sort with.
	 */
	@Accessor(qualifier = "fieldName", type = Accessor.Type.SETTER)
	public void setFieldName(final String value)
	{
		getPersistenceContext().setPropertyValue(FIELDNAME, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>SolrSortField.sort</code> attribute defined at extension <code>commerceservices</code>. 
	 *  
	 * @param value the sort
	 */
	@Accessor(qualifier = "sort", type = Accessor.Type.SETTER)
	public void setSort(final SolrSortModel value)
	{
		getPersistenceContext().setPropertyValue(SORT, value);
	}
	
}
