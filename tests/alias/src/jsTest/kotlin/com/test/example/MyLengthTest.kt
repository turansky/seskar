package com.test.example

import kotlin.test.Test
import kotlin.test.assertEquals

class MyLengthTest {
    @Test
    fun testThisAlias() {
        val l = MyLength("13")
        assertEquals("13", l.asString())
    }
}
