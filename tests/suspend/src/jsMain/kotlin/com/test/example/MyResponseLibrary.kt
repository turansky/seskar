@file:JsModule("my-response-library")

package com.test.example

import js.promise.Promise
import seskar.js.JsAsync

@JsName("getResponse")
external fun getResponseAsync(
    url: String,
): Promise<Any?>

@JsAsync
external suspend fun getResponse(
    url: String,
): Any?

@JsName("getResponseOptional")
external fun getResponseOptionalAsync(
    url: String,
): Promise<String>

@JsAsync(optional = true)
external suspend fun getResponseOptional(
    url: String,
): String?
