package com.test.memo

import react.VFC
import react.dom.html.ReactHTML.div
import react.use.useRenderCount

const val COUNTER_CONTAINER_ID = "counter-container"

val CounterContainer = VFC {
    val renderCount = useRenderCount()

    div {
        id = COUNTER_CONTAINER_ID
        dataCount = renderCount

        Counter()
    }
}
