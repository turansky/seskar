package com.test.events

import web.events.EventInstance
import kotlin.test.Test
import kotlin.test.assertIs

class MyTargetTest {
    @Test
    fun checkDataEvent() {
        val target = MyTarget()
        val dataEvent = target.dataEvent

        assertIs<EventInstance<*, *, *>>(dataEvent)
    }
}
