package com.test.example

import js.objects.unsafeJso
import kotlin.test.Test
import kotlin.test.assertEquals

class ElementAttributesTest {
    @Test
    fun testFunctionCall() {
        val attributes: ElementAttributes = unsafeJso {
            asDynamic()["get-data"] = { "13" }
        }

        assertEquals("13", attributes.getData())
    }
}
