package com.test.example

import seskar.js.JsNative

sealed external interface MyHandler {
    @JsNative
    operator fun invoke(x: Int, y: Int): Int
}
