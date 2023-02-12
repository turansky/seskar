package com.test.example

import seskar.js.JsInt
import seskar.js.JsUnion

@JsUnion
external enum class GraphItemType {
    @JsInt(1)
    NODE,

    @JsInt(2)
    EDGE,

    @JsInt(4)
    PORT,

    @JsInt(8)
    LABEL,

    ;
}
