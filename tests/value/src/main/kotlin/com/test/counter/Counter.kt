package com.test.counter

import com.test.example.Count
import react.*
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.onChange

const val COUNTER_CONTAINER_ID = "container"

external interface CounterProps : Props {
    var active: Boolean
}

val Counter = FC<CounterProps> { props ->
    val (trigger, setTrigger) = useState(1000)

    var countValue by useRefValue(0)
    val count = useMemo(trigger) {
        countValue += if (props.active) 1 else 0
        Count(countValue)
    }

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
