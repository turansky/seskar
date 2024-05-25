package com.test.example

import web.events.EventTarget
import kotlin.test.Test
import kotlin.test.assertEquals

class MyFancyChildTupleTest {
    @Test
    fun testIndexDestructuring() {
        val target = EventTarget()
        val (a, b, c, d) = MyFancyChildTuple("42", 56, target, 102)

        assertEquals("42", a)
        assertEquals(56, b)
        assertEquals(target, c)
        assertEquals(102, d)
    }

    @Test
    fun testIndexComponents() {
        val target = EventTarget()
        val tuple = MyFancyChildTuple("42", 56, target, 102)

        assertEquals("42", tuple.component1())
        assertEquals(56, tuple.component2())
        assertEquals(target, tuple.component3())
        assertEquals(102, tuple.component4())
    }
}
