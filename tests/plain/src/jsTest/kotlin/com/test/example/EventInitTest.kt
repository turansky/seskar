package com.test.example

import js.objects.jso
import web.events.EventInit
import kotlin.test.Test
import kotlin.test.assertTrue

class EventInitTest {
    @Test
    fun test() {
        // val init: MyEventInit = jso {
        val init: EventInit = jso {
            bubbles = true
            cancelable = true
        }

        assertTrue(init.bubbles == true)
    }
}
