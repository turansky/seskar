package web.dom

import web.events.Event
import web.events.EventInstance

// TODO: remove after wrappers update
inline val <C : Element> C.changeEvent: EventInstance<Event, C, Node>
    get() = EventInstance(this, "change")
