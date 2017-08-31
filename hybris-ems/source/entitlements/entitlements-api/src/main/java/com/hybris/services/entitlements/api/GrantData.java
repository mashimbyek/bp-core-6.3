/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 *
 */
package com.hybris.services.entitlements.api;

import com.hybris.commons.dto.impl.AbstractPropertyAwareDto;
import com.hybris.services.entitlements.api.status.Status;
import com.hybris.services.entitlements.condition.ConditionData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Grant DTO.
 */
@XmlRootElement
public class GrantData extends AbstractPropertyAwareDto
{
	private static final int INITIAL_NON_ZERO_ODD_NUMBER = 17;
	private static final int MULTIPLIER_NON_ZERO_ODD_NUMBER = 37;
	private String id;
	@NotEmpty
    private String userId;
	@NotEmpty
    private String grantSource;
	@NotEmpty
    private String grantSourceId;
	@Constraint("dateTimeValidator")
    private String grantTime;
	@NotEmpty
    private String entitlementType;
    private Status status = Status.ACTIVE;

	@Valid
	@Constraint("conditionDataListValidator")
	@XmlElement(name="conditions")
	private final List<ConditionData> conditions = new ArrayList<>();

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(final String userId)
	{
		this.userId = userId;
	}

	public String getGrantSource()
	{
		return grantSource;
	}

	public void setGrantSource(final String grantSource)
	{
		this.grantSource = grantSource;
	}

	public String getGrantSourceId()
	{
		return grantSourceId;
	}

	public void setGrantSourceId(final String grantSourceId)
	{
		this.grantSourceId = grantSourceId;
	}

	public String getGrantTime()
	{
		return grantTime;
	}

	public void setGrantTime(final String grantTime)
	{
		this.grantTime = grantTime;
	}

	public String getEntitlementType()
	{
		return entitlementType;
	}

	public void setEntitlementType(final String entitlementType)
	{
		this.entitlementType = entitlementType;
	}

    public List<ConditionData> getConditions()
	{
        return conditions;
    }

	/**
	 * Overwrite conditions.
	 *
	 * @param condition list of conditions
	 */
    public void setCondition(final ConditionData... condition)
	{
        conditions.clear();
        Collections.addAll(conditions, condition);
    }

	/**
	 * Overwrite conditions.
	 *
	 * @param conditions list of conditions
	 */
    public void setConditions(final Collection<ConditionData> conditions)
	{
        this.conditions.clear();
        if (conditions != null)
        {
            this.conditions.addAll(conditions);
        }
    }

	/**
	 * Append condition to existing ones.
	 *
	 * @param item new condition
	 */
	public void addCondition(final ConditionData item)
	{
		conditions.add(item);
	}

	public String getId()
	{
		return id;
	}

	public void setId(final String id)
	{
		this.id = id;
	}

	@Override
	public int hashCode()
	{
		return new HashCodeBuilder(INITIAL_NON_ZERO_ODD_NUMBER, MULTIPLIER_NON_ZERO_ODD_NUMBER).
//				appendSuper(super.hashCode()).
				append(userId).
				append(grantSource).
				append(grantSourceId).
				append(grantTime).
				append(entitlementType).
				append(conditions).
                append(status).
				toHashCode();
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		if (obj == this)
		{
			return true;
		}
		if (obj.getClass() != getClass())
		{
			return false;
		}
		final GrantData grantData = (GrantData) obj;
		return new EqualsBuilder()
//				.appendSuper(super.equals(obj))
				.append(id, grantData.id)
				.append(userId, grantData.userId)
				.append(grantSource, grantData.grantSource)
				.append(grantSourceId, grantData.grantSourceId)
				.append(grantTime, grantData.grantTime)
				.append(entitlementType, grantData.entitlementType)
				.append(conditions, grantData.conditions)
                .append(status, grantData.status)
				.isEquals();
	}

    /**
     * Returns grant status.
     * @return Status
     */
    public Status getStatus()
    {
        return status;
    }

    /**
     * Sets grant status.
     * @param statusArg Status
     */
    public void setStatus(final Status statusArg)
    {
        this.status = statusArg;
    }
}
