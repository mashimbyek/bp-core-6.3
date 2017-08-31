/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */

package com.hybris.datahub.core.facades.impl.converter;

import com.hybris.datahub.core.facades.ImportError;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.impex.model.ImpExMediaModel;
import de.hybris.platform.servicelayer.media.MediaService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;


@SuppressWarnings("javadoc")
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class UnresolvedLinesParserUnitTest
{
	@InjectMocks
	private final UnresolvedLinesParser parser = new UnresolvedLinesParser();
	@Mock
	private MediaService mediaService;
	private final int DEFAULT_ERROR_LIMIT = 10;

	@Before
	public void before()
	{
		Registry.setCurrentTenant(Registry.getSlaveJunitTenant());
		parser.setErrorLimit(DEFAULT_ERROR_LIMIT);
	}

	@Test(expected = IOException.class)
	public void testUnreadableUnresolvedLinesResultsException() throws IOException, ImpExException
	{
		parser.parse(toErrorThrowingImpExMediaModel());
	}

	private ImpExMediaModel toErrorThrowingImpExMediaModel()
	{
		Mockito.doThrow(IOException.class).when(mediaService).getStreamFromMedia(Mockito.any(ImpExMediaModel.class));
		return Mockito.mock(ImpExMediaModel.class);
	}

	@Test
	public void testEmptyUnresolvedLinesResultsInEmptyErrors() throws IOException, ImpExException
	{
		final Collection<ImportError> errors = parser.parse(emptyUnresolvedLines());

		Assert.assertTrue(errors.isEmpty());
	}

	@Test
	public void testCreatesAnErrorWhenUnresolvedLinesIsNotEmpty() throws IOException, ImpExException
	{
		final Collection<ImportError> errors = parser.parse(unresolvedLines());

		Assert.assertEquals(1, errors.size());
	}

	@Test(expected = ErrorLimitExceededException.class)
	public void testCreatesMaxErrorsWhenUnresolvedLinesExceedsMaxErrors() throws IOException, ImpExException
	{
		parser.setErrorLimit(2);
		final String[] threeUnresolvedLines =
				{
						"UPDATE Product;;code[unique=true];name[lang=en];Unit(code);catalogVersion(catalog(id),version [unique=true,allowNull=true]);description[lang=en];approvalStatus(code);ean;manufacturerName;numberContentUnits",
						",,,no existing item found for update;123;A4;New Product;pieces;;Experimental product by A Company;approved;0123123456789;A Company;1",
						",,,no existing item found for update;123;A4;New Product;pieces;;Experimental product by A Company;approved;0123123456789;A Company;1",
						",,,no existing item found for update;123;A4;New Product;pieces;;Experimental product by A Company;approved;0123123456789;A Company;1"};

		parser.parse(toImpExMediaModel(threeUnresolvedLines));
	}

	@Test
	public void testCorrectlyExtractsMessageFromTheUnresolvedLinesError() throws IOException, ImpExException
	{
		final ImportError err = parser.parse(unresolvedLines()).iterator().next();

		Assert.assertEquals("no existing item found for update", err.getMessage());
	}

	@Test
	public void testCorrectlyExtractsImpexLineFromTheUnresolvedLinesError() throws IOException, ImpExException
	{
		final ImportError err = parser.parse(unresolvedLines()).iterator().next();

		Assert.assertEquals(";123;A4;New Product;pieces;;Experimental product by A Company;approved;0123123456789;A Company;1",
				err.getScriptLine());
	}

	@Test
	public void testClassifiesParsedError() throws IOException, ImpExException
	{
		final ImportError err = parser.parse(unresolvedLines()).iterator().next();

		Assert.assertNotNull(err.getCode());
	}

	@Test
	public void testExtractsItemIdFromTheErrorMessage() throws IOException, ImpExException
	{
		final ImportError err = parser.parse(unresolvedLines()).iterator().next();

		Assert.assertEquals(new Long(123), err.getCanonicalItemId());
	}

	@Test
	public void testToErrorReturnsNull() throws IOException, ImpExException
	{
		final String[] unresolvedLines =
				{
						"INSERT_UPDATE TestItem;;integer[unique=true];",
						"Some error that doesn't make sense"};
		final ImportError err = parser.parse(toImpExMediaModel(unresolvedLines)).iterator().next();

		Assert.assertEquals("UNCLASSIFIED", err.getMessage());
		Assert.assertEquals("Some error that doesn't make sense", err.getScriptLine());
	}

	@Test
	public void testParsesAnErrorFromAMessageLineBeginningWithItemType() throws IOException, ImpExException
	{
		final String[] unresolvedLines =
				{
						"INSERT_UPDATE TestItem;;integer[unique=true];",
						"TestItem,8796387966977,,, column 3: could not resolve item for bad_unit, column 3: cannot resolve value 'bad_unit' for attribute 'unit';<ignore>5000001;<ignore>;bad_unit;<ignore>;<ignore>M1-bad_unit;<ignore>;<ignore>"};

		final ImportError err = parser.parse(toImpExMediaModel(unresolvedLines)).iterator().next();

		Assert.assertEquals("column 3: cannot resolve value 'bad_unit' for attribute 'unit'", err.getMessage());
	}

	@Test
	public void testParsesAMessageWithRemoveError() throws IOException, ImpExException
	{
		final String[] unresolvedLines =
				{
						"$catalogVersion=catalogversion(catalog(id[default=apparelProductCatalog]),version[default='Staged'])[unique=true,default=apparelProductCatalog:Staged]",
						"REMOVE Category;;supercategories(code,$catalogVersion);$catalogVersion;code[unique=true]",
						",,,could not remove item 8796160688270 due to [de.hybris.platform.category.interceptors.CategoryRemovalValidator@6427d506]:cannot remove [C12], since this category still has sub-categories;9;C2;;C12"};

		final ImportError err = parser.parse(toImpExMediaModel(unresolvedLines)).iterator().next();

		Assert.assertEquals(
				"could not remove item 8796160688270 due to [de.hybris.platform.category.interceptors.CategoryRemovalValidator@6427d506]:cannot remove [C12], since this category still has sub-categories",
				err.getMessage());
	}

	@Test
	public void testAcceptsForwardSlash() throws IOException, ImpExException
	{
		final String[] unresolvedLines =
				{
						"UPDATE Product;;code[unique=true];name[lang=en];Unit(code);catalogVersion(catalog(id),version [unique=true,allowNull=true]);description[lang=en];approvalStatus(code);ean;manufacturerName;numberContentUnits",
						",,,cannot create due to unresolved mandatory/initial columns, column 4: could not resolve item for 0016006610WE;123;A4;New Product;pieces;;Experimental product by A Company;approved;0123123456789;A Company;1",				};

		final ImportError err = parser.parse(toImpExMediaModel(unresolvedLines)).iterator().next();

		Assert.assertEquals("column 4: could not resolve item for 0016006610WE", err.getMessage());
		Assert.assertEquals(";123;A4;New Product;pieces;;Experimental product by A Company;approved;0123123456789;A Company;1", err.getScriptLine());
	}

	@Test
	public void testNoMessageReturnedFromImpex() throws IOException, ImpExException{
		final String[] unresolvedLines =
				{
						"UPDATE Product;;code[unique=true];name[lang=en];Unit(code);catalogVersion(catalog(id),version [unique=true,allowNull=true]);description[lang=en];approvalStatus(code);ean;manufacturerName;numberContentUnits",
						",,,,;123;A4;New Product;pieces;;Experimental product by A Company;approved;0123123456789;A Company;1",				};

		final ImportError err = parser.parse(toImpExMediaModel(unresolvedLines)).iterator().next();

		Assert.assertEquals("MISSING_IMPEX_ERROR_MESSAGE", err.getMessage());
		Assert.assertEquals(";123;A4;New Product;pieces;;Experimental product by A Company;approved;0123123456789;A Company;1", err.getScriptLine());
	}

	private ImpExMediaModel toImpExMediaModel(final String[] unresolvedLines)
	{
		final InputStream mediaStream = IOUtils.toInputStream(StringUtils.join(unresolvedLines, System.lineSeparator()));
		final ImpExMediaModel media = Mockito.mock(ImpExMediaModel.class);
		Mockito.doReturn(mediaStream).when(mediaService).getStreamFromMedia(media);
		return media;
	}

	private ImpExMediaModel emptyUnresolvedLines()
	{
		final String[] unresolvedLines = {""};
		return toImpExMediaModel(unresolvedLines);
	}

	private ImpExMediaModel unresolvedLines()
	{
		final String[] unresolvedLines =
				{
						"UPDATE Product;;code[unique=true];name[lang=en];Unit(code);catalogVersion(catalog(id),version [unique=true,allowNull=true]);description[lang=en];approvalStatus(code);ean;manufacturerName;numberContentUnits",
						",,,no existing item found for update;123;A4;New Product;pieces;;Experimental product by A Company;approved;0123123456789;A Company;1"};
		return toImpExMediaModel(unresolvedLines);
	}

	@Test
	public void testRemovesImpexDecorationsAddedByTheImportServiceFromTheScriptLine() throws IOException, ImpExException
	{
		final String[] unresolvedLines =
				{
						"$catalogVersion=catalogversion(catalog(id[default=apparelProductCatalog]),version[default='Staged'])[unique=true,default=apparelProductCatalog:Staged]",
						"REMOVE Category;;supercategories(code,$catalogVersion);$catalogVersion;code[unique=true]",
						",8796191358977,,, column 4: could not resolve item for piece;<ignore>123;<ignore>A2;<ignore>Another Product;piece;<ignore>;<ignore>Highly rated product by consumers;<ignore>approved;<ignore>1230123456789;<ignore>A Company;<ignore>3"};

		final ImportError err = parser.parse(toImpExMediaModel(unresolvedLines)).iterator().next();

		Assert.assertEquals(";123;A2;Another Product;piece;;Highly rated product by consumers;approved;1230123456789;A Company;3",
				err.getScriptLine());
	}
}
