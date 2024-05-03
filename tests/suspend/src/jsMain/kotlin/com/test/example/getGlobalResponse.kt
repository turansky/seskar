package com.test.example

import js.promise.Promise

@JsName("getGlobalResponse")
external fun getGlobalResponseAsync(
    url: String,
): Promise<Any?>

external suspend fun getGlobalResponse(
    url: String,
): Any?
