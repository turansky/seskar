@file:Suppress(
    "NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package com.test.example

import seskar.js.JsUnion
import seskar.js.JsValue

@JsUnion
sealed external interface CustomAlignO {
    companion object {
        @JsValue("t")
        val TOP: TOP

        @JsValue("l")
        val LEFT: LEFT

        @JsValue("b")
        val BOTTOM: BOTTOM

        @JsValue("r")
        val RIGHT: RIGHT
    }

    interface TOP : CustomAlignO
    interface LEFT : CustomAlignO
    interface BOTTOM : CustomAlignO
    interface RIGHT : CustomAlignO
}
