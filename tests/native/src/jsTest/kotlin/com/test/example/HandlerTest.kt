package com.test.example

import kotlin.test.Test
import kotlin.test.assertEquals

class HandlerTest {
    @Test
    fun test() {
        val handler: MyHandler = ({ x: Int, y: Int -> x + y }).unsafeCast<MyHandler>()

        assertEquals(55, handler(13, 42))
    }
}
