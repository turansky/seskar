package com.test.memo

import react.VFC
import react.dom.html.ReactHTML.button

const val COUNTER_ID = "counter"

val Counter = VFC {
    val renderCount = useRenderCount()

    button {
        id = COUNTER_ID
        dataCount = renderCount
    }
}
