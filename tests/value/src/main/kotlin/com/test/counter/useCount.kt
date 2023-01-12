package com.test.counter

import react.useMemo
import react.useRefValue

fun useCount(
    trigger: Int,
    active: Boolean,
): Count {
    var countValue by useRefValue(0)
    return useMemo(trigger) {
        countValue += if (active) 1 else 0
        Count(countValue)
    }
}
