package com.test.counter

import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.onChange
import react.useEffect
import react.useState

const val COUNTER_CONTAINER_ID = "container"

external interface CounterProps : Props {
    var active: Boolean
}

val Counter = FC<CounterProps> { props ->
    val (trigger, setTrigger) = useState(1000)
    val count = useCount(
        trigger = trigger,
        active = props.active,
    )

    val (updateCount, setUpdateCount) = useState(0)

    useEffect(count) {
        setUpdateCount { it + 1 }
    }

    div {
        id = COUNTER_CONTAINER_ID
        dataCount = updateCount

        onChange = {
            setTrigger { it + 1 }
        }

        button {
            title = "First button"
        }

        button {
            title = "Second button"
        }
    }
}
