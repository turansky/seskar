package com.test.example

import kotlin.test.Test
import kotlin.test.assertEquals

class AlignTest {
    @Test
    fun test() {
        assertEquals<Any>("TOP", Align.TOP)
        assertEquals<Any>("LEFT", Align.LEFT)
        assertEquals<Any>("BOTTOM", Align.BOTTOM)
        assertEquals<Any>("RIGHT", Align.RIGHT)
    }
}
