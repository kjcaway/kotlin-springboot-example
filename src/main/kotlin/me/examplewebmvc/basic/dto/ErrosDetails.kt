package me.examplewebmvc.basic.dto

import java.util.*

data class ErrorsDetails(
    val time: Date,
    val message: String,
    val details: String
)