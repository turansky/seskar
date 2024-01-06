package com.test.example

import js.objects.jso
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertIsNot

class StreamResultTest {
    @Test
    fun testValue() {
        val result: StreamResult = jso<dynamic> {
            done = false
        }

        assertIs<StreamValueResult>(result)
        assertIsNot<StreamDoneResult>(result)
    }

    @Test
    fun testDone() {
        val result: StreamResult = jso<dynamic> {
            done = true
        }

        assertIs<StreamDoneResult>(result)
        assertIsNot<StreamValueResult>(result)
    }

    @Test
    fun testValueClassName() {
        assertEquals("StreamValueResult", StreamValueResult::class.js.name)
    }

    @Test
    fun testDoneClassName() {
        assertEquals("StreamDoneResult", StreamDoneResult::class.js.name)
    }
}
