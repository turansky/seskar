package com.test.memo

import react.VFC
import react.dom.html.ReactHTML.button
import react.use.useRenderCount

val VoidCounter = VFC {
    val renderCount = useRenderCount()

    button {
        dataCount = renderCount
    }
}
