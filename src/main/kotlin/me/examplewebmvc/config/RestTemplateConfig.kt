package me.examplewebmvc.config

import me.examplewebmvc.interceptor.RestTemplateInterceptor
import org.apache.http.impl.client.HttpClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.BufferingClientHttpRequestFactory
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

@Configuration
class RestTemplateConfig {

    @Bean
    fun restTemplate(): RestTemplate{
        val factory = HttpComponentsClientHttpRequestFactory()
        val client = HttpClientBuilder.create()
            .setMaxConnTotal(50)
            .setMaxConnPerRoute(20)
            .build()

        factory.httpClient = client
        factory.setConnectTimeout(3000)
        factory.setReadTimeout(5000)

        val restTemplate = RestTemplate(BufferingClientHttpRequestFactory(factory))
        restTemplate.interceptors = listOf(RestTemplateInterceptor())

        return restTemplate
    }
}