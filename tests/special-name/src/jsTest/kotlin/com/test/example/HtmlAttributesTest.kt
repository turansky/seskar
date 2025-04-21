package com.test.example

import js.objects.unsafeJso
import kotlin.test.Test
import kotlin.test.assertEquals

class HtmlAttributesTest {
    @Test
    fun testGet() {
        val attributes: HtmlAttributes = unsafeJso {
            asDynamic()["aria-label"] = "13"
        }

        assertEquals("13", attributes.ariaLabel)
    }

    @Test
    fun testSet() {
        val attributes: HtmlAttributes = unsafeJso {
            ariaLabel = "42"
        }

        assertEquals("42", attributes.asDynamic()["aria-label"])
    }

    @Test
    fun testFunctionCall() {
        val attributes: HtmlAttributes = unsafeJso {
            asDynamic()["get-data"] = { "42" }
        }

        assertEquals("42", attributes.getData())
    }
}
