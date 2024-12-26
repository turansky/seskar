package com.test.example

import js.core.BigInt

fun assertEquals(
    expected: String,
    actual: Any,
) {
    kotlin.test.assertEquals<Any>(expected = expected, actual = actual)
}

fun assertEquals(
    expected: Boolean,
    actual: Any,
) {
    kotlin.test.assertEquals<Any>(expected = expected, actual = actual)
}

fun assertEquals(
    expected: Number,
    actual: Any,
) {
    kotlin.test.assertEquals<Any>(expected = expected, actual = actual)
}

fun assertEquals(
    expected: BigInt,
    actual: Any,
) {
    kotlin.test.assertEquals<Any>(expected = expected, actual = actual)
}
