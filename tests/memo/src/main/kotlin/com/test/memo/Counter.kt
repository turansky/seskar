package com.test.memo

import react.VFC
import react.dom.html.ReactHTML.button
import react.use.useRenderCount

const val COUNTER_ID = "counter"

val Counter = VFC {
    val renderCount = useRenderCount()

    button {
        id = COUNTER_ID
        dataCount = renderCount
    }
}
