package com.test.memo

import react.useCallback
import react.useState

fun useUpdate(): () -> Unit {
    val (_, setTrigger) = useState(0)
    return useCallback {
        setTrigger { it + 1 }
    }
}
