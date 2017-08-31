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
package com.hybris.backoffice.widgets.catalog;

import static com.hybris.backoffice.widgets.catalog.CatalogSelectorController.MODEL_SELECTED_DATA;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;

import java.util.Arrays;
import java.util.Set;

import org.fest.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;

import com.hybris.cockpitng.core.events.CockpitEvent;
import com.hybris.cockpitng.dataaccess.facades.object.ObjectCRUDHandler;
import com.hybris.cockpitng.labels.LabelService;
import com.hybris.cockpitng.testing.AbstractWidgetUnitTest;
import com.hybris.cockpitng.testing.annotation.DeclaredGlobalCockpitEvent;
import com.hybris.cockpitng.testing.annotation.DeclaredGlobalCockpitEvents;
import com.hybris.cockpitng.testing.annotation.DeclaredViewEvent;
import com.hybris.cockpitng.testing.annotation.ExtensibleWidget;
import com.hybris.cockpitng.testing.annotation.SocketsAreJsonSerializable;


@DeclaredViewEvent(componentID = CatalogSelectorController.POPUP_OPENER, eventName = Events.ON_CLICK)
@DeclaredGlobalCockpitEvents({
		@DeclaredGlobalCockpitEvent(eventName = ObjectCRUDHandler.OBJECT_UPDATED_EVENT, scope = CockpitEvent.SESSION),
		@DeclaredGlobalCockpitEvent(eventName = ObjectCRUDHandler.OBJECT_CREATED_EVENT, scope = CockpitEvent.SESSION),
		@DeclaredGlobalCockpitEvent(eventName = ObjectCRUDHandler.OBJECTS_DELETED_EVENT, scope = CockpitEvent.SESSION)
})
@ExtensibleWidget(level = ExtensibleWidget.ALL)
@SocketsAreJsonSerializable(false)
public class CatalogSelectorControllerTest extends AbstractWidgetUnitTest<CatalogSelectorController>
{
	@Mock
	private Tree tree;

	@Mock
	private Button popupOpener;

	@Mock
	private LabelService labelService;

	@Mock
	private Treeitem treeitem_catalog_1;
	@Mock
	private CatalogModel catalog_1;
	@Mock
	private Treechildren treechildren_catalog_1;

	@Mock
	private Treeitem treeitem_catalogVersion_1_1;
	@Mock
	private CatalogVersionModel catalogVersion_1_1;

	@Mock
	private Treeitem treeitem_catalogVersion_1_2;
	@Mock
	private CatalogVersionModel catalogVersion_1_2;

	@Mock
	private Treeitem treeitem_catalog_2;
	@Mock
	private CatalogModel catalog_2;
	@Mock
	private Treechildren treechildren_catalog_2;

	@Mock
	private Treeitem treeitem_catalogVersion_2_1;
	@Mock
	private CatalogVersionModel catalogVersion_2_1;

	@Mock
	private Treeitem treeitem_catalogVersion_2_2;
	@Mock
	private CatalogVersionModel catalogVersion_2_2;

	@Mock
	private Treeitem treeitem_all;

	@Mock
	private Treeitem treeitem_notSupported;

	private final Object notSupportedData = new Object();

	@InjectMocks
	private final CatalogSelectorController controller = new CatalogSelectorController();

	@Override
	protected CatalogSelectorController getWidgetController()
	{
		return controller;
	}

	@Before
	public void setUp()
	{
		when(treeitem_catalog_1.getValue()).thenReturn(catalog_1);
		when(treeitem_catalog_1.getTreechildren()).thenReturn(treechildren_catalog_1);
		when(treechildren_catalog_1.getItems()).thenReturn(Arrays.asList(treeitem_catalogVersion_1_1, treeitem_catalogVersion_1_2));

		when(treeitem_catalogVersion_1_1.getValue()).thenReturn(catalogVersion_1_1);
		when(treeitem_catalogVersion_1_1.getParentItem()).thenReturn(treeitem_catalog_1);
		when(treeitem_catalogVersion_1_2.getValue()).thenReturn(catalogVersion_1_2);
		when(treeitem_catalogVersion_1_2.getParentItem()).thenReturn(treeitem_catalog_1);

		when(treeitem_catalog_2.getValue()).thenReturn(catalog_2);
		when(treeitem_catalog_2.getTreechildren()).thenReturn(treechildren_catalog_2);
		when(treechildren_catalog_2.getItems()).thenReturn(Arrays.asList(treeitem_catalogVersion_2_1, treeitem_catalogVersion_2_2));

		when(treeitem_catalogVersion_2_1.getValue()).thenReturn(catalogVersion_2_1);
		when(treeitem_catalogVersion_2_1.getParentItem()).thenReturn(treeitem_catalog_2);
		when(treeitem_catalogVersion_2_2.getValue()).thenReturn(catalogVersion_2_2);
		when(treeitem_catalogVersion_2_2.getParentItem()).thenReturn(treeitem_catalog_2);

		when(treeitem_all.getValue()).thenReturn(null);
		when(tree.getItems())
				.thenReturn(Collections.set(treeitem_catalog_1, treeitem_catalogVersion_1_1, treeitem_catalogVersion_1_2,
						treeitem_catalog_2, treeitem_catalogVersion_2_1, treeitem_catalogVersion_2_2, treeitem_all));

		when(treeitem_notSupported.getValue()).thenReturn(notSupportedData);
	}

	@Test
	public void checkSelectionForAllItem() throws Exception
	{
		// when
		controller.renderItem(treeitem_all, null);

		// then
		verify(tree).selectItem(treeitem_all);
		final ArgumentCaptor<Set> selectedModels = ArgumentCaptor.forClass(Set.class);
		verify(widgetModel).setValue(eq(MODEL_SELECTED_DATA), selectedModels.capture());
		assertThat(selectedModels.getValue()).isEmpty();
	}

	@Test
	public void checkSelectCatalog()
	{
		// given
		when(tree.getSelectedItems()).thenReturn(Collections.set(treeitem_catalogVersion_1_1, treeitem_catalog_1));
		when(widgetModel.getValue(MODEL_SELECTED_DATA, Set.class)).thenReturn(Collections.set(catalogVersion_1_1));

		// when
		controller.renderItem(treeitem_catalog_1, catalog_1);

		// then
		verify(tree).addItemToSelection(treeitem_catalogVersion_1_1);
		verify(tree).addItemToSelection(treeitem_catalogVersion_1_2);
		final ArgumentCaptor<Set> selectedModels = ArgumentCaptor.forClass(Set.class);
		verify(widgetModel).setValue(eq(MODEL_SELECTED_DATA), selectedModels.capture());
		assertThat(selectedModels.getValue()).containsOnly(catalog_1, catalogVersion_1_1, catalogVersion_1_2);
	}

	@Test
	public void checkDeselectCatalog()
	{
		// given
		when(tree.getSelectedItems()).thenReturn(Collections.set(treeitem_catalogVersion_1_1));
		when(widgetModel.getValue(MODEL_SELECTED_DATA, Set.class)).thenReturn(Collections.set(catalog_1, catalogVersion_1_1));

		// when
		controller.renderItem(treeitem_catalog_1, catalog_1);

		// then
		verify(tree).removeItemFromSelection(treeitem_catalogVersion_1_1);
		verify(tree).removeItemFromSelection(treeitem_catalogVersion_1_2);
		final ArgumentCaptor<Set> selectedModels = ArgumentCaptor.forClass(Set.class);
		verify(widgetModel).setValue(eq(MODEL_SELECTED_DATA), selectedModels.capture());
		assertThat(selectedModels.getValue()).isEmpty();
	}

	@Test
	public void checkSelectCatalogVersion()
	{
		// given
		when(tree.getSelectedItems()).thenReturn(Collections.set(treeitem_catalogVersion_1_1));
		when(widgetModel.getValue(MODEL_SELECTED_DATA, Set.class)).thenReturn(Collections.set(catalog_2));

		// when
		controller.renderItem(treeitem_catalogVersion_1_1, catalogVersion_1_1);

		// then
		final ArgumentCaptor<Set> selectedModels = ArgumentCaptor.forClass(Set.class);
		verify(widgetModel).setValue(eq(MODEL_SELECTED_DATA), selectedModels.capture());
		assertThat(selectedModels.getValue()).containsOnly(catalog_2, catalogVersion_1_1);
	}

	@Test
	public void checkSelectCatalogVersionWhenAllChildrenSelected()
	{
		// given
		when(tree.getSelectedItems()).thenReturn(Collections.set(treeitem_catalogVersion_1_1, treeitem_catalogVersion_1_2));
		when(widgetModel.getValue(MODEL_SELECTED_DATA, Set.class)).thenReturn(Collections.set(catalogVersion_1_1));

		// when
		controller.renderItem(treeitem_catalogVersion_1_2, catalogVersion_1_2);

		// then
		verify(tree).addItemToSelection(treeitem_catalog_1);
		final ArgumentCaptor<Set> selectedModels = ArgumentCaptor.forClass(Set.class);
		verify(widgetModel).setValue(eq(MODEL_SELECTED_DATA), selectedModels.capture());
		assertThat(selectedModels.getValue()).containsOnly(catalogVersion_1_1, catalogVersion_1_2, catalog_1);
	}

	@Test
	public void checkDeselectCatalogVersion()
	{
		// given
		when(tree.getSelectedItems()).thenReturn(Collections.set(treeitem_catalogVersion_1_1));
		when(widgetModel.getValue(MODEL_SELECTED_DATA, Set.class))
				.thenReturn(Collections.set(catalog_1, catalogVersion_1_1, catalogVersion_1_2));

		// when
		controller.renderItem(treeitem_catalogVersion_1_2, catalogVersion_1_2);

		// then
		verify(tree).removeItemFromSelection(treeitem_catalog_1);
		final ArgumentCaptor<Set> selectedModels = ArgumentCaptor.forClass(Set.class);
		verify(widgetModel).setValue(eq(MODEL_SELECTED_DATA), selectedModels.capture());
		assertThat(selectedModels.getValue()).containsOnly(catalogVersion_1_1);
	}

	@Test
	public void checkDeselectLastCatalogSelectsAllFilter()
	{
		// given
		when(tree.getSelectedItems()).thenReturn(Collections.set());
		when(widgetModel.getValue(MODEL_SELECTED_DATA, Set.class))
				.thenReturn(Collections.set(catalog_1, catalogVersion_1_1, catalogVersion_1_2));

		// when
		controller.renderItem(treeitem_catalog_1, catalog_1);

		// then
		verify(tree).removeItemFromSelection(treeitem_catalogVersion_1_1);
		verify(tree).removeItemFromSelection(treeitem_catalogVersion_1_2);
		verify(tree).selectItem(treeitem_all);
		final ArgumentCaptor<Set> selectedModels = ArgumentCaptor.forClass(Set.class);
		verify(widgetModel).setValue(eq(MODEL_SELECTED_DATA), selectedModels.capture());
		assertThat(selectedModels.getValue()).isEmpty();
	}

	@Test
	public void checkDeselectLastCatalogVersionSelectsAllFilter()
	{
		// given
		when(tree.getSelectedItems()).thenReturn(Collections.set());
		when(widgetModel.getValue(MODEL_SELECTED_DATA, Set.class)).thenReturn(Collections.set(catalogVersion_1_1));

		// when
		controller.renderItem(treeitem_catalogVersion_1_1, catalogVersion_1_1);

		// then
		verify(tree).selectItem(treeitem_all);
		final ArgumentCaptor<Set> selectedModels = ArgumentCaptor.forClass(Set.class);
		verify(widgetModel).setValue(eq(MODEL_SELECTED_DATA), selectedModels.capture());
		assertThat(selectedModels.getValue()).isEmpty();
	}

	@Test
	public void checkSelectNotSupportedType()
	{
		// given
		when(tree.getSelectedItems()).thenReturn(Collections.set(treeitem_notSupported));
		when(widgetModel.getValue(MODEL_SELECTED_DATA, Set.class)).thenReturn(Collections.set(catalog_2));

		// when
		controller.renderItem(treeitem_notSupported, notSupportedData);

		// then
		verifyNoInteraction();
	}

	@Test
	public void checkDeselectNotSupportedType()
	{
		// given
		when(tree.getSelectedItems()).thenReturn(Collections.set());
		when(widgetModel.getValue(MODEL_SELECTED_DATA, Set.class)).thenReturn(Collections.set(catalog_2));

		// when
		controller.renderItem(treeitem_notSupported, notSupportedData);

		// then
		verifyNoInteraction();
	}

	private void verifyNoInteraction()
	{
		verify(tree, never()).addItemToSelection(any(Treeitem.class));
		verify(tree, never()).selectItem(any(Treeitem.class));
		verify(tree, never()).removeItemFromSelection(any(Treeitem.class));
		verify(widgetModel, never()).setValue(any(String.class), any(Object.class));
	}

	@Test
	public void checkSelectOnRenderForCatalog()
	{
		// given
		final Set<Object> selectedModels = Collections.set(catalog_1);

		// when
		controller.selectOnRender(treeitem_catalog_1, selectedModels);

		// then
		assertThat(selectedModels).containsOnly(catalog_1);
		verify(tree).addItemToSelection(treeitem_catalog_1);
	}

	@Test
	public void checkSelectOnRenderForCatalogVersion()
	{
		// given
		final Set<Object> selectedModels = Collections.set(catalog_1);

		// when
		controller.selectOnRender(treeitem_catalogVersion_1_2, selectedModels);

		// then
		assertThat(selectedModels).containsOnly(catalog_1, catalogVersion_1_2);
		verify(tree).addItemToSelection(treeitem_catalogVersion_1_2);
	}
}
