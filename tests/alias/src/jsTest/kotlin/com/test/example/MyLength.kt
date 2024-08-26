package com.test.example

import seskar.js.JsAlias
import seskar.js.JsAlias.Companion.THIS

sealed external interface MyLength {
    @JsAlias(THIS)
    fun asString(): String
}

fun MyLength(
    value: String,
): MyLength =
    value.unsafeCast<MyLength>()
