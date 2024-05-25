package com.test.example

import web.events.EventTarget
import kotlin.test.Test
import kotlin.test.assertEquals

class MyFancyTupleTest {
    @Test
    fun testIndexDestructuring() {
        val target = EventTarget()
        val (a, b, c) = MyFancyTuple("42", 56, target)

        assertEquals("42", a)
        assertEquals(56, b)
        assertEquals(target, c)
    }

    @Test
    fun testIndexComponents() {
        val target = EventTarget()
        val tuple = MyFancyTuple("42", 56, target)

        assertEquals("42", tuple.component1())
        assertEquals(56, tuple.component2())
        assertEquals(target, tuple.component3())
    }
}
