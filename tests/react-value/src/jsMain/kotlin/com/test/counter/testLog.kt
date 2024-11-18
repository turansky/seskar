package com.test.counter

import js.globals.GlobalScope
import js.globals.globalThis

private const val DATA_LOG = "data-log"

private var GlobalScope.dataLog: String?
    get() = get(DATA_LOG) as String?
    set(value) {
        set(DATA_LOG, value)
    }

fun testLog(
    text: String,
) {
    val oldText: String? = globalThis.dataLog
    globalThis.dataLog = if (oldText != null) {
        "$oldText\n$text"
    } else text
}

val windowTestLog: String
    get() = globalThis.dataLog ?: ""
