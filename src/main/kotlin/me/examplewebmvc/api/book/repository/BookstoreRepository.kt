package me.examplewebmvc.api.book.repository

import me.examplewebmvc.api.book.entity.BookstoreEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.PersistenceContext

@Repository
@PersistenceContext
interface BookstoreRepository: JpaRepository<BookstoreEntity, Long>{
}