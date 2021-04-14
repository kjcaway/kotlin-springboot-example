package me.examplewebmvc.api.book.domain

import me.examplewebmvc.api.book.domain.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Long> {
    fun findByName(name: String): List<Book>
    fun findByAuthor(author: String): List<Book>
    fun findByBookstore_BookstoreId(bookstoreId: Long): List<Book>

    @Query("select b from Book b left join fetch b.bookstore")
    fun findAllWithFetchJoin(): List<Book>
}