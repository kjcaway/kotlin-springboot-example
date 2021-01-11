package me.examplewebmvc.api.book.type

import java.io.Serializable

data class Book(
    var bookId: Long? = null,
    var name: String? = null,
    var author: String? = null,
    var bookstoreId: Long? = null
): Serializable
