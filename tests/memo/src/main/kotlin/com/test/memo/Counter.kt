package com.test.memo

import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.use.useRenderCount

const val COUNTER_ID = "counter"

external interface CounterProps : Props {
    var title: String
}

val Counter = FC<CounterProps> { props ->
    val renderCount = useRenderCount()

    button {
        id = COUNTER_ID
        title = props.title

        dataCount = renderCount
    }
}
