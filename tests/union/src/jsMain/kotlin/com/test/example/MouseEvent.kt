package com.test.example

import seskar.js.JsValue
import web.events.Event
import web.events.EventTarget
import web.events.EventType

external class MouseEvent<C : EventTarget?> : Event<C> {
    companion object {
        @JsValue("click")
        val CLICK: EventType<MouseEvent<*>>
    }
}
