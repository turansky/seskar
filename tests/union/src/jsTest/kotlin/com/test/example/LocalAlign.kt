@file:Suppress(
    "NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package com.test.example

import seskar.js.JsValue
import seskar.js.JsVirtual

@JsVirtual
sealed external interface LocalAlign {
    companion object {
        @JsValue("TOP")
        val TOP: LocalAlign

        @JsValue("LEFT")
        val LEFT: LocalAlign

        @JsValue("BOTTOM")
        val BOTTOM: LocalAlign

        @JsValue("RIGHT")
        val RIGHT: LocalAlign
    }
}
