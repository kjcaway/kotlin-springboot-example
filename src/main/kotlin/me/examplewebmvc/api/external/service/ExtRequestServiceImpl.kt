package me.examplewebmvc.api.external.service

import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ExtRequestServiceImpl(
    val restTemplate: RestTemplate
): ExtRequestService {
    private inline fun <reified T: Any> typeRef(): ParameterizedTypeReference<T> = object: ParameterizedTypeReference<T>(){}

    override fun getExtResource(): Any {
        val response = restTemplate.exchange(
            "https://httpbin.org/get", HttpMethod.GET, null, typeRef<Any>()
        )

        return mapOf(
            "result" to "SUCCESS",
            "data" to response.body!!
        )
    }
}