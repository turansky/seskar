package com.test.memo

import react.FC
import react.use.useRenderCount
import react.use.useUpdate

val VoidCounterContainer = FC {
    val renderCount = useRenderCount()
    val update = useUpdate()

    Box {
        dataCount = renderCount
        onChange = update

        VoidCounter()
    }
}
