package com.test.events

import web.events.EventTarget
import kotlin.test.Test
import kotlin.test.assertIs

class MyTargetTest {
    @Test
    fun checkDataEvent() {
        val target = MyTarget()

        assertIs<EventTarget>(target)
    }
}
