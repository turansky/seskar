package com.test.example

import kotlin.test.Test
import kotlin.test.assertEquals

class MyStringArrayTest {
    @Test
    fun testLength() {
        val array = MyStringArray.of()

        assertEquals(0, array.length)
    }
}
