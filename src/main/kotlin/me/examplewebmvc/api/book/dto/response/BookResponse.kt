package me.examplewebmvc.api.book.dto.response

import com.fasterxml.jackson.annotation.JsonInclude
import me.examplewebmvc.api.book.domain.Book
import java.io.Serializable

@JsonInclude(JsonInclude.Include.NON_NULL)
data class BookResponse(
    var bookId: Long? = null,
    var name: String? = null,
    var author: String? = null
): Serializable{
    constructor(input: Book): this() {
        this.bookId = input.bookId
        this.name = input.name
        this.author = input.author
    }
}
