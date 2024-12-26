package com.test.example

import seskar.js.JsRawValue

sealed external interface BooleanItemType {
    companion object {
        @JsRawValue("false")
        val f: BooleanItemType

        @JsRawValue("true")
        val t: BooleanItemType
    }
}
