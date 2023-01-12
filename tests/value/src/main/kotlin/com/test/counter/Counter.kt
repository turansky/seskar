package com.test.counter

import com.test.example.Count
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
    val (trigger, setTrigger) = useState(Count(1000))

    val (count, setCount) = useState(0)

    useEffect(trigger) {
        setCount { it + 1 }
    }

    div {
        id = COUNTER_CONTAINER_ID
        dataCount = count

        onChange = {
            val diff = if (props.active) 1 else 0
            setTrigger {
                Count(it.value + diff)
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
