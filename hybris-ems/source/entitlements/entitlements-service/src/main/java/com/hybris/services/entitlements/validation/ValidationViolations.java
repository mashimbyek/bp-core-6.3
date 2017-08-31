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
package com.hybris.services.entitlements.validation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * Container of validation issues.
 */
public class ValidationViolations
{
	private List<Violation> errors = new ArrayList<>();
	private List<String> pathStack = new ArrayList<>();
	private String path = "";

	/**
	 * Add a field violation.
	 *
	 * @param field field name where an issue has been found
	 * @param value current value of the field
	 * @param message description of the issue
	 */
	public void add(final String field, final Object value, final String message)
	{
		String f = field;
		if (f == null)
		{
			f = "<null>";
		}
		if (!StringUtils.isBlank(f) && !StringUtils.isBlank(path) && !path.endsWith("."))
		{
			f = "." + f;
		}
		errors.add(new Violation(path + f, value, message));
	}

	/**
	 * Add a report of violation issues on object as a whole.
	 *
	 * @param message description of the issue
	 */
	public void add(final String message)
	{
		errors.add(new Violation(null, null, message));
	}

	/**
	 * Get field path relative to root object.
	 * <p>
	 *     Path is used to address invalid members in complex structures.
	 * </p>
	 *
	 * @return path
	 */
	public String getPath()
	{
		return path;
	}

	/**
	 * Add next level of hierarchy to the field path. I.e. store sub-field name.
	 * @param member field name
	 */
	public void pushNestedPath(final String member)
	{
		pathStack.add(path);
		if (!StringUtils.isBlank(path) && !path.startsWith(".") && !member.startsWith(".") &&
				!member.startsWith("["))
		{
			path += '.';
		}
		path += member;
	}

	/**
	 * Restore the field path to the state, in which it was before the latest pushNestedPath.
	 */
	public void popPath()
	{
		if (!pathStack.isEmpty())
		{
			path = pathStack.get(pathStack.size()-1);
			pathStack.remove(pathStack.size()-1);
		}
	}

	/**
	 * Check if there were any validation errors.
	 * @return true is any errors took place
	 */
	public boolean hasErrors()
	{
		return !errors.isEmpty();
	}

	@Override
	public String toString()
	{
		final StringBuilder builder = new StringBuilder();
        String separator = "";
		for (Violation error : errors)
		{
            builder.append(separator);
			if (error.getField() == null)
			{
				builder.append(getPath());
			}
			else
			{
				builder.append(error.getField());
				builder.append('=');
				if (error.getValue() == null)
				{
					builder.append("null");
				}
				else
				{
					builder.append(error.getValue().toString());
				}
			}
			builder.append(": ");
			builder.append(error.getMessage());
			separator = " ";
		}
		return builder.toString();
	}

	/**
	 * Batch add reports from another container.
	 *
	 * @param other container to add to this one
	 */
	public void addAll(final ValidationViolations other)
	{
		errors.addAll(other.errors);
	}
}
