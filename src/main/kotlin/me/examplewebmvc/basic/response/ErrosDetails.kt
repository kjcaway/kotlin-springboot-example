package me.examplewebmvc.basic.response

import java.util.*

data class ErrorsDetails(
    val time: Date,
    val message: String,
    val details: String
)