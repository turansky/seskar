@file:Suppress(
    "NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package com.test.example

import seskar.js.JsIntValue
import seskar.js.JsVirtual

@JsVirtual
sealed external interface GraphItemType {
    companion object {
        @JsIntValue(1)
        val NODE: GraphItemType

        @JsIntValue(2)
        val EDGE: GraphItemType

        @JsIntValue(4)
        val PORT: GraphItemType

        @JsIntValue(8)
        val LABEL: GraphItemType
    }
}
