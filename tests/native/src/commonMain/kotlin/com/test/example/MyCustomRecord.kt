package com.test.example

external interface MyCustomRecord {
    operator fun get(
        key: String,
        default: () -> Int,
    ): Int
}
