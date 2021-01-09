package me.examplewebmvc.api.book

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import me.examplewebmvc.api.book.repository.BookRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Api(value = "BookController")
@RestController
@RequestMapping("/book")
class BookController(
    val bookRepository: BookRepository
) {
    @ApiOperation(value = "book", notes = "select books")
    @GetMapping
    fun books(
        @ApiParam(value = "book store id", example = "1")
        @RequestParam(required = false)
        bookStoreId: Long
    ): ResponseEntity<Any>{
        val books = bookRepository.findAll()

        return ResponseEntity.ok().body(hashMapOf(
            "data" to books,
            "count" to books.size
        ))
    }



}