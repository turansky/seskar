package com.test.example

import js.objects.jso
import kotlin.test.Test
import kotlin.test.assertEquals

class ElementAttributesTest {
    @Test
    fun testFunctionCall() {
        val attributes: ElementAttributes = jso {
            asDynamic()["get-data"] = { "13" }
        }

        assertEquals("13", attributes.getData())
    }
}
