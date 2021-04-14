package me.examplewebmvc.api.book.service

import me.examplewebmvc.api.book.dto.request.BookRequest
import me.examplewebmvc.api.book.dto.response.BookResponse

interface BookService {
    fun getBooks(bookstoreId: Long?): List<BookResponse>
    fun getBook(bookId: Long): BookResponse
    fun setBook(inputBook: BookRequest)
    fun modBook(inputBook: BookRequest)
    fun delBook(bookId: Long)
}