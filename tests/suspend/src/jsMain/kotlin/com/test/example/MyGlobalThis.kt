package com.test.example

import js.promise.Promise
import seskar.js.JsAsync

external interface MyGlobalThis {
    @JsName("getResponse")
    fun getResponseAsync(
        url: String,
    ): Promise<Any?>

    @JsAsync
    suspend fun getResponse(
        url: String,
    ): Any?
}
