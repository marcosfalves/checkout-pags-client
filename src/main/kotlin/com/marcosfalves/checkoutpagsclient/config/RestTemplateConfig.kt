package com.marcosfalves.checkoutpagsclient.config

import org.apache.http.impl.client.CloseableHttpClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.DefaultUriBuilderFactory

@Configuration
@PropertySource("classpath:application.properties")
class RestTemplateConfig {

	@Autowired
	private lateinit var httpClient: CloseableHttpClient

	@Value("\${api.host.baseurl}")
	private lateinit var apiHost: String

	@Bean
	fun restTemplate(): RestTemplate {
		val restTemplate = RestTemplate(clientHttpRequestFactory())
		restTemplate.setUriTemplateHandler(DefaultUriBuilderFactory(apiHost))
		return restTemplate
	}

	@Bean
	@ConditionalOnMissingBean
	fun clientHttpRequestFactory(): HttpComponentsClientHttpRequestFactory {
		val clientHttpRequestFactory = HttpComponentsClientHttpRequestFactory()
		clientHttpRequestFactory.setHttpClient(httpClient)
		return clientHttpRequestFactory
	}
}