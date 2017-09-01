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
package de.hybris.platform.solrfacetsearch.converters.populator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.solrfacetsearch.config.EndpointURL;
import de.hybris.platform.solrfacetsearch.converters.populator.DefaultSolrServerConfigPopulator.PropertiesBasedSolrEndpointSource;
import de.hybris.platform.util.Config;

import java.util.List;
import java.util.UUID;

import org.junit.Test;


@IntegrationTest
public class PropertiesBasedSolrEndpointSourceIntegrationTest extends ServicelayerBaseTest
{
	@Test
	public void shouldUsePropertyNameBasedOnConfigName()
	{
		final PropertiesBasedSolrEndpointSource src = givenSource("expected");

		final String propertyName = src.getPropertyName();

		assertThat(propertyName).isEqualTo("solr.config.expected.urls");
	}

	@Test
	public void shouldThrowNPEWhenConfigNameIsNull()
	{
		try
		{
			givenSource(null);
			failBecauseExceptionWasNotThrown(NullPointerException.class);
		}
		catch (final NullPointerException expected)
		{
			assertThat(expected).hasNoCause().hasMessageContaining("solrConfigName");
		}
	}

	@Test
	public void shouldReturnEmptyURLListForBlankConfigName()
	{
		final PropertiesBasedSolrEndpointSource src = givenSource(" \n \t ");

		final boolean isThereAnyUrl = src.isThereAnyUrlConfigured();
		final List<EndpointURL> urls = src.getURLs();

		assertThat(isThereAnyUrl).isFalse();
		assertThat(urls).isNotNull().isEmpty();
	}

	@Test
	public void shouldReturnEmptyURLListForEmptyValue()
	{
		final String configName = testConfig("");
		final PropertiesBasedSolrEndpointSource src = givenSource(configName);

		final boolean isThereAnyUrl = src.isThereAnyUrlConfigured();
		final List<EndpointURL> urls = src.getURLs();

		assertThat(isThereAnyUrl).isFalse();
		assertThat(urls).isNotNull().isEmpty();
	}

	@Test
	public void shouldReturnSingleMasterURLForSingleValue()
	{
		final String configName = testConfig(" http://master:12345\t");
		final PropertiesBasedSolrEndpointSource src = givenSource(configName);

		final boolean isThereAnyUrl = src.isThereAnyUrlConfigured();
		final List<EndpointURL> urls = src.getURLs();

		assertThat(isThereAnyUrl).isTrue();
		assertThat(urls).isNotNull().hasSize(1).doesNotContainNull();

		final EndpointURL master = urls.get(0);
		assertThat(master.isMaster()).isTrue();
		assertThat(master.getUrl()).isEqualTo("http://master:12345");
		assertThat(master.getModifiedTime()).isNotNull();
	}

	@Test
	public void shouldReturnMultipleURLsAndFirstMustBeMarkedAsMaster()
	{
		final String configName = testConfig(" http://master:12345\t http://slave:1234      ");
		final PropertiesBasedSolrEndpointSource src = givenSource(configName);

		final boolean isThereAnyUrl = src.isThereAnyUrlConfigured();
		final List<EndpointURL> urls = src.getURLs();

		assertThat(isThereAnyUrl).isTrue();
		assertThat(urls).isNotNull().hasSize(2).doesNotContainNull();

		final EndpointURL master = urls.get(0);
		assertThat(master.isMaster()).isTrue();
		assertThat(master.getUrl()).isEqualTo("http://master:12345");
		assertThat(master.getModifiedTime()).isNotNull();

		final EndpointURL slave = urls.get(1);
		assertThat(slave.isMaster()).isFalse();
		assertThat(slave.getUrl()).isEqualTo("http://slave:1234");
		assertThat(slave.getModifiedTime()).isNotNull();

		assertThat(master.getModifiedTime()).isEqualTo(slave.getModifiedTime());
	}


	private String testConfig(final String value)
	{
		final String name = "TEST_CONFIG" + UUID.randomUUID();
		Config.setParameter("solr.config." + name + ".urls", value);
		return name;
	}

	private PropertiesBasedSolrEndpointSource givenSource(final String configName)
	{
		return new PropertiesBasedSolrEndpointSource(configName);
	}

}
