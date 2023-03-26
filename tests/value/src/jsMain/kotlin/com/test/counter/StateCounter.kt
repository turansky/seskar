package com.test.counter

import react.VFC
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.onChange
import react.use.useRenderCount
import react.useState

val StateCounter = VFC {
    val (_, setCount) = useState(Count(42))

    val renderCount = useRenderCount()

    div {
        dataCount = renderCount

        onChange = {
            setCount(Count(42))
        }

        button {
            title = "First button"
        }

        button {
            title = "Second button"
        }
    }
}
