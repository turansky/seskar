@file:Suppress(
    "NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package com.test.example

import seskar.js.JsVirtual

@JsVirtual
sealed external interface Align {
    companion object {
        val TOP: Align
        val LEFT: Align
        val BOTTOM: Align
        val RIGHT: Align
    }
}
