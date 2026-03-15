// TODO: common extensions?

package testing.library.dom

import web.html.HTMLElement

fun FireEvent.change(
    target: HTMLElement,
) {
    get(EventType.change)!!(target, undefined)
}

fun FireEvent.click(
    target: HTMLElement,
) {
    get(EventType.click)!!(target, undefined)
}
