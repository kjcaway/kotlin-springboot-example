package me.examplewebmvc.api.book.type.response

import me.examplewebmvc.api.book.entity.BookEntity
import java.io.Serializable

data class BookResponse(
    var bookId: Long? = null,
    var name: String? = null,
    var author: String? = null
): Serializable{
    constructor(input: BookEntity): this() {
        this.bookId = input.bookId
        this.name = input.name
        this.author = input.author
    }
}
