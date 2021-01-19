package me.examplewebmvc.api.book.entity

import me.examplewebmvc.api.book.type.request.BookRequest
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "tbl_book")
data class BookEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var bookId: Long? = null,
    var name: String? = null,
    var author: String? = null,
    @ManyToOne
    @JoinColumn(name = "bookstoreId", insertable = false, updatable = false)
    var bookstore: BookstoreEntity = BookstoreEntity()
): Serializable {

    fun setBookInfo(input: BookRequest){
        this.name = input.name
        this.author = input.author
        this.bookstore.bookstoreId = input.bookstoreId
    }
}
