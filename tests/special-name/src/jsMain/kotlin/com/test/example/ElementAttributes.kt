package com.test.example

import seskar.js.JsSpecialName

external interface ElementAttributes {
    @JsSpecialName("get-data")
    fun getData(): String
}
