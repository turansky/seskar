package com.test.events

import web.events.Event
import web.events.EventInstance
import web.events.JsEvent

@JsName("EventTarget")
external class MyTarget {
    @JsEvent("data")
    val dataEvent: EventInstance<Event, *, *>
}
