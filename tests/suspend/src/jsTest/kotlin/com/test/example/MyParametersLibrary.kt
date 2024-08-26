@file:JsModule("./my-parameters-library")

package com.test.example

import js.promise.Promise
import seskar.js.JsAsync

@JsName("getParameters")
external fun getParametersAsync(
    a: String = definedExternally,
    b: String? = definedExternally,
    c: String = definedExternally,
): Promise<Array<Any?>>

@JsAsync
external suspend fun getParameters(
    a: String = definedExternally,
    b: String? = definedExternally,
    c: String = definedExternally,
): Array<Any?>
