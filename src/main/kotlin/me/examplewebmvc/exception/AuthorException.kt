package me.examplewebmvc.exception


class AuthorException (
    val author: String,
    override val cause: Throwable? = null
): RuntimeException(cause)