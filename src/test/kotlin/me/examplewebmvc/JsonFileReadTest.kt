package me.examplewebmvc

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import me.examplewebmvc.api.book.service.BookService
import me.examplewebmvc.api.book.type.request.BookRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class JsonFileReadTest {
    @Autowired
    private lateinit var bookService: BookService
    private val mapper = jacksonObjectMapper()

    @Test
    fun jsonFileReadTest(){
        val jsonArr = mapper.readValue(File("src/test/resources/data.json"), Array<JsonData>::class.java)

        jsonArr.forEach {
            var inputBook = BookRequest()
            inputBook.bookId = it.bookId
            inputBook.bookstoreId = it.bookstoreId
            inputBook.name = it.name
            inputBook.author = it.author

            bookService.setBook(inputBook)
        }
    }
}