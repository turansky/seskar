package com.test.example

import seskar.js.JsNativeGetter
import seskar.js.JsNativeSetter

sealed external interface MyRecord {
    @JsNativeGetter
    operator fun get(
        key: String,
    ): Int

    @JsNativeSetter
    operator fun set(
        key: String,
        value: Int,
    )
}
