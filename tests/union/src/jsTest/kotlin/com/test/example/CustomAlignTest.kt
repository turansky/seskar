package com.test.example

import kotlin.test.Test

class CustomAlignTest {
    @Test
    fun test() {
        assertEquals("t", CustomAlign.TOP)
        assertEquals("l", CustomAlign.LEFT)
        assertEquals("b", CustomAlign.BOTTOM)
        assertEquals("r", CustomAlign.RIGHT)
    }
}
