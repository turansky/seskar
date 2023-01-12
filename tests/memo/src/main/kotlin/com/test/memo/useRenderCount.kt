package com.test.memo

import react.useRefValue

fun useRenderCount(): Int {
    var count by useRefValue(0)
    count++
    return count
}
