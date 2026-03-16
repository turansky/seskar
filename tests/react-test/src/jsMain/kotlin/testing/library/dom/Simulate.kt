// TODO: common extensions?

package testing.library.dom

import js.internal.InternalApi
import js.reflect.Reflect
import web.events.Event
import web.events.EventInstance
import web.html.HTMLElement

@OptIn(InternalApi::class)
inline fun <reified E : Event> EventInstance<E, HTMLElement, *>.fire() {
    val event = Reflect.construct(
        E::class.js,
        arrayOf(type),
    )

    fireEventRaw(target as HTMLElement, event)
}
