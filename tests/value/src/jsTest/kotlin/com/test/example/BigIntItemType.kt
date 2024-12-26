package com.test.example

import seskar.js.JsRawValue

sealed external interface BigIntItemType {
    companion object {
        @JsRawValue("15n")
        val P_15: BigIntItemType

        @JsRawValue("234769n")
        val P_234769: BigIntItemType

        @JsRawValue("23476913131313131313131313131313131313131313131313131313234769n")
        val P_23476913131313131313131313131313131313131313131313131313234769: BigIntItemType

        @JsRawValue("-15n")
        val N_15: BigIntItemType

        @JsRawValue("-234769n")
        val N_234769: BigIntItemType

        @JsRawValue("-23476913131313131313131313131313131313131313131313131313234769n")
        val N_23476913131313131313131313131313131313131313131313131313234769: BigIntItemType
    }
}
