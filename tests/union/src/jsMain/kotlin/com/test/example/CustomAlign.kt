@file:Suppress(
    "NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package com.test.example

import seskar.js.JsString
import seskar.js.JsUnion

@JsUnion
sealed external interface CustomAlign {
    companion object {
        @JsString("t")
        val TOP: CustomAlign

        @JsString("l")
        val LEFT: CustomAlign

        @JsString("b")
        val BOTTOM: CustomAlign

        @JsString("r")
        val RIGHT: CustomAlign
    }
}
