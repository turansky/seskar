package com.test.memo

import react.VFC
import react.dom.html.ReactHTML.button

const val COUNTER_CONTAINER_ID = "counter-container"

val CounterContainer = VFC {
    val renderCount = useRenderCount()

    button {
        id = COUNTER_CONTAINER_ID
        dataCount = renderCount
    }
}
