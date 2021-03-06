package me.examplewebmvc.api.book.service

import me.examplewebmvc.api.book.domain.Book
import me.examplewebmvc.api.book.domain.BookRepository
import me.examplewebmvc.api.book.domain.BookstoreRepository
import me.examplewebmvc.api.book.dto.request.BookRequest
import me.examplewebmvc.api.book.dto.response.BookResponse
import me.examplewebmvc.exception.AuthorException
import me.examplewebmvc.exception.EmptyBookException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("bookService")
@Transactional
class BookServiceImpl(
        val bookRepository: BookRepository,
        var bookstoreRepository: BookstoreRepository
) : BookService{
    override fun getBooks(bookstoreId: Long?): List<BookResponse> {
        return if (bookstoreId != null) {
            val bookList = bookRepository.findByBookstore_BookstoreId(bookstoreId)
            bookList.map { bookEntity -> BookResponse(bookEntity)}
        } else {
            val bookList = bookRepository.findAll()
            bookList.map { bookEntity -> BookResponse(bookEntity)}
        }
    }

    override fun getBook(bookId: Long): BookResponse {
        var bookOptional  = bookRepository.findById(bookId)
        if(bookOptional.isEmpty){
            throw EmptyBookException("there's empty matched book.")
        }
        return BookResponse(bookOptional.get())
    }

    override fun setBook(inputBook: BookRequest): BookResponse {
        if(inputBook.author!!.length < 3){
            throw AuthorException(inputBook.author!!)
        }
        var bookstoreEntity = bookstoreRepository.findByIdOrNull(inputBook.bookstoreId!!)
        var bookEntity = Book(inputBook)
        bookstoreEntity?.let { bookEntity.setStore(it)}

        val book = bookRepository.save(bookEntity)

        return BookResponse(book)
    }

    override fun modBook(inputBook: BookRequest) {
        var bookOptional = bookRepository.findById(inputBook.bookId!!)
        if(bookOptional.isEmpty){
            throw EmptyBookException("there's empty matched book.")
        }

        bookOptional.ifPresent { bookEntity ->
            inputBook.author?.let {
                if (it.length < 5) {
                    throw AuthorException(it)
                }
                bookEntity.author = it
            }
            inputBook.name?.let { bookEntity.name = it }

            val book = bookRepository.save(bookEntity)

            BookResponse(book)
        }
    }

    override fun delBook(bookId: Long){
        var exist = bookRepository.existsById(bookId)
        if(!exist){
            throw EmptyBookException("there's empty matched book.")
        }

        bookRepository.deleteById(bookId)
    }

}