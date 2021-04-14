package me.examplewebmvc.basic

import me.examplewebmvc.basic.dto.ErrorsDetails
import me.examplewebmvc.exception.EmptyBookException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@ControllerAdvice
class CustomExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [EmptyBookException::class])
    fun handlerEmptyBook(ex: EmptyBookException, request: WebRequest): ResponseEntity<ErrorsDetails> {
        val errorDetails = ErrorsDetails(
            Date(),
            "Custom Empty Exception!!",
            ex.message!!
        )
        return ResponseEntity(errorDetails, EmptyBookException::class.java.getAnnotation(ResponseStatus::class.java).value)
    }
}