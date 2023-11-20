package com.test.example

import js.core.Symbol
import kotlin.test.Test
import kotlin.test.assertIs
import kotlin.test.assertIsNot
import com.test.example.String as JsString

class StringTest {
    @Test
    fun isForString() {
        val s = "Frodo"

        assertIs<JsString>(s)
    }

    @Test
    fun isForObject() {
        val a = Any()

        assertIsNot<JsString>(a)
    }

    @Test
    fun isForWellKnownSymbol() {
        val s = Symbol.hasInstance

        assertIsNot<JsString>(s)
    }
}
