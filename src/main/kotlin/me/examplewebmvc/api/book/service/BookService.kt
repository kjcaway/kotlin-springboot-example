package me.examplewebmvc.api.book.service

import me.examplewebmvc.api.book.entity.BookEntity
import me.examplewebmvc.api.book.type.Book

interface BookService {
    fun getBooks(bookstoreId: Long?): List<BookEntity>
    fun getBook(bookId: Long): BookEntity?
    fun setBook(inputBook: Book)
    fun modBook(inputBook: Book)
    fun delBook(bookId: Long)
}