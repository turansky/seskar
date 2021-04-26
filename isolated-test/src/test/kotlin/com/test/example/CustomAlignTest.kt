package com.test.example

import kotlin.test.Test
import kotlin.test.assertEquals

class CustomAlignTest {
    @Test
    fun test() {
        assertEquals<Any>("t", CustomAlign.TOP)
        assertEquals<Any>("l", CustomAlign.LEFT)
        assertEquals<Any>("b", CustomAlign.BOTTOM)
        assertEquals<Any>("r", CustomAlign.RIGHT)
    }
}
