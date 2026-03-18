package com.test.example

import js.numbers.BigInt

sealed external interface MyRecord {
    operator fun get(
        key: String,
    ): BigInt

    operator fun set(
        key: String,
        value: BigInt,
    )
}
