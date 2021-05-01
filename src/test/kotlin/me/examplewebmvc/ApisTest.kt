package me.examplewebmvc

import me.examplewebmvc.util.JsonUtil
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.hamcrest.Matchers.`is`

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.MethodName::class)
@DisplayName("APIs TEST")
class ApisTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    @DisplayName("테스트 01. book 모두 조회(bookstoreId = null)")
    fun _01_BookController_Select_All(){
        val result: ResultActions = mockMvc.perform(
                get("/api/book")
                        .accept(MediaType.APPLICATION_JSON)
        )

        result.andDo(print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.code", `is`("SUCCESS")))
                .andExpect(jsonPath("$.result").isArray)
                .andExpect(jsonPath("$.result[0].bookId").isNumber)
                .andExpect(jsonPath("$.result[0].name").isString)
                .andExpect(jsonPath("$.result[0].author").isString)
    }

    @Test
    @DisplayName("테스트 02. book 모두 조회(bookstoreId = 1)")
    fun _02_BookController_Select_BookstoreEquals1(){
        val result: ResultActions = mockMvc.perform(
                get("/api/book?bookStoreId=1")
                        .accept(MediaType.APPLICATION_JSON)
        )

        result.andDo(print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.code", `is`("SUCCESS")))
                .andExpect(jsonPath("$.result").isArray)
                .andExpect(jsonPath("$.result[0].bookId").isNumber)
                .andExpect(jsonPath("$.result[0].name").isString)
                .andExpect(jsonPath("$.result[0].author").isString)
    }

    @Test
    @DisplayName("테스트 03. book 조회(bookId = 1)")
    fun _03_BookController_SelectOne_BookEquals1(){
        val result: ResultActions = mockMvc.perform(
                get("/api/book/1")
                        .accept(MediaType.APPLICATION_JSON)
        )

        result.andDo(print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.code", `is`("SUCCESS")))
                .andExpect(jsonPath("$.result").isMap)
                .andExpect(jsonPath("$.result.bookId").isNumber)
                .andExpect(jsonPath("$.result.name").isString)
                .andExpect(jsonPath("$.result.author").isString)
    }

    @Test
    @DisplayName("테스트 04. book 조회에러(bookId = 0)")
    fun _04_BookController_SelectOne_EmptyBookException(){
        val result: ResultActions = mockMvc.perform(
                get("/api/book/0")
                        .accept(MediaType.APPLICATION_JSON)
        )

        result.andDo(print())
                .andExpect(status().isNotFound)
                .andExpect(jsonPath("$.code", `is`("FAIL")))
                .andExpect(jsonPath("$.message", `is`("there's empty matched book.")))
    }

    @Test
    @DisplayName("테스트 05. book 입력")
    fun _05_BookController_InsertBook(){
        val result: ResultActions = mockMvc.perform(
                post("/api/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.convertToJsonStr(
                                hashMapOf<String, Any>(
                                        "bookstoreId" to 1,
                                        "name" to "Test Book",
                                        "author" to "Tester"
                                )
                        ))
        )

        result.andDo(print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.code", `is`("SUCCESS")))
                .andExpect(jsonPath("$.result").isMap)
                .andExpect(jsonPath("$.result.bookId").isNumber)
                .andExpect(jsonPath("$.result.name").isString)
                .andExpect(jsonPath("$.result.author").isString)
    }

    @Test
    @DisplayName("테스트 06. book 입력 에러(author 길이)")
    fun _06_BookController_InsertBook_AuthorException(){
        val result: ResultActions = mockMvc.perform(
                post("/api/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.convertToJsonStr(
                                hashMapOf<String, Any>(
                                        "bookstoreId" to 1,
                                        "name" to "Test Book(AuthorException)",
                                        "author" to "Te"
                                )
                        ))
        )

        result.andDo(print())
                .andExpect(status().isBadRequest)
    }
}