package com.test.example

import js.promise.Promise
import seskar.js.JsAsync

@JsName("getGlobalResponse")
external fun getGlobalResponseAsync(
    url: String,
): Promise<Any?>

@JsAsync
external suspend fun getGlobalResponse(
    url: String,
): Any?
