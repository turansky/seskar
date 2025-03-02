package com.test.example

import kotlin.test.Test
import kotlin.test.assertEquals

class MyStringArrayTest {
    @Test
    fun testLength() {
        val array = MyStringArray.of()

        assertEquals(0, array.length)
    }

    @Test
    fun testFirst() {
        val array = MyStringArray.of("f_1", "s_2", "t_3", "f_4")

        assertEquals("f_1", array.getFirst())
    }

    @Test
    fun testSecond() {
        val array = MyStringArray.of("f_1", "s_2", "t_3", "f_4")

        assertEquals("s_2", array.getSecond())
    }

    @Test
    fun testThird() {
        val array = MyStringArray.of("f_1", "s_2", "t_3", "f_4")

        assertEquals("t_3", array.getThird())
    }

    @Test
    fun testFourth() {
        val array = MyStringArray.of("f_1", "s_2", "t_3", "f_4")

        assertEquals("f_4", array.getFourth())
    }
}
