// TODO: common extensions?

package testing.library.dom

import js.internal.InternalApi
import react.act
import web.events.Event
import web.events.EventInstance
import web.html.HTMLElement
import web.pointer.PointerEvent

@OptIn(InternalApi::class)
suspend fun EventInstance<Event, HTMLElement, *>.fire() {
    act {
        target.dispatchEvent(Event(type))
    }
}

@OptIn(InternalApi::class)
suspend fun EventInstance<PointerEvent, HTMLElement, *>.fire() {
    act {
        target.dispatchEvent(PointerEvent(type))
    }
}
