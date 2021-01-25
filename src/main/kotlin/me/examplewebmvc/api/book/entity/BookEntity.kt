package me.examplewebmvc.api.book.entity

import me.examplewebmvc.api.book.type.request.BookRequest
import me.examplewebmvc.basic.entity.BaseEntity
import java.io.Serializable
import javax.persistence.*


@Entity
@Table(name = "tbl_book")
class BookEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var bookId: Long? = null,
    var name: String? = null,
    var author: String? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookstoreId")
    var bookstore: BookstoreEntity? = null
): BaseEntity(), Serializable {

    constructor(input: BookRequest): this(){
        input.name?.let { this.name = it}
        input.author?.let { this.author = it}
    }

    fun setStore(bookstore: BookstoreEntity){
        this.bookstore = bookstore
    }
}
