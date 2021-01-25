package me.examplewebmvc.api.book.entity

import me.examplewebmvc.basic.entity.BaseEntity
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "tbl_bookstore")
class BookstoreEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var bookstoreId: Long? = null,
    var name: String? = null
): BaseEntity(), Serializable {
    constructor(name: String): this(){
        this.name = name
    }
}