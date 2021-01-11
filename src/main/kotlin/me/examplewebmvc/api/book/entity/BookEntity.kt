package me.examplewebmvc.api.book.entity

import me.examplewebmvc.api.book.type.Book
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

    fun setBookInfo(book: Book){
        this.name = book.name
        this.author = book.author
        this.bookstore.bookstoreId = book.bookstoreId
    }
}
