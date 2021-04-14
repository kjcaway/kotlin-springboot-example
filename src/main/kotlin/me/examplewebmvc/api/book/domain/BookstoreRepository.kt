package me.examplewebmvc.api.book.domain

import me.examplewebmvc.api.book.domain.Bookstore
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.PersistenceContext

@Repository
@PersistenceContext
interface BookstoreRepository: JpaRepository<Bookstore, Long>{
}