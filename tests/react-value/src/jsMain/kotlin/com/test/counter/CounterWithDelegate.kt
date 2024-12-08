package com.test.counter

import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.use.useUpdate
import react.use.useUpdateEffect
import react.useRefValue
import react.useState

const val COUNTER_WITH_DELEGATE_CONTAINER_ID = "container"

external interface CounterWithDelegateProps : Props {
    var active: Boolean
}

val CounterWithDelegate = FC<CounterWithDelegateProps> { props ->
    val update = useUpdate()

    var countValue by useRefValue(0)
    var count by useState(Count(countValue))

    val (updateCount, setUpdateCount) = useState(0)

    useUpdateEffect(count) {
        setUpdateCount { it + 1 }
    }

    Box {
        id = COUNTER_WITH_DELEGATE_CONTAINER_ID
        dataCount = updateCount

        onChange = {
            update()

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
