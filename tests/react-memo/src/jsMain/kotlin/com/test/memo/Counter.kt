package com.test.memo

import react.FC
import react.Props
import react.dom.html.ReactHTML.br
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.span
import react.use.useRenderCount
import react.use.useUpdate

const val COUNTER_ID = "counter"

external interface CounterProps : Props {
    var title: String
}

val Counter = FC<CounterProps> { props ->
    val renderCount = useRenderCount()
    val update = useUpdate()

    Button {
        id = COUNTER_ID
        title = props.title
        dataCount = renderCount
        onClick = update
    }

    // move in separate test project
    div {
        div {
            span()
        }

        br()

        div {
            span()
        }
    }
}
