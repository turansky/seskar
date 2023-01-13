package com.test.memo

import react.VFC
import react.dom.html.ReactHTML.div
import react.use.useRendersCount

const val COUNTER_CONTAINER_ID = "counter-container"

val CounterContainer = VFC {
    val renderCount = useRendersCount()

    div {
        id = COUNTER_CONTAINER_ID
        dataCount = renderCount

        Counter()
    }
}
