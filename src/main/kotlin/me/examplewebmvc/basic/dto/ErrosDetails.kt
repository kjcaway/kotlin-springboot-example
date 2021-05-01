package me.examplewebmvc.basic.dto

data class ErrorsDetails(
    val code: String = "FAIL",
    val message: String
)