package me.examplewebmvc.api.book

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import me.examplewebmvc.api.book.entity.BookEntity
import me.examplewebmvc.api.book.repository.BookRepository
import me.examplewebmvc.api.book.service.BookService
import me.examplewebmvc.api.book.type.Book
import me.examplewebmvc.exception.AuthorException
import me.examplewebmvc.exception.EmptyBookException
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@Api(value = "BookController")
@RestController
@RequestMapping("/book")
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
            ResponseEntity.ok().body(hashMapOf(
                "data" to books,
                "count" to books.size
            ))
        } catch (e: Exception){
            logger.error(e.localizedMessage, e)
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error")
        }
    }

    @ApiOperation(value = "insert book", notes = "insert book")
    @PostMapping
    fun setBook(
        @ApiParam(value = "{\"author\": \"\", \"name\": \"\"}")
        @RequestBody
        book: Book
    ): ResponseEntity<Any>{
        return try {
            bookService.setBook(book)

            ResponseEntity.ok().build()
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
        book: Book
    ): ResponseEntity<Any>{
        return try {
            bookService.modBook(book)

            ResponseEntity.ok().build()
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

            ResponseEntity.ok().build()
        } catch (e: EmptyBookException){
            logger.error(e.localizedMessage, e)
            throw e
        } catch (e: Exception){
            logger.error(e.localizedMessage, e)
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error")
        }
    }
}