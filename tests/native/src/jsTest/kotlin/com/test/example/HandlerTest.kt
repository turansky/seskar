package com.test.example

import js.reflect.unsafeCast
import kotlin.test.Test
import kotlin.test.assertEquals

class HandlerTest {
    @Test
    fun test() {
        val handler: MyHandler = unsafeCast { x: Int, y: Int ->
            x + y
        }

        assertEquals(55, handler(13, 42))
    }
}
