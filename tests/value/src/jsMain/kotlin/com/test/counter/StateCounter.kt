package com.test.counter

import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.onChange
import react.use.useRenderCount
import react.useState

external interface StateCounterProps : Props {
    var active: Boolean
}

val StateCounter = FC<StateCounterProps> { props ->
    val (_, setCount) = useState(Count(42))

    val renderCount = useRenderCount()

    div {
        dataCount = renderCount

        onChange = {
            setCount {
                Count(it.value + (if (props.active) 1 else 0))
            }
        }

        button {
            title = "First button"
        }

        button {
            title = "Second button"
        }
    }
}
