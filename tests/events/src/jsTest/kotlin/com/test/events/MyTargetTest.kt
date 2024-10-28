package com.test.events

import kotlin.test.Test
import kotlin.test.assertNotNull

class MyTargetTest {
    @Test
    fun checkDataEvent() {
        val target = MyTarget()
        val dataEvent = target.dataEvent

        assertNotNull(dataEvent)
    }
}
