package com.test.example

import kotlin.test.Test
import kotlin.test.assertEquals

class ValueTransformTest {
    @Test
    fun age_second() {
        val age = Age(42)

        val array = useArray("first", age)
        assertEquals("first", array[0])
        assertEquals(42, array[1])
    }

    @Test
    fun age_first() {
        val age = Age(13)

        val array = useArray(age, "second")
        assertEquals(13, array[0])
        assertEquals("second", array[1])
    }

    @Test
    fun age_second_null() {
        val age: Age? = null

        val array = useArray("first", age)
        assertEquals("first", array[0])
        assertEquals(null, array[1])
    }

    @Test
    fun age_first_null() {
        val age: Age? = null

        val array = useArray(age, "second")
        assertEquals(null, array[0])
        assertEquals("second", array[1])
    }
}
