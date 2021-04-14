package me.examplewebmvc.api.book.dto.request

import java.io.Serializable

data class BookRequest(
    var bookId: Long? = null,
    var name: String? = null,
    var author: String? = null,
    var bookstoreId: Long? = null
): Serializable
