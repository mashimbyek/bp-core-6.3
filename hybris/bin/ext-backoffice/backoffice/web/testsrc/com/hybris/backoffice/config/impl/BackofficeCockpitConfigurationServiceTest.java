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
package com.hybris.backoffice.config.impl;

import com.hybris.cockpitng.core.config.CockpitConfigurationException;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.user.EmployeeModel;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.MockSessionService;
import de.hybris.platform.servicelayer.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;


public class BackofficeCockpitConfigurationServiceTest {

    @InjectMocks
    private final BackofficeCockpitConfigurationService backofficeCockpitConfigurationService = new BackofficeCockpitConfigurationService();

    @Mock
    private MediaService mediaService;

    @Mock
    private ModelService modelService;

    @Mock
    private CatalogVersionService catalogVersionService;

    @Mock
    private UserService userService;

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private MockSessionService sessionService;

    @Mock
    private EmployeeModel admin;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        backofficeCockpitConfigurationService.setSessionService(sessionService);
        when(userService.getAdminUser()).thenReturn(admin);
    }

    @Test
    public void getConfigFileInputStreamTest() throws FileNotFoundException, CockpitConfigurationException
    {
        final InputStream inputStream = Mockito.mock(InputStream.class);
        final MediaModel media = Mockito.mock(MediaModel.class);
        media.setCode(Mockito.anyString());
        when(mediaService.getMedia(Mockito.anyString())).thenReturn(media);
        when(backofficeCockpitConfigurationService.getCockpitNGConfig()).thenReturn(media);
        when(mediaService.getStreamFromMedia(media)).thenReturn(inputStream);
        assertNotNull(backofficeCockpitConfigurationService.getConfigFileInputStream());
    }

    @Test
    public void getCockpitNGConfigTest() throws CockpitConfigurationException
    {
        final MediaModel media = Mockito.mock(MediaModel.class);
        media.setCode(Mockito.anyString());
        when(mediaService.getMedia(Mockito.anyString())).thenReturn(media);
        assertNotNull(backofficeCockpitConfigurationService.getCockpitNGConfig());
        assertEquals(media, backofficeCockpitConfigurationService.getCockpitNGConfig());
    }

    @Test
    public void createConfigFileTest()
    {
        final CatalogUnawareMediaModel media = Mockito.mock(CatalogUnawareMediaModel.class);
        when(modelService.create(CatalogUnawareMediaModel.class)).thenReturn(media);
        assertNotNull(backofficeCockpitConfigurationService.createConfigFile());
        assertEquals(media, backofficeCockpitConfigurationService.createConfigFile());
    }
}
