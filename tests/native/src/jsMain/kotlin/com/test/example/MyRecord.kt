package com.test.example

import seskar.js.JsNative

sealed external interface MyRecord {
    @JsNative
    operator fun get(
        key: String,
    ): Int

    @JsNative
    operator fun set(
        key: String,
        value: Int,
    )
}
