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
 *
 *
 */
package de.hybris.platform.assistedservicefacades.customer360;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.assistedservicefacades.customer360.impl.DefaultAdditionalInformationFrameworkFacade;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mockito.Mockito;

import static org.mockito.Matchers.any;

@UnitTest
public class AdditionalInformationFrameworkFacadeTest
{
    private DefaultAdditionalInformationFrameworkFacade facade;

    private static final String SECTION1_TITLE = "s1t";
    private static final String SECTION1_FRAGMENT1_TITLE = "s1f1t";
    private static final String SECTION1_FRAGMENT2_TITLE = "s1f2t";
    private static final String SECTION2_TITLE = "s2t";
    private static final String SECTION2_FRAGMENT1_TITLE = "s2f1t";

    private final Date data = new Date(); // just data object for Fragment populator
    private final String jspPath = "/jsp/path"; // just jsp path Fragment populator

    @Before
    public void setup()
    {
        facade = new DefaultAdditionalInformationFrameworkFacade();

        final Section section1 = new Section();
        section1.setId("section1");
        section1.setTitle(SECTION1_TITLE);

        final Fragment fragment1 = new Fragment();
        fragment1.setTitle(SECTION1_FRAGMENT1_TITLE);
        fragment1.setId("fragment1");

        final Fragment fragment2 = new Fragment();
        fragment2.setTitle(SECTION1_FRAGMENT2_TITLE);
        fragment2.setId("fragment2");

        final Section section2 = new Section();
        section2.setId("section2");
        section2.setTitle(SECTION2_TITLE);

        final Fragment fragment3 = new Fragment();
        fragment3.setTitle(SECTION2_FRAGMENT1_TITLE);
        fragment3.setId("fragment3");

        section1.setFragments(Arrays.asList(fragment1, fragment2));
        section2.setFragments(Arrays.asList(fragment3));

        facade.setSections(Arrays.asList(section1, section2));

        // only 1 data provider is defined
        final FragmentModelProvider mock = Mockito.mock(FragmentModelProvider.class);
        Mockito.when(mock.getModel(any(Map.class))).thenReturn(data);
        final Map<String, FragmentModelProvider> modelProvidersMap = new HashMap<String, FragmentModelProvider>()
        {{
            put("fragment3", mock);
        }};
        facade.setModelProvidersMap(modelProvidersMap);

        // only 1 jsp path provider is defined
        final Map<String, String> jspPathes = new HashMap<String, String>()
        {{
            put("fragment2", jspPath);
        }};
        facade.setJspProvidersMap(jspPathes);
    }

    @Test
    public void shouldGetSectionById()
    {
        final Section section1 = facade.getSection("section1");

        Assert.assertTrue(section1.getTitle().equals(SECTION1_TITLE));
        Assert.assertTrue(section1.getFragments().size() == 2);

        final Section section2 = facade.getSection("section2");

        Assert.assertTrue(section2.getTitle().equals(SECTION2_TITLE));
        Assert.assertTrue(section2.getFragments().size() == 1);
    }

    @Test
    public void shouldGetFragments()
    {
        final Fragment fragment1 = facade.getFragment("section1", "fragment1");
        Assert.assertTrue(fragment1.getTitle().equals(SECTION1_FRAGMENT1_TITLE));
        Assert.assertTrue(fragment1.getData() == null);

        final Fragment fragment3 = facade.getFragment("section2", "fragment3");
        Assert.assertTrue(fragment3.getTitle().equals(SECTION2_FRAGMENT1_TITLE));
        Assert.assertEquals(fragment3.getData(), data);

        final Fragment fragment2 = facade.getFragment("section1", "fragment2");
        Assert.assertTrue(fragment2.getTitle().equals(SECTION1_FRAGMENT2_TITLE));
        Assert.assertTrue(fragment2.getJspPath().equals(jspPath));
        Assert.assertTrue(fragment2.getData() == null);
    }
}