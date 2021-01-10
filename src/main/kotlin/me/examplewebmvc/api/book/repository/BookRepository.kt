package me.examplewebmvc.api.book.repository

import me.examplewebmvc.api.book.model.BookEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<BookEntity, Long> {

}