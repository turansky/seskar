package com.test.counter

import js.array.component1
import js.array.component2
import react.FC
import react.PropsWithChildren
import react.dom.html.ReactHTML.div
import react.use.useEventListener
import react.useRefState
import web.dom.ElementId
import web.events.CHANGE
import web.events.Event
import web.html.HTMLDivElement

external interface BoxProps :
    PropsWithChildren {
    var id: ElementId?
    var dataCount: Int?
    var onChange: (() -> Unit)?
}

val Box = FC<BoxProps> { props ->
    val (element, elementRef) = useRefState<HTMLDivElement>()

    useEventListener(element, Event.CHANGE) {
        props.onChange?.invoke()
    }

    div {
        id = props.id
        ref = elementRef
        dataCount = props.dataCount

        +props.children
    }
}
