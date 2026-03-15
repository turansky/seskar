package react.dom.test

import testing.library.dom.EventType
import testing.library.dom.change
import testing.library.dom.click
import testing.library.dom.fireEvent
import web.html.HTMLElement

fun HTMLElement.simulateChange() {
    fireEvent[EventType.change]!!(this, undefined)
}

fun HTMLElement.simulateClick() {
    fireEvent[EventType.click]!!(this, undefined)
}
