package com.test.memo

import js.array.component1
import js.array.component2
import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.use.useEventListener
import react.useRefState
import web.dom.ElementId
import web.html.HTMLButtonElement
import web.pointer.CLICK
import web.pointer.PointerEvent

external interface ButtonProps : Props {
    var id: ElementId?
    var title: String?
    var dataCount: Int?

    var onClick: (() -> Unit)?

}

val Button = FC<ButtonProps> { props ->
    val (element, elementRef) = useRefState<HTMLButtonElement>()

    useEventListener(element, PointerEvent.CLICK) {
        props.onClick?.invoke()
    }

    button {
        id = props.id
        ref = elementRef
        title = props.title
        dataCount = props.dataCount
    }
}
