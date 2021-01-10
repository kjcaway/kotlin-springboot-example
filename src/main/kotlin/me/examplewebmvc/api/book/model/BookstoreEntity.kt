package me.examplewebmvc.api.book.model

import javax.persistence.*

@Entity
@Table(name = "tbl_bookstore")
class BookstoreEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var bookstoreId: Long? = null,
    var name: String? = null
)