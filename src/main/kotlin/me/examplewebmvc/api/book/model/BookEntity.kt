package me.examplewebmvc.api.book.model

import javax.persistence.*

@Entity
@Table(name = "tbl_book")
data class BookEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val bookId: Long,
    val name: String,
    val author: String
)
