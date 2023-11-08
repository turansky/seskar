@file:Suppress(
    "NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package com.test.example

import seskar.js.JsValue
import seskar.js.JsVirtual

@JsVirtual
sealed external interface CustomAlign {
    companion object {
        @JsValue("t")
        val TOP: CustomAlign

        @JsValue("l")
        val LEFT: CustomAlign

        @JsValue("b")
        val BOTTOM: CustomAlign

        @JsValue("r")
        val RIGHT: CustomAlign
    }
}
