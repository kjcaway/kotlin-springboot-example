package me.examplewebmvc.api.book

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import me.examplewebmvc.annotation.AuthorDefaultSet
import me.examplewebmvc.basic.response.BasicResponse
import me.examplewebmvc.api.book.service.BookService
import me.examplewebmvc.api.book.type.request.BookRequest
import me.examplewebmvc.exception.AuthorException
import me.examplewebmvc.exception.EmptyBookException
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@Api(value = "BookController")
@RestController
@RequestMapping("\${api.base.path}/book")
class BookController(
    val bookService: BookService
) {
    val logger: Log = LogFactory.getLog(BookController::class.java)

    @ApiOperation(value = "select books", notes = "select books")
    @GetMapping
    fun getBooks(
        @ApiParam(value = "book store id", example = "1")
        @RequestParam(required = false)
        bookStoreId: Long?
    ): ResponseEntity<Any>{
        return try {
            val books = bookService.getBooks(bookStoreId)

            ResponseEntity(BasicResponse("SUCCESS", books), HttpStatus.OK)
        } catch (e: Exception){
            logger.error(e.localizedMessage, e)
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error")
        }
    }

    @ApiOperation(value = "select book", notes = "select book")
    @GetMapping("/{id}")
    fun getBook(
        @ApiParam(value = "book id", example = "1")
        @PathVariable
        id: Long
    ): ResponseEntity<Any>{
        return try {
            val book = bookService.getBook(id)

            ResponseEntity(BasicResponse("SUCCESS", book), HttpStatus.OK)
        } catch (e: EmptyBookException){
            logger.error(e.localizedMessage, e)
            throw e
        } catch (e: Exception){
            logger.error(e.localizedMessage, e)
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error")
        }
    }

    @AuthorDefaultSet
    @ApiOperation(value = "insert book", notes = "insert book")
    @PostMapping
    fun setBook(
        @ApiParam(value = "{\"author\": \"\", \"name\": \"\"}")
        @RequestBody
        book: BookRequest
    ): ResponseEntity<Any>{
        return try {
            val book = bookService.setBook(book)

            ResponseEntity(BasicResponse("SUCCESS", book), HttpStatus.OK)
        } catch (e: AuthorException){
            logger.error(e.localizedMessage, e)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "${e.author} is too short.")
        } catch (e: Exception){
            logger.error(e.localizedMessage, e)
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error")
        }
    }

    @ApiOperation(value = "modify book", notes = "modify book")
    @PutMapping
    fun modBook(
        @ApiParam(value = "{\"bookId\": 1, \"authorK\": \"\", \"name\": \"\"}")
        @RequestBody
        book: BookRequest
    ): ResponseEntity<Any>{
        return try {
            bookService.modBook(book)

            ResponseEntity(HttpStatus.OK)
        } catch (e: EmptyBookException){
            // Use ControlAdvice and Exception Handler
            logger.error(e.localizedMessage, e)
            throw e
        } catch (e: AuthorException){
            // Use ResponseStatusException (new in spring 5)
            logger.error(e.localizedMessage, e)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "${e.author} is too short.")
        } catch (e: Exception){
            logger.error(e.localizedMessage, e)
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error")
        }
    }

    @ApiOperation(value = "delete book", notes = "delete book")
    @DeleteMapping("/{id}")
    fun delBook(
        @PathVariable("id")
        bookId: Long
    ): ResponseEntity<Any>{
        return try {
            bookService.delBook(bookId)

            ResponseEntity(HttpStatus.OK)
        } catch (e: EmptyBookException){
            logger.error(e.localizedMessage, e)
            throw e
        } catch (e: Exception){
            logger.error(e.localizedMessage, e)
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error")
        }
    }
}