package me.examplewebmvc.api.book

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import me.examplewebmvc.api.book.model.BookEntity
import me.examplewebmvc.api.book.repository.BookRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Api(value = "BookController")
@RestController
@RequestMapping("/book")
class BookController(
    val bookRepository: BookRepository
) {
    @ApiOperation(value = "select books", notes = "select books")
    @GetMapping
    fun getBooks(
        @ApiParam(value = "book store id", example = "1")
        @RequestParam(required = false)
        bookStoreId: Long
    ): ResponseEntity<Any>{
        return try {
            val books = bookRepository.findAll()

            ResponseEntity.ok().body(hashMapOf(
                "data" to books,
                "count" to books.size
            ))
        } catch (e: Exception){
            ResponseEntity.badRequest().build()
        }
    }

    @ApiOperation(value = "insert book", notes = "insert book")
    @PostMapping
    fun setBook(
        @ApiParam(value = "{\"author\": \"\", \"name\": \"\"}")
        @RequestBody
        map: Map<String, Any>
    ): ResponseEntity<Any>{
        return try {
            val bookEntity = BookEntity()
            bookEntity.author = map["author"] as String
            bookEntity.name = map["name"] as String

            bookRepository.save(bookEntity)

            ResponseEntity.ok().build()
        } catch (e: Exception){
            ResponseEntity.badRequest().build()
        }
    }

    @ApiOperation(value = "modify book", notes = "modify book")
    @PutMapping("/{id}")
    fun modBook(
        @PathVariable("id")
        bookId: Long,
        @ApiParam(value = "{\"bookId\": 1, \"author\": \"\", \"name\": \"\"}")
        @RequestBody
        map: Map<String, Any>
    ): ResponseEntity<Any>{
        return try {
            var bookEntity = bookRepository.findByIdOrNull(bookId)

            if(bookEntity != null){
                map["author"]?.let { bookEntity.author = it as String}
                map["name"]?.let { bookEntity.name = it as String}
                bookRepository.save(bookEntity)
            }

            ResponseEntity.ok().build()

        } catch (e: Exception){
            ResponseEntity.badRequest().build()
        }
    }

    @ApiOperation(value = "delete book", notes = "delete book")
    @DeleteMapping("/{id}")
    fun delBook(
        @PathVariable("id")
        bookId: Long
    ): ResponseEntity<Any>{
        return try {
            var bookEntity = bookRepository.findByIdOrNull(bookId)

            if(bookEntity != null){
                bookRepository.delete(bookEntity)
            }

            ResponseEntity.ok().build()

        } catch (e: Exception){
            ResponseEntity.badRequest().build()
        }
    }
}