package com.test.counter

import web.events.Event
import web.events.EventType

class CountEvent(
    val count: Int,
) : Event(
    type = COUNT,
) {
    companion object {
        val COUNT: EventType<CountEvent> = EventType("count")
    }
}
