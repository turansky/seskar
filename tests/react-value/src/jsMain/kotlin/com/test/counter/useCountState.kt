package com.test.counter

import react.useCallback
import react.useMemo
import react.useRefValue
import react.useState

data class CountState(
    val count: Count,
    val update: () -> Unit,
)

fun useCountState(
    active: Boolean,
): CountState {
    val (trigger, setTrigger) = useState(1000)
    var countValue by useRefValue(0)

    val count = useMemo(trigger) {
        countValue += if (active) 1 else 0
        Count(countValue)
    }

    val update = useCallback {
        setTrigger { it + 1 }
    }

    return CountState(count, update)
}
