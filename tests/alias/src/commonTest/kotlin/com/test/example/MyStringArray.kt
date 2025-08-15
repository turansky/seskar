package com.test.example

import seskar.js.JsAlias
import kotlin.js.JsName

@JsName("Array")
external class MyStringArray {
    val length: Int

    @JsAlias("[0]")
    fun getFirst(): String?

    @JsAlias("[1]")
    fun getSecond(): String?

    @JsAlias("[2]")
    fun getThird(): String?

    @JsAlias("[3]")
    fun getFourth(): String?

    companion object {
        fun of(vararg items: String): MyStringArray
    }
}
