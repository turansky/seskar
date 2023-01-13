package com.test.memo

import react.VFC
import react.dom.html.ReactHTML.div
import react.dom.onChange
import react.use.useRenderCount
import react.use.useUpdate

const val COUNTER_CONTAINER_ID = "counter-container"

val CounterContainer = VFC {
    val renderCount = useRenderCount()
    val update = useUpdate()

    div {
        id = COUNTER_CONTAINER_ID
        dataCount = renderCount

        onChange = {
            update()
        }

        Counter()
    }
}
