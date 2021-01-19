package me.examplewebmvc.api.book.type.request

import java.io.Serializable

data class BookRequest(
    var bookId: Long? = null,
    var name: String? = null,
    var author: String? = null,
    var bookstoreId: Long? = null
): Serializable
