package com.test.example

fun <T : Enum<T>> assertEquals(
    expected: String,
    actual: T,
) {
    kotlin.test.assertEquals<Any>(expected = expected, actual = actual)
}

fun <T : Enum<T>> assertEquals(
    expected: Int,
    actual: T,
) {
    kotlin.test.assertEquals<Any>(expected = expected, actual = actual)
}
