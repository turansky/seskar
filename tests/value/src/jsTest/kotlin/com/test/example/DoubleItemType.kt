package com.test.example

import seskar.js.JsRawValue

sealed external interface DoubleItemType {
    companion object {
        @JsRawValue("1.5")
        val P_1_5: DoubleItemType

        @JsRawValue("234.769")
        val P_234_769: DoubleItemType

        @JsRawValue("-1.5")
        val N_1_5: DoubleItemType

        @JsRawValue("-234.769")
        val N_234_769: DoubleItemType
    }
}
