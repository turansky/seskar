package com.test.example

import js.promise.Promise

external interface MyGlobalThis {
    @JsName("getResponse")
    fun getResponseAsync(
        url: String,
    ): Promise<Any?>

    suspend fun getResponse(
        url: String,
    ): Any?
}