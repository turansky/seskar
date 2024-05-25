package com.test.example

import js.promise.Promise
import seskar.js.JsAsync

external interface MyLoaderBaseBase<T : Any> {
    @JsName("getResponse")
    fun getResponseAsync(
        url: String,
    ): Promise<T>

    @JsAsync
    suspend fun getResponse(
        url: String,
    ): T
}

external interface MyLoaderBase<T : Any>
    : MyLoaderBaseBase<T> {
    val a: String?
}

external interface MyLoader<T : Any>
    : MyLoaderBase<T> {
    val b: String?
}
