package com.test.memo

import react.VFC
import react.dom.html.ReactHTML.div
import react.dom.onChange
import react.use.useRenderCount
import react.use.useUpdate

val VoidCounterContainer = VFC {
    val renderCount = useRenderCount()
    val update = useUpdate()

    div {
        dataCount = renderCount

        onChange = {
            update()
        }

        VoidCounter()
    }
}
