package com.test.example

import kotlin.test.Test
import kotlin.test.assertEquals

class SizeTest {
    @Test
    fun age() {
        assertEquals(1, getSize("first"))
        assertEquals(1, getSize("first", Age(42)))
        assertEquals(1, getSize(Age(42), "second"))
    }

    @Test
    fun count() {
        assertEquals(1, getSize("first"))
        assertEquals(2, getSize("first", Count(42)))
        assertEquals(2, getSize(Count(42), "second"))
    }
}

private fun getSize(
    vararg dependencies: Any?,
): Int =
    dependencies.size
