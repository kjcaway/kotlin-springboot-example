package me.examplewebmvc.api.book.domain

import me.examplewebmvc.basic.domain.Base
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "tbl_bookstore")
class Bookstore (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var bookstoreId: Long? = null,
    var name: String? = null
): Base(), Serializable {
    constructor(name: String): this(){
        this.name = name
    }
}