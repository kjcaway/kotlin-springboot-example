package me.examplewebmvc.interceptor

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse


class RestTemplateInterceptor : ClientHttpRequestInterceptor{

    val logger: Log = LogFactory.getLog(RestTemplateInterceptor::class.java)

    override fun intercept(
        request: HttpRequest,
        body: ByteArray,
        execution: ClientHttpRequestExecution
    ): ClientHttpResponse {
        val start = System.currentTimeMillis()
        val response = execution.execute(request, body)
        val end = System.currentTimeMillis()
        logger.info("RestTemplate request total time : ${end - start}ms")

        return response
    }
}