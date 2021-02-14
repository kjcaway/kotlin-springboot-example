package me.examplewebmvc.api.external.service

import java.util.concurrent.CompletableFuture

interface ExtTestService {
    fun getOtherResourceSync(repeatCnt: Int): List<String>
    fun getOtherResourceAsync(repeatCnt: Int): CompletableFuture<List<String>>
}