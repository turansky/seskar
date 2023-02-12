package react.dom.test

import web.html.HTMLElement

suspend fun HTMLElement.simulateChange() {
    act {
        Simulate.change(this)
    }
}

suspend fun HTMLElement.simulateClick() {
    act {
        Simulate.click(this)
    }
}
