package com.test.example

import kotlin.test.Test
import kotlin.test.assertEquals

private fun createHandler(): MyHandler =
    js("((x, y) => x + y)")

class HandlerTest {
    @Test
    fun test() {
        val handler: MyHandler = createHandler()

        assertEquals(55, handler(13, 42))
    }
}
