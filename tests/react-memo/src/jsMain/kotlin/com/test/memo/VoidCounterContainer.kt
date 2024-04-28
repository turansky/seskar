package com.test.memo

import react.FC
import react.dom.html.ReactHTML.div
import react.dom.onChange
import react.use.useRenderCount
import react.use.useUpdate

val VoidCounterContainer = FC {
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
