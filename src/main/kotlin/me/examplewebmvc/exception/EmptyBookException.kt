package me.examplewebmvc.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.NOT_FOUND)
class EmptyBookException (
    override val message: String?
) : RuntimeException(message)