package com.test.memo

import react.FC
import react.use.useRenderCount
import react.use.useUpdate

val VoidCounter = FC {
    val renderCount = useRenderCount()
    val update = useUpdate()

    Button {
        dataCount = renderCount
        onClick = update
    }
}
