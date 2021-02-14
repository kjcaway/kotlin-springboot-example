package me.examplewebmvc.api.book.service

import me.examplewebmvc.api.book.entity.BookstoreEntity
import me.examplewebmvc.api.book.repository.BookRepository
import me.examplewebmvc.api.book.repository.BookstoreRepository
import me.examplewebmvc.api.book.type.request.BookRequest
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("local")
class BookServiceImplTest {
    @Autowired
    private lateinit var bookService: BookService
    @Autowired
    private lateinit var bookRepository: BookRepository
    @Autowired
    private lateinit var bookstoreRepository: BookstoreRepository

    @Test
    fun setBook(){
        var inputBook = BookRequest()
        inputBook.name = "주식의 모든것3"
        inputBook.author = "강팔자"
        inputBook.bookstoreId = 1

        bookService.setBook(inputBook)
    }

    @Test
    fun getBookListTest(){
        var bookstoreId = 1L
        var listA = bookRepository.findByBookstore_BookstoreId(bookstoreId)
        println("-----")
        var listB = bookRepository.findAll()
        println("-----")
        var listC = bookRepository.findAllWithFetchJoin()
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

    @Test
    fun setBookstore(){
        var bookstoreEntity = BookstoreEntity("알라딘책방")
        bookstoreRepository.save(bookstoreEntity)

    }
}