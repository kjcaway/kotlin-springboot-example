package me.examplewebmvc.api.book.repository

import me.examplewebmvc.api.book.entity.BookEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<BookEntity, Long> {
    fun findByName(name: String): List<BookEntity>
    fun findByAuthor(author: String): List<BookEntity>
    fun findByBookstore_BookstoreId(bookstoreId: Long): List<BookEntity>
}