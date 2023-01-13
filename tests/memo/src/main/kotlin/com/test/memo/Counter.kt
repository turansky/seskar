package com.test.memo

import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.dom.onChange
import react.use.useRenderCount
import react.use.useUpdate

const val COUNTER_ID = "counter"

external interface CounterProps : Props {
    var title: String
}

val Counter = FC<CounterProps> { props ->
    val renderCount = useRenderCount()
    val update = useUpdate()

    button {
        id = COUNTER_ID
        title = props.title
        dataCount = renderCount

        onChange = {
            update()
        }
    }
}
