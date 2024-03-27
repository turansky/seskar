package com.test.example

import js.objects.jso
import kotlin.test.Test
import kotlin.test.assertEquals

class HtmlAttributesTest {
    @Test
    fun testGet() {
        val attributes: HtmlAttributes = jso {
            asDynamic()["aria-label"] = "13"
        }

        assertEquals("13", attributes.ariaLabel)
    }

    @Test
    fun testSet() {
        val attributes: HtmlAttributes = jso {
            ariaLabel = "42"
        }

        assertEquals("42", attributes.asDynamic()["aria-label"])
    }

    @Test
    fun testFunctionCall() {
        val attributes: HtmlAttributes = jso {
            asDynamic()["get-data"] = { "42" }
        }

        assertEquals("42", attributes.getData())
    }
}
