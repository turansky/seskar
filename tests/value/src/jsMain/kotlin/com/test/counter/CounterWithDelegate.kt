package com.test.counter

import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.onChange
import react.use.useUpdateEffect
import react.useRefValue
import react.useState

const val COUNTER_WITH_DELEGATE_CONTAINER_ID = "container"

external interface CounterWithDelegateProps : Props {
    var active: Boolean
}

val CounterWithDelegate = FC<CounterWithDelegateProps> { props ->
    val (_, update) = useState(1000)

    var countValue by useRefValue(0)
    var count by useState(Count(countValue))

    val (updateCount, setUpdateCount) = useState(0)

    useUpdateEffect(count) {
        setUpdateCount { it + 1 }
    }

    div {
        id = COUNTER_WITH_DELEGATE_CONTAINER_ID
        dataCount = updateCount

        onChange = {
            update { it + 1 }

            if (props.active) {
                countValue += 1
            }

            count = Count(countValue)
        }

        button {
            title = "First button"
        }

        button {
            title = "Second button"
        }
    }
}
