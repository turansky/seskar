package com.test.example

fun <T : Any> assertEquals(
    expected: String,
    actual: T,
) {
    kotlin.test.assertEquals<Any>(expected = expected, actual = actual)
}

fun <T : Any> assertEquals(
    expected: Int,
    actual: T,
) {
    kotlin.test.assertEquals<Any>(expected = expected, actual = actual)
}
