package com.test.example

import js.core.jso
import kotlin.test.Test
import kotlin.test.assertEquals

class AriaAttributesTest {
    @Test
    fun testGet() {
        val attributes: AriaAttributes = jso {
            asDynamic()["aria-label"] = "13"
        }

        assertEquals("13", attributes.ariaLabel)
    }

    @Test
    fun testSet() {
        val attributes: AriaAttributes = jso {
            ariaLabel = "42"
        }

        assertEquals("42", attributes.asDynamic()["aria-label"])
    }
}
