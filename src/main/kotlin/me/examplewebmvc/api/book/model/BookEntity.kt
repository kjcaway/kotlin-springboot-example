package me.examplewebmvc.api.book.model

import javax.persistence.*

@Entity
@Table(name = "tbl_book")
class BookEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var bookId: Long? = null,
    var name: String? = null,
    var author: String? = null,
    @ManyToOne
    @JoinColumn(name = "bookstoreId")
    var bookstore: BookstoreEntity? = null
)
