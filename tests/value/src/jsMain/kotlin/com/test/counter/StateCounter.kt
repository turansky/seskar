package com.test.counter

import react.VFC
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.onChange
import react.use.useUpdateEffect
import react.useState

val StateCounter = VFC {
    val (count, setCount) = useState(Count(42))
    val (updateCount, setUpdateCount) = useState(0)

    useUpdateEffect(count) {
        setUpdateCount { it + 1 }
    }

    div {
        id = COUNTER_CONTAINER_ID
        dataCount = updateCount

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
