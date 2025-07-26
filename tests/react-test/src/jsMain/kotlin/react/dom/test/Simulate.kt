package react.dom.test

import react.act
import web.events.CHANGE
import web.events.Event
import web.html.HTMLElement
import web.pointer.CLICK
import web.pointer.PointerEvent

suspend fun HTMLElement.simulateChange() {
    act {
        dispatchEvent(Event(Event.CHANGE))
    }
}

suspend fun HTMLElement.simulateClick() {
    act {
        dispatchEvent(PointerEvent(PointerEvent.CLICK))
    }
}
