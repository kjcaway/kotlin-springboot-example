package me.examplewebmvc.api.book.domain

import me.examplewebmvc.api.book.dto.request.BookRequest
import me.examplewebmvc.basic.domain.Base
import java.io.Serializable
import javax.persistence.*


@Entity
@Table(name = "tbl_book")
class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var bookId: Long? = null,
    var name: String? = null,
    var author: String? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookstoreId")
    var bookstore: Bookstore? = null
): Base(), Serializable {

    constructor(input: BookRequest): this(){
        input.name?.let { this.name = it}
        input.author?.let { this.author = it}
    }

    fun setStore(bookstore: Bookstore){
        this.bookstore = bookstore
    }
}
