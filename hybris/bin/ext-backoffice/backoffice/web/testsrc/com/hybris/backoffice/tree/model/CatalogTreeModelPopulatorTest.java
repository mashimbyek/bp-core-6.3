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
package com.hybris.backoffice.tree.model;

import static com.hybris.cockpitng.widgets.common.explorertree.ExplorerTreeController.DYNAMIC_NODE_SELECTION_CONTEXT;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.type.TypeService;
import de.hybris.platform.servicelayer.user.UserService;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hybris.backoffice.navigation.NavigationNode;
import com.hybris.cockpitng.core.context.CockpitContext;
import com.hybris.cockpitng.core.context.impl.DefaultCockpitContext;
import com.hybris.cockpitng.core.user.CockpitUserService;
import com.hybris.cockpitng.dataaccess.facades.permissions.PermissionFacade;
import com.hybris.cockpitng.dataaccess.facades.type.TypeFacade;
import com.hybris.cockpitng.labels.LabelService;
import com.hybris.cockpitng.testing.AbstractCockpitngUnitTest;
import com.hybris.cockpitng.tree.node.DynamicNode;
import com.hybris.cockpitng.tree.node.TypeNode;


public class CatalogTreeModelPopulatorTest extends AbstractCockpitngUnitTest<CatalogTreeModelPopulator>
{

	public static final String TYPE_A = "TypeA";
	public static final String TYPE_B = "TypeB";
	public static final String TYPE_UNKNOWN = "UnknownType";

	@InjectMocks
	private final CatalogTreeModelPopulator populator = new CatalogTreeModelPopulator();

	@Mock
	private TypeService typeService;

	@Mock
	private CockpitUserService cockpitUserService;

	@Mock
	private UserService userService;

	@Mock
	private CatalogService catalogService;

	@Mock
	private PermissionFacade permissionFacade;

	@Mock
	private TypeFacade typeFacade;

	@Mock
	private UserModel admin;

	@Mock
	private LabelService labelService;

	@Mock
	private CatalogVersionService catalogVersionService;

	@Mock
	private CatalogModel catalog;
	@Mock
	private CatalogVersionModel catalogVersion;
	@Mock
	private CategoryModel category1;
	@Mock
	private CategoryModel category2;
	@Mock
	private CategoryModel subCategory1;
	@Mock
	private CategoryModel subCategory2;

	@Override
	protected Class<? extends CatalogTreeModelPopulator> getWidgetType()
	{
		return CatalogTreeModelPopulator.class;
	}

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		when(typeService.getTypeForCode(Matchers.eq(TYPE_UNKNOWN))).thenThrow(UnknownIdentifierException.class);
		when(cockpitUserService.getCurrentUser()).thenReturn("admin");
		when(userService.getUserForUID("admin")).thenReturn(admin);
		when(Boolean.valueOf(userService.isAdmin(admin))).thenReturn(Boolean.TRUE);
		when(Boolean.valueOf(permissionFacade.canReadInstance(Matchers.any()))).thenReturn(Boolean.TRUE);
		getPopulator().setExcludedTypes(null);

		when(catalog.getCatalogVersions()).thenReturn(Sets.newHashSet(catalogVersion));
		when(catalogVersion.getRootCategories()).thenReturn(Arrays.asList(category1, category2));
		when(category1.getAllSubcategories()).thenReturn(Arrays.asList(subCategory1, subCategory2));
		when(catalogService.getAllCatalogs()).thenReturn(Lists.newArrayList(catalog));
		when(catalogVersionService.getAllCatalogVersions()).thenReturn(Collections.singletonList(catalogVersion));
	}

	@Test
	public void excludeUnknownTypes()
	{
		getPopulator().setExcludedTypes(Sets.newHashSet(TYPE_A, TYPE_UNKNOWN, TYPE_B));
		getPopulator().postConstruct();
		final Set<String> types = getPopulator().getExcludedTypes();
		assertThat(types).containsOnly(TYPE_A, TYPE_B);
	}

	@Test
	public void filterCatalogs()
	{
		final CatalogModel readable = mock(CatalogModel.class);
		final CatalogModel notReadable = mock(CatalogModel.class);

		getPopulator().setExcludedTypes(Sets.newHashSet(TYPE_A));

		when(Boolean.valueOf(typeService.isAssignableFrom(TYPE_A, TYPE_B))).thenReturn(Boolean.TRUE);
		when(catalogService.getAllCatalogs()).thenReturn(Lists.newArrayList(readable, notReadable));
		when(typeFacade.getType(notReadable)).thenReturn(TYPE_B);

		assertThat(getPopulator().getAllReadableCatalogs(new DefaultCockpitContext())).containsOnly(readable);

	}

	@Test(expected = IllegalArgumentException.class)
	public void unsupportedParentNode()
	{
		getPopulator().getChildren(new TypeNode(null, null));
	}

	@Test
	public void getChildrenForRootElement()
	{
		final CatalogModel catalog1 = mock(CatalogModel.class);
		final CatalogModel catalog2 = mock(CatalogModel.class);

		when(catalogService.getAllCatalogs()).thenReturn(Lists.newArrayList(catalog1, catalog2));

		assertThat(getPopulator().getChildren(new DynamicNode(null, null))).hasSize(2);
	}

	@Test
	public void getChildrenForRootElementWhenContextIsSet()
	{
		final CatalogModel selectedCatalog = mock(CatalogModel.class);
		final CatalogModel notSelectedCatalog = mock(CatalogModel.class);

		final DynamicNode rootNode = new DynamicNode(null, getPopulator());
		final CockpitContext context = new DefaultCockpitContext();
		context.setParameter(DYNAMIC_NODE_SELECTION_CONTEXT, Collections.singletonList(selectedCatalog));
		rootNode.setContext(context);

		when(catalogService.getAllCatalogs()).thenReturn(Lists.newArrayList(selectedCatalog, notSelectedCatalog));

		assertThat(getPopulator().getChildren(rootNode)).hasSize(1);
	}

	@Test
	public void getChildrenForCatalog()
	{
		final CatalogModel catalog = mock(CatalogModel.class);
		final CatalogVersionModel catalogVersion1 = mock(CatalogVersionModel.class);
		when(catalogVersion1.getVersion()).thenReturn("v1");
		final CatalogVersionModel catalogVersion2 = mock(CatalogVersionModel.class);
		when(catalogVersion2.getVersion()).thenReturn("v2");
		final CatalogVersionModel catalogVersion3 = mock(CatalogVersionModel.class);
		when(catalogVersion3.getVersion()).thenReturn("v3");
		when(catalog.getCatalogVersions()).thenReturn(Sets.newHashSet(catalogVersion1, catalogVersion2, catalogVersion3));

		final DynamicNode rootNode = new DynamicNode(null, getPopulator(), BigInteger.ONE);
		final CockpitContext context = new DefaultCockpitContext();
		context.setParameter(DYNAMIC_NODE_SELECTION_CONTEXT, Arrays.asList(catalogVersion1, catalogVersion2));
		rootNode.setContext(context);

		when(catalogService.getAllCatalogs()).thenReturn(Lists.newArrayList(catalog));
		when(catalogVersionService.getAllCatalogVersions())
				.thenReturn(Arrays.asList(catalogVersion1, catalogVersion2, catalogVersion3));

		final List<NavigationNode> catalogNodes = getPopulator().getChildren(rootNode);
		final List<NavigationNode> catalogVersionNodes = getPopulator().getChildren(catalogNodes.get(0));

		assertThat(catalogVersionNodes).hasSize(2);
		assertThat(catalogVersionNodes).onProperty("data").containsOnly(catalogVersion1, catalogVersion2);
	}

	@Test
	public void getChildrenOnlyToLimitedDepth()
	{
		final DynamicNode rootNode = new DynamicNode(null, getPopulator(), BigInteger.ZERO);

		final List<NavigationNode> catalogNodes = getPopulator().getChildren(rootNode);
		final List<NavigationNode> catalogVersionNodes = getPopulator().getChildren(catalogNodes.get(0));

		assertThat(catalogNodes).hasSize(1);
		assertThat(catalogVersionNodes).isEmpty();
		verifyZeroInteractions(catalogVersionService);
	}

	@Test
	public void getChildrenForCatalogVersion()
	{
		final DynamicNode rootNode = new DynamicNode(null, getPopulator(), BigInteger.valueOf(4));

		final List<NavigationNode> catalogNodes = getPopulator().getChildren(rootNode);
		final List<NavigationNode> catalogVersionNodes = getPopulator().getChildren(catalogNodes.get(0));
		final List<NavigationNode> categoryNodes = getPopulator().getChildren(catalogVersionNodes.get(0));
		final List<NavigationNode> subCategoryNodes = getPopulator().getChildren(categoryNodes.get(0));

		assertThat(categoryNodes).hasSize(2);
		assertThat(subCategoryNodes).hasSize(2);
	}

	@Test
	public void getSupportedCategoriesOnly()
	{
		when(category2.getAllSubcategories()).thenReturn(Arrays.asList(subCategory1, subCategory2));

		final DynamicNode rootNode = new DynamicNode(null, getPopulator(), BigInteger.valueOf(4));

		getPopulator().setExcludedTypes(Sets.newHashSet(TYPE_A));
		when(Boolean.valueOf(typeService.isAssignableFrom(TYPE_A, TYPE_A))).thenReturn(Boolean.TRUE);
		when(typeFacade.getType(category1)).thenReturn(TYPE_A);
		when(typeFacade.getType(subCategory1)).thenReturn(TYPE_A);

		final List<NavigationNode> catalogNodes = getPopulator().getChildren(rootNode);
		final List<NavigationNode> catalogVersionNodes = getPopulator().getChildren(catalogNodes.get(0));
		final List<NavigationNode> categoryNodes = getPopulator().getChildren(catalogVersionNodes.get(0));
		final List<NavigationNode> subCategoryNodes = getPopulator().getChildren(categoryNodes.get(0));

		assertThat(categoryNodes).hasSize(1);
		assertThat(categoryNodes).onProperty("data").containsOnly(category2);
		assertThat(subCategoryNodes).hasSize(1);
		assertThat(subCategoryNodes).onProperty("data").containsOnly(subCategory2);
	}

	@Test
	public void getChildrenForCatalogVersionShouldReturnEmptyCollectionWhenNoReadAccess()
	{
		final DynamicNode rootNode = new DynamicNode(null, getPopulator(), BigInteger.valueOf(4));

		final List<NavigationNode> catalogNodes = getPopulator().getChildren(rootNode);
		final List<NavigationNode> catalogVersionNodes = getPopulator().getChildren(catalogNodes.get(0));
		when(Boolean.valueOf(permissionFacade.canReadInstance(Matchers.any()))).thenReturn(Boolean.FALSE);
		final List<NavigationNode> categoryNodes = getPopulator().getChildren(catalogVersionNodes.get(0));

		assertThat(categoryNodes).isEmpty();
	}

	@Test
	public void getChildrenForCategoryShouldReturnEmptyCollectionWhenNoReadAccess()
	{
		final DynamicNode rootNode = new DynamicNode(null, getPopulator(), BigInteger.valueOf(4));

		final List<NavigationNode> catalogNodes = getPopulator().getChildren(rootNode);
		final List<NavigationNode> catalogVersionNodes = getPopulator().getChildren(catalogNodes.get(0));
		final List<NavigationNode> categoryNodes = getPopulator().getChildren(catalogVersionNodes.get(0));
		when(Boolean.valueOf(permissionFacade.canReadInstance(Matchers.any()))).thenReturn(Boolean.FALSE);
		final List<NavigationNode> subCategoryNodes = getPopulator().getChildren(categoryNodes.get(0));

		assertThat(categoryNodes).hasSize(2);
		assertThat(subCategoryNodes).isEmpty();
	}

	@Test
	public void createDynamicNodeId()
	{
		// given
		final NavigationNode node = Mockito.mock(NavigationNode.class);
		Mockito.when(node.getId()).thenReturn("id");

		final NavigationNode parent3 = Mockito.mock(NavigationNode.class);
		Mockito.when(parent3.getId()).thenReturn("par3");
		Mockito.when(node.getParent()).thenReturn(parent3);

		final NavigationNode parent2 = Mockito.mock(NavigationNode.class);
		Mockito.when(parent3.getParent()).thenReturn(parent2);

		final NavigationNode parent1 = Mockito.mock(NavigationNode.class);
		Mockito.when(parent1.getId()).thenReturn("par1");
		Mockito.when(parent2.getParent()).thenReturn(parent1);

		// when
		final String id = getPopulator().createDynamicNodeId(node, "suffix");

		// then
		Assertions.assertThat(id).isEqualTo("par1_par3_id_suffix");
	}

	public CatalogTreeModelPopulator getPopulator()
	{
		return populator;
	}
}
