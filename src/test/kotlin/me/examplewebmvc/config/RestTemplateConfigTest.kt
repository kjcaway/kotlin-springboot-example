package me.examplewebmvc.config

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.client.RestTemplate


@SpringBootTest
@ActiveProfiles("local")
class RestTemplateConfigTest{
    @Autowired
    private lateinit var restTemplate: RestTemplate

    private inline fun <reified T: Any> typeRef(): ParameterizedTypeReference<T> = object: ParameterizedTypeReference<T>(){}

    @Test
    fun restTemplateRequestTest(){
        val response = restTemplate.exchange("https://httpbin.org/get"
            , HttpMethod.GET
            , null
            , typeRef<Any>())

        println(response.body.toString())
    }
}