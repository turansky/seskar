package com.test.example

fun assertEquals(
    expected: String,
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
