package com.test.example

import kotlin.test.Test
import kotlin.test.assertEquals

class SizeTest {
    @Test
    fun age() {
        assertEquals(1, useSize("first"))
        assertEquals(2, useSize("first", Age(42)))
        assertEquals(2, useSize(Age(42), "second"))
    }

    @Test
    fun count() {
        assertEquals(1, useSize("first"))
        assertEquals(2, useSize("first", Count(42)))
        assertEquals(2, useSize(Count(42), "second"))
    }
}

private fun useSize(
    vararg dependencies: Any?,
): Int =
    dependencies.size
