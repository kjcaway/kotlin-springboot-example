package me.examplewebmvc.api.book.service

import me.examplewebmvc.api.book.entity.BookEntity
import me.examplewebmvc.api.book.repository.BookRepository
import me.examplewebmvc.api.book.type.Book
import me.examplewebmvc.exception.AuthorException
import me.examplewebmvc.exception.EmptyBookException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("bookService")
@Transactional
class BookServiceImpl(
    val bookRepository: BookRepository
) : BookService{
    override fun getBooks(bookstoreId: Long?): List<BookEntity> {
        return if (bookstoreId != null) {
            bookRepository.findByBookstore_BookstoreId(bookstoreId)
        } else {
            bookRepository.findAll()
        }
    }

    override fun getBook(bookId: Long): BookEntity? {
        var bookOptional  = bookRepository.findById(bookId)
        if(bookOptional.isEmpty){
            throw EmptyBookException("there's empty matched book.")
        }
        return bookOptional.get()
    }

    override fun setBook(inputBook: Book) {
        var bookEntity = BookEntity()

        if(inputBook.author!!.length < 5){
            throw AuthorException(inputBook.author!!)
        }

        bookEntity.setBookInfo(inputBook)

        bookRepository.save(bookEntity)
    }

    override fun modBook(inputBook: Book) {
        var bookOptional = bookRepository.findById(inputBook.bookId!!)
        if(bookOptional.isEmpty){
            throw EmptyBookException("there's empty matched book.")
        }

        bookOptional.ifPresent { bookEntity ->
            inputBook.author?.let {
                if(it.length < 5){
                    throw AuthorException(it)
                }
                bookEntity.author = it
            }
            inputBook.name?.let { bookEntity.name = it}

            bookRepository.save(bookEntity)
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