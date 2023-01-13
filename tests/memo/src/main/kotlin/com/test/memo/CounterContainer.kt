package com.test.memo

import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.onChange
import react.use.useRenderCount
import react.use.useUpdate

const val COUNTER_CONTAINER_ID = "counter-container"

external interface CounterContainerProps : Props {
    var title: String
}

val CounterContainer = FC<CounterContainerProps> {
    val renderCount = useRenderCount()
    val update = useUpdate()

    div {
        id = COUNTER_CONTAINER_ID
        dataCount = renderCount

        onChange = {
            update()
        }

        Counter {
            title = "Counter"
        }
    }
}
