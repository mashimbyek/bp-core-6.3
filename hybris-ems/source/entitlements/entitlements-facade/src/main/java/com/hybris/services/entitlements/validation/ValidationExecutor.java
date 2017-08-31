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

import com.hybris.services.entitlements.api.Constraint;
import com.hybris.services.entitlements.api.NotEmpty;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * Spring validation does not work as desired.
 * <p>
 *   Here is the explanation why we need our own validation framework:
 *   <a href="http://justsomejavaguy.blogspot.ru/2011/05/spring-validation-is-still-pretty-dumb.html">proof</a>.
 * </p>
 */
public class ValidationExecutor
{
	private static final Logger LOG = LoggerFactory.getLogger(ValidationExecutor.class);

	@Autowired
	private ApplicationContext context;
	private List<Validator> validators;

	@PostConstruct
	private void collectValidators()
	{
		final Map<String, Validator> beans = context.getBeansOfType(Validator.class);
		validators = new ArrayList<>(beans.size());
		validators.addAll(beans.values());
	}

	/**
	 * Validates given object by a validator mapped to object's class.
	 *
	 * @param object object to validate
	 * @return collection of validation issues
	 */
	public ValidationViolations validate(final Object object)
	{
		final ValidationViolations accumulator = new ValidationViolations();
		final Set<String> validatedObjects = new HashSet<>();
		try
		{
			validate(object, accumulator, validatedObjects);
		}
		catch (final IllegalAccessException | ClassCastException e)
		{
			accumulator.add(e.getMessage());
		}
		return accumulator;
	}

	void validate(final Object object, final ValidationViolations errors, final Set<String> validatedObjects)
			throws IllegalAccessException
	{
		if (validatedObjects.contains(errors.getPath()))
		{
			return;
		}
		validatedObjects.add(errors.getPath());
		if (object == null || treatAsACollection(object, errors, validatedObjects))
		{
			return;
		}
		applyValidators(object, errors);
		Class cls = object.getClass();
		do
		{
			validateClassFields(object, errors, validatedObjects, cls);
			cls = cls.getSuperclass();
		} while (cls != Object.class);
	}

	private void validateClassFields(final Object object, final ValidationViolations errors,
			final Set<String> validatedObjects, final Class cls)
	{
		for (final Field field : cls.getDeclaredFields())
		{
			if (!Modifier.isStatic(field.getModifiers()))
			{
				try
				{
					final Object value = getValue(object, cls, field);
					if (processAnnotations(field, value, errors) && value != null)
					{
						errors.pushNestedPath(field.getName());
						try
						{
							validate(value, errors, validatedObjects);
						}
						catch (final IllegalAccessException | ClassCastException e)
						{
							LOG.warn("Error validating " + errors.getPath(), e);
						}
						finally
						{
							errors.popPath();
						}
					}
				}
				catch (final IllegalAccessException e)
				{
					// If there is no getter, the field is not intended for external access, so no validation needed.
					LOG.info("Field " + errors.getPath() + "can not be accessed", e);
				}
			}
		}
	}

	/**
	 * This method is a workaround to get content of a private/protected field without changing accessibility.
	 * We just search for corresponding getter and execute it.
	 *
	 * @param object object to get value from
	 * @param cls class of the object
	 * @param field field to get value of
	 * @return field value
	 * @throws IllegalAccessException if the field has no public getters
	 */
	private Object getValue(final Object object, final Class cls, final Field field) throws IllegalAccessException
	{
		try
		{
			// Try naive approach first.
			return field.get(object);
		}
		catch (final IllegalAccessException fieldIsNotPublic)
		{
			// Ok, the field is not public. Invoke getter.
			try
			{
				return PropertyUtils.getProperty(object, field.getName());
			}
			catch (final NoSuchMethodException getterNotFound)
			{
				// Getter not found. Never mind, this fields is for internal use only.
				LOG.info("No method to get field {}", field.getName());
				throw fieldIsNotPublic;
			}
			catch (final InvocationTargetException getterInvocationError)
			{
				LOG.warn("Error invoking method {}#get{}", cls.getName(), field.getName());
				throw fieldIsNotPublic;
			}
		}
	}

	private boolean treatAsACollection(final Object object, final ValidationViolations errors, final Set<String> validatedObjects)
			throws IllegalAccessException
	{
		if (Map.class.isAssignableFrom(object.getClass()))
		{
			for (final Map.Entry<Object, Object> entry : ((Map<Object,Object>) object).entrySet())
			{
				errors.pushNestedPath("[" + entry.getKey() + ']');
				validate(entry.getValue(), errors, validatedObjects);
				errors.popPath();
			}
			return true;
		}
		if (Collection.class.isAssignableFrom(object.getClass()))
		{
			int index = 0;
			for (final Object item : (Collection) object)
			{
				errors.pushNestedPath("[" + index + ']');
				validate(item, errors, validatedObjects);
				errors.popPath();
				index += 1;
			}
			return true;
		}
		return false;
	}

	private void applyValidators(final Object object, final ValidationViolations errors)
	{
		for (final Validator validator : validators)
		{
			if (validator.supportsClass(object.getClass()))
			{
				validator.validate(object, errors);
			}
		}
	}

	private boolean processAnnotations(final Field field, final Object object, final ValidationViolations errors)
			throws IllegalAccessException
	{
		boolean res = false;
		for (final Annotation annotation : field.getAnnotations())
		{
			if (annotation.annotationType() == Valid.class)
			{
				res = true;
			}
			else if (annotation.annotationType() == NotNull.class)
			{
				if (object == null)
				{
					// TODO: Support message templates
					errors.add(field.getName(), null, "May not be null");
				}
			}
			else if (annotation.annotationType() == Constraint.class)
			{
				errors.pushNestedPath(field.getName());
				try
				{
					final Validator customValidator = context.getBean(((Constraint) annotation).value(), Validator.class);
					customValidator.validate(object, errors);
				}
				finally
				{
					errors.popPath();
				}
			} else if (annotation.annotationType() == NotEmpty.class)
			{
				if (object == null || StringUtils.isEmpty(object.toString()))
				{
					errors.add(field.getName(), object, "Must not be empty");
				}
			}
			// AssertTrue
			// AssertFalse
			// DecimalMax
			// DecimalMin
			// Digits
			// Future
			// Max
			// Min
			// Null
			// Past
			// Pattern
			// Size
		}
		return res;
	}
}
