package me.examplewebmvc.config

import com.fasterxml.jackson.core.type.TypeReference
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.client.RestTemplate
import java.util.HashMap

import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod


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