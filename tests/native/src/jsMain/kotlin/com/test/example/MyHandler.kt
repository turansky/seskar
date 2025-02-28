package com.test.example

import seskar.js.JsNativeInvoke

sealed external interface MyHandler {
    @JsNativeInvoke
    operator fun invoke(x: Int, y: Int): Int
}
