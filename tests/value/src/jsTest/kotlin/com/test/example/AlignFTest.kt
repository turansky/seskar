package com.test.example

import kotlin.test.Test

class AlignFTest {
    @Test
    fun test() {
        assertEquals("TOP", AlignF.top())
        assertEquals("LEFT", AlignF.left())
        assertEquals("BOTTOM", AlignF.bottom())
        assertEquals("RIGHT", AlignF.right())
    }
}
