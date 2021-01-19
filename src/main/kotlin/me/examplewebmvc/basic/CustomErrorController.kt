package me.examplewebmvc.basic

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomErrorController: ErrorController {
    companion object {
        private const val PATH = "/error"   // default property in 'server.error.path'
    }

    override fun getErrorPath(): String {
        return PATH
    }

    @RequestMapping(value = [PATH])
    fun error(): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }
}