package me.examplewebmvc.api.book.entity

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "tbl_bookstore")
data class BookstoreEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var bookstoreId: Long? = null,
    var name: String? = null
): Serializable {
}