@file:Suppress(
    "NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package com.test.example

import seskar.js.JsUnion
import seskar.js.JsValue

@JsUnion
sealed external interface Align {
    companion object {
        @JsValue("TOP")
        val TOP: Align

        @JsValue("LEFT")
        val LEFT: Align

        @JsValue("BOTTOM")
        val BOTTOM: Align

        @JsValue("RIGHT")
        val RIGHT: Align
    }
}
