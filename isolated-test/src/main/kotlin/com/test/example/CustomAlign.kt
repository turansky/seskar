package com.test.example

import seskar.js.JsUnion
import seskar.js.JsValue

@JsUnion
external enum class CustomAlign {
    @JsValue("t")
    TOP,

    @JsValue("l")
    LEFT,

    @JsValue("b")
    BOTTOM,

    @JsValue("r")
    RIGHT,

    ;
}
