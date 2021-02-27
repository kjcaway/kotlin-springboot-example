package me.examplewebmvc.api.external.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.client.RestTemplate

@SpringBootTest
// @ExtendWith(MockitoExtension::class) // junit4 에선 써야되나?
@ActiveProfiles("local")
class ExtRequestServiceImplTest{

    @Autowired
    private lateinit var extRequestService: ExtRequestService
    @MockBean
    private lateinit var restTemplate: RestTemplate
    private inline fun <reified T: Any> typeRef(): ParameterizedTypeReference<T> = object: ParameterizedTypeReference<T>(){}

    @Test
    fun getExtResourceTest(){
        `when`(restTemplate.exchange(
            Mockito.anyString(),
            Mockito.any(HttpMethod::class.java),
            Mockito.eq(null),
            Mockito.eq(typeRef<Any>())
        )).thenReturn(ResponseEntity.ok("mock test"))

        val response = extRequestService.getExtResource()
        println(response.toString())

        assertNotNull(response)
        assertEquals(response, mapOf("result" to "SUCCESS", "data" to "mock test"))
    }
}