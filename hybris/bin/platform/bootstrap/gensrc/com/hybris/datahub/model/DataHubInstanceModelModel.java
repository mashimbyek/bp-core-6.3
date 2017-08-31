/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 25 Aug 2017 4:31:18 PM                      ---
 * ----------------------------------------------------------------
 */
package com.hybris.datahub.model;

import de.hybris.bootstrap.annotations.Accessor;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.model.ItemModelContext;

/**
 * Generated model class for type DataHubInstanceModel first defined at extension datahubbackoffice.
 * <p>
 * Data Hub instance the UI should point to.
 */
@SuppressWarnings("all")
public class DataHubInstanceModelModel extends ItemModel
{
	/**<i>Generated model type code constant.</i>*/
	public final static String _TYPECODE = "DataHubInstanceModel";
	
	/** <i>Generated constant</i> - Attribute key of <code>DataHubInstanceModel.instanceName</code> attribute defined at extension <code>datahubbackoffice</code>. */
	public static final String INSTANCENAME = "instanceName";
	
	/** <i>Generated constant</i> - Attribute key of <code>DataHubInstanceModel.InstanceLocation</code> attribute defined at extension <code>datahubbackoffice</code>. */
	public static final String INSTANCELOCATION = "InstanceLocation";
	
	
	/**
	 * <i>Generated constructor</i> - Default constructor for generic creation.
	 */
	public DataHubInstanceModelModel()
	{
		super();
	}
	
	/**
	 * <i>Generated constructor</i> - Default constructor for creation with existing context
	 * @param ctx the model context to be injected, must not be null
	 */
	public DataHubInstanceModelModel(final ItemModelContext ctx)
	{
		super(ctx);
	}
	
	/**
	 * <i>Generated constructor</i> - Constructor with all mandatory attributes.
	 * @deprecated Since 4.1.1 Please use the default constructor without parameters
	 * @param _InstanceLocation initial attribute declared by type <code>DataHubInstanceModel</code> at extension <code>datahubbackoffice</code>
	 * @param _instanceName initial attribute declared by type <code>DataHubInstanceModel</code> at extension <code>datahubbackoffice</code>
	 */
	@Deprecated
	public DataHubInstanceModelModel(final String _InstanceLocation, final String _instanceName)
	{
		super();
		setInstanceLocation(_InstanceLocation);
		setInstanceName(_instanceName);
	}
	
	/**
	 * <i>Generated constructor</i> - for all mandatory and initial attributes.
	 * @deprecated Since 4.1.1 Please use the default constructor without parameters
	 * @param _InstanceLocation initial attribute declared by type <code>DataHubInstanceModel</code> at extension <code>datahubbackoffice</code>
	 * @param _instanceName initial attribute declared by type <code>DataHubInstanceModel</code> at extension <code>datahubbackoffice</code>
	 * @param _owner initial attribute declared by type <code>Item</code> at extension <code>core</code>
	 */
	@Deprecated
	public DataHubInstanceModelModel(final String _InstanceLocation, final String _instanceName, final ItemModel _owner)
	{
		super();
		setInstanceLocation(_InstanceLocation);
		setInstanceName(_instanceName);
		setOwner(_owner);
	}
	
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DataHubInstanceModel.InstanceLocation</code> attribute defined at extension <code>datahubbackoffice</code>. 
	 * @return the InstanceLocation - The URL for this Data Hub instance
	 */
	@Accessor(qualifier = "InstanceLocation", type = Accessor.Type.GETTER)
	public String getInstanceLocation()
	{
		return getPersistenceContext().getPropertyValue(INSTANCELOCATION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DataHubInstanceModel.instanceName</code> attribute defined at extension <code>datahubbackoffice</code>. 
	 * @return the instanceName - The name for this Data Hub instance
	 */
	@Accessor(qualifier = "instanceName", type = Accessor.Type.GETTER)
	public String getInstanceName()
	{
		return getPersistenceContext().getPropertyValue(INSTANCENAME);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>DataHubInstanceModel.InstanceLocation</code> attribute defined at extension <code>datahubbackoffice</code>. 
	 *  
	 * @param value the InstanceLocation - The URL for this Data Hub instance
	 */
	@Accessor(qualifier = "InstanceLocation", type = Accessor.Type.SETTER)
	public void setInstanceLocation(final String value)
	{
		getPersistenceContext().setPropertyValue(INSTANCELOCATION, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>DataHubInstanceModel.instanceName</code> attribute defined at extension <code>datahubbackoffice</code>. 
	 *  
	 * @param value the instanceName - The name for this Data Hub instance
	 */
	@Accessor(qualifier = "instanceName", type = Accessor.Type.SETTER)
	public void setInstanceName(final String value)
	{
		getPersistenceContext().setPropertyValue(INSTANCENAME, value);
	}
	
}
