package com.test.example

import seskar.js.JsValue
import web.events.Event
import web.events.EventType

external class PointerEvent : Event {
    companion object {
        @JsValue("click")
        val CLICK: EventType<PointerEvent>
    }
}
