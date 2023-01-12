package com.test.counter

import js.core.globalThis

const val TEST_LOG = "data-count"

fun testLog(
    text: String,
) {
    val oldText: String? = globalThis[TEST_LOG]
    globalThis[TEST_LOG] = if (oldText != null) {
        "$oldText\n$text"
    } else text
}

val windowTestLog: String
    get() = globalThis[TEST_LOG] ?: ""
