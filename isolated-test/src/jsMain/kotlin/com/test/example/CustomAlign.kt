package com.test.example

import seskar.js.JsString
import seskar.js.JsUnion

@JsUnion
external enum class CustomAlign {
    @JsString("t")
    TOP,

    @JsString("l")
    LEFT,

    @JsString("b")
    BOTTOM,

    @JsString("r")
    RIGHT,

    ;
}
