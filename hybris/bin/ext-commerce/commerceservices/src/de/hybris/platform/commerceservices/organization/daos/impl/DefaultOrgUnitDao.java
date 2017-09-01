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
package de.hybris.platform.commerceservices.organization.daos.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commerceservices.model.OrgUnitModel;
import de.hybris.platform.commerceservices.organization.daos.OrgUnitDao;
import de.hybris.platform.commerceservices.search.dao.impl.DefaultPagedGenericDao;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.enums.QuoteState;
import de.hybris.platform.core.model.order.QuoteModel;
import de.hybris.platform.core.model.security.PrincipalModel;
import de.hybris.platform.core.model.user.EmployeeModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Set;

import org.springframework.beans.factory.annotation.Required;


/**
 * Default implementation of the {@link OrgUnitDao} interface extending {@link DefaultPagedGenericDao}.
 */
public class DefaultOrgUnitDao<T extends OrgUnitModel> extends DefaultPagedGenericDao<T> implements OrgUnitDao<T>
{
	/**
	 * Sub-query that makes sure only the latest version for each distinct quote code is returned.
	 */
	protected static final String QUOTE_DISTINCT_CODE_MAX_VERSION_QUERY = " AND EXISTS ( SELECT CODE_MAX_VERSION.code, CODE_MAX_VERSION.maxVersion FROM"
			+ " ({{ SELECT {code} as code, max({version}) as maxVersion FROM {Quote} WHERE {state} IN (?states) GROUP BY {code} }})"
			+ " CODE_MAX_VERSION WHERE {quote:code} = CODE_MAX_VERSION.code AND {quote:version} = CODE_MAX_VERSION.maxVersion )";

	/**
	 * Joining the quote table with other tables (e.g. PrincipipalGroupRelation), so that all quotes associated to the
	 * employee's organizational units can be resolved.
	 */
	protected static final String QUOTE_TO_EMPLOYEE_JOINS = " JOIN Customer as cust ON {quote:user} = {cust:pk}"
			+ " JOIN PrincipalGroupRelation as rel1 ON {rel1:source} = {cust:pk}"
			+ " JOIN OrgUnit as unit ON {unit:pk} = {rel1:target}"
			+ " JOIN PrincipalGroupRelation as rel2 ON {rel2:target} = {unit:pk}"
			+ " JOIN Employee as empl ON {empl:pk} = {rel2:source}";

	/**
	 * Query template to retrieve quotes associated to an employee. Takes a JOIN statement used to define the relation
	 * between quote and employee as a first argument and an additional clause to narrow down the result set as a second
	 * argument.
	 */
	protected static final String QUOTE_FOR_EMPLOYEE_QUERY_FORMAT = "SELECT {quote:pk} FROM { Quote as quote %s } WHERE {empl:pk} = ?employee AND {quote:state} IN (?states) %s";

	public DefaultOrgUnitDao(final String typeCode)
	{
		super(typeCode);
	}

	private ModelService modelService;

	@Override
	public <U extends PrincipalModel> SearchPageData<U> findMembersOfType(final OrgUnitModel unit, final Class<U> memberType,
			final PageableData pageableData)
	{
		validateParameterNotNullStandardMessage("unit", unit);
		validateParameterNotNullStandardMessage("memberType", memberType);
		validateParameterNotNullStandardMessage("pageableData", pageableData);

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT {m:pk}	");
		sql.append("FROM	");
		sql.append("{	");
		sql.append("OrgUnit as unit ");
		sql.append("	JOIN PrincipalGroupRelation as unit_rel ");
		sql.append("	ON   {unit_rel:target} = {unit:pk} ");
		sql.append("	JOIN ").append(getModelService().getModelType(memberType)).append(" as m ");
		sql.append("	ON   {m:pk} = {unit_rel:source} ");
		sql.append("} ");
		sql.append("WHERE {unit:pk} = ?unit");

		final FlexibleSearchQuery query = new FlexibleSearchQuery(sql.toString());
		query.getQueryParameters().put("unit", unit);
		query.setNeedTotal(true);
		query.setCount(pageableData.getPageSize());
		query.setStart(pageableData.getCurrentPage() * pageableData.getPageSize());

		return getPagedFlexibleSearchService().search(query, pageableData);
	}

	@Override
	public SearchPageData<QuoteModel> findQuotesForEmployee(final EmployeeModel employee, final Set<QuoteState> states,
			final PageableData pageableData)
	{
		validateParameterNotNullStandardMessage("employee", employee);
		validateParameterNotNullStandardMessage("states", states);
		validateParameterNotNullStandardMessage("pageableData", pageableData);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(getQuoteForEmployeeQuery());
		query.getQueryParameters().put("employee", employee);
		query.getQueryParameters().put("states", states);
		query.setNeedTotal(true);
		query.setCount(pageableData.getPageSize());
		query.setStart(pageableData.getCurrentPage() * pageableData.getPageSize());

		return getPagedFlexibleSearchService().search(query, pageableData);
	}

	protected String getQuoteForEmployeeQuery()
	{
		return String.format(QUOTE_FOR_EMPLOYEE_QUERY_FORMAT, QUOTE_TO_EMPLOYEE_JOINS, QUOTE_DISTINCT_CODE_MAX_VERSION_QUERY);
	}

	protected ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

}
