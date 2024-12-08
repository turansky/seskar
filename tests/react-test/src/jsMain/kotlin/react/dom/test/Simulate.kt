package react.dom.test

import react.act
import web.events.Event
import web.html.HTMLElement
import web.uievents.MouseEvent

suspend fun HTMLElement.simulateChange() {
    act {
        dispatchEvent(Event(Event.CHANGE))
    }
}

suspend fun HTMLElement.simulateClick() {
    act {
        dispatchEvent(MouseEvent(MouseEvent.CLICK))
    }
}
