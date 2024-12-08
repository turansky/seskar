package com.test.memo

import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.use.useEventListener
import react.useRefState
import web.html.HTMLButtonElement
import web.uievents.MouseEvent

external interface ButtonProps : Props {
    var id: String?
    var title: String?
    var dataCount: Int?

    var onClick: (() -> Unit)?

}

val Button = FC<ButtonProps> { props ->
    val (element, elementRef) = useRefState<HTMLButtonElement>()

    useEventListener(element, MouseEvent.CLICK) {
        props.onClick?.invoke()
    }

    button {
        id = props.id
        ref = elementRef
        title = props.title
        dataCount = props.dataCount
    }
}
