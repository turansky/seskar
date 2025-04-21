package com.test.example

import js.objects.unsafeJso
import kotlin.test.Test
import kotlin.test.assertEquals

class AriaAttributesTest {
    @Test
    fun testGet() {
        val attributes: AriaAttributes = unsafeJso {
            asDynamic()["aria-label"] = "13"
        }

        assertEquals("13", attributes.ariaLabel)
    }

    @Test
    fun testSet() {
        val attributes: AriaAttributes = unsafeJso {
            ariaLabel = "42"
        }

        assertEquals("42", attributes.asDynamic()["aria-label"])
    }
}
