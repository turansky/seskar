package com.test.example

import kotlin.test.Test
import kotlin.test.assertIs

class TypeTest {
    @Test
    fun age_second() {
        val array = getArray("first", Age(42))
        assertIs<String>(array[0])
        assertIs<Int>(array[1])
    }

    @Test
    fun age_first() {
        val array = getArray(Age(42), "first")
        assertIs<Int>(array[0])
        assertIs<String>(array[1])

    }
}

private fun getArray(
    vararg dependencies: Any?,
): Array<out Any?> =
    dependencies
