package com.test.counter

import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.use.useUpdateEffect
import react.useState

external interface CounterProps : Props {
    var active: Boolean
}

val Counter = FC<CounterProps> { props ->
    val (count, update) = useCountState(props.active)
    val (updateCount, setUpdateCount) = useState(0)

    useUpdateEffect(count) {
        setUpdateCount { it + 1 }
    }

    Box {
        dataCount = updateCount
        onChange = update

        button {
            title = "First button"
        }

        button {
            title = "Second button"
        }
    }
}
