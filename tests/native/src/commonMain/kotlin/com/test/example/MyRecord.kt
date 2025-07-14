package com.test.example

sealed external interface MyRecord {
    operator fun get(
        key: String,
    ): Int

    operator fun set(
        key: String,
        value: Int,
    )
}
