package com.test.memo

import react.VFC
import react.dom.html.ReactHTML.button
import react.use.useRenderCount
import react.use.useUpdate

val VoidCounter = VFC {
    val renderCount = useRenderCount()
    val update = useUpdate()

    button {
        dataCount = renderCount

        onClick = {
            update()
        }
    }
}
