@file:Suppress(
    "NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package com.test.example

import seskar.js.JsInt
import seskar.js.JsUnion

@JsUnion
sealed external interface GraphItemType {
    companion object {
        @JsInt(1)
        val NODE: GraphItemType

        @JsInt(2)
        val EDGE: GraphItemType

        @JsInt(4)
        val PORT: GraphItemType

        @JsInt(8)
        val LABEL: GraphItemType
    }
}
