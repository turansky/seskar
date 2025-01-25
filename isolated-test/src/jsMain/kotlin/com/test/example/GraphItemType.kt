package com.test.example

import seskar.js.JsRawValue

sealed external interface GraphItemType {
    companion object {
        @JsRawValue("1")
        val NODE: GraphItemType

        @JsRawValue("2")
        val EDGE: GraphItemType

        @JsRawValue("4")
        val PORT: GraphItemType

        @JsRawValue("8")
        val LABEL: GraphItemType
    }
}
