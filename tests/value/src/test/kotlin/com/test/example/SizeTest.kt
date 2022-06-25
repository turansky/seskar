package com.test.example

import kotlin.test.Test
import kotlin.test.assertEquals

class SizeTest {
    @Test
    fun age() {
        val age = Age(42)

        assertEquals(1, useSize("first"))
        assertEquals(2, useSize("first", age))
        assertEquals(2, useSize(age, "second"))
    }

    @Test
    fun count() {
        val count = Count(42)

        assertEquals(1, useSize("first"))
        assertEquals(2, useSize("first", count))
        assertEquals(2, useSize(count, "second"))
    }
}

private fun useSize(
    vararg dependencies: Any?,
): Int =
    dependencies.size
