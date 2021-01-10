package me.examplewebmvc.api.book.repository

import me.examplewebmvc.api.book.model.BookstoreEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BookstoreRepository: JpaRepository<BookstoreEntity, Long>{
}