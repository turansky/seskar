package testing.library.dom

import web.html.HTMLElement

fun HTMLElement.simulateChange() {
    fireEvent[EventType.change]!!(this, undefined)
}

fun HTMLElement.simulateClick() {
    fireEvent[EventType.click]!!(this, undefined)
}
