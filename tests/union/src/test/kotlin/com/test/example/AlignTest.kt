package com.test.example

import kotlin.test.Test

class AlignTest {
    @Test
    fun test() {
        assertEquals("TOP", Align.TOP)
        assertEquals("LEFT", Align.LEFT)
        assertEquals("BOTTOM", Align.BOTTOM)
        assertEquals("RIGHT", Align.RIGHT)
    }
}
