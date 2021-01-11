package me.examplewebmvc.api.book.service

import me.examplewebmvc.api.book.repository.BookRepository
import me.examplewebmvc.api.book.type.Book
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.platform.commons.logging.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookServiceImplTest {
    @Autowired
    private lateinit var bookService: BookService
    @Autowired
    private lateinit var bookRepository: BookRepository

    @Test
    fun setBook(){
        var inputBook = Book()
        inputBook.name = "HI, Yolo"
        inputBook.author = "Parker Davison"
        inputBook.bookstoreId = 1

        bookService.setBook(inputBook)
    }

    @Test
    fun getBookByName() {
        var books = bookRepository.findByName("테스트")
        assertNotNull(books, "books is not null")
    }

    @Test
    fun getBookByAuthor(){
        var books = bookRepository.findByAuthor("테스트")
        assertNotNull(books, "books is not null")
    }

    @Test
    fun testRepository(){
        var optionalBookEntity = bookRepository.findById(12)
        optionalBookEntity.ifPresent(System.out::println)

        var books = bookRepository.findAll()
        books.forEach(System.out::println)

        var books2 = bookRepository.findByBookstore_BookstoreId(1)
        books2.forEach(System.out::println)

    }
}