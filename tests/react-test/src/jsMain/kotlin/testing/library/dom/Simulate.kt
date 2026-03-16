// TODO: common extensions?

package testing.library.dom

import js.internal.InternalApi
import web.events.Event
import web.events.EventInstance
import web.html.HTMLElement
import web.pointer.PointerEvent

@OptIn(InternalApi::class)
fun EventInstance<Event, HTMLElement, *>.fire() {
    fireEventRaw(target as HTMLElement, Event(type))
}

@OptIn(InternalApi::class)
fun EventInstance<PointerEvent, HTMLElement, *>.fire() {
    fireEventRaw(target as HTMLElement, PointerEvent(type))
}
