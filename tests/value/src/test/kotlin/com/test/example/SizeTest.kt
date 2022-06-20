package com.test.example

import kotlin.test.Test
import kotlin.test.assertEquals

class SizeTest {
    @Test
    fun test() {
        assertEquals(1, getSize("first"))
        assertEquals(1, getSize("first", Age(42)))
        assertEquals(1, getSize(Age(42), "second"))
    }
}

private fun getSize(
    vararg dependencies: Any?,
): Int =
    dependencies.size
