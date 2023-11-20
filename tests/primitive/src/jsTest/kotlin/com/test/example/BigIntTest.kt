package com.test.example

import js.core.BigInt
import js.core.n
import kotlin.test.Test
import kotlin.test.assertIs
import kotlin.test.assertIsNot

class BigIntTest {
    @Test
    fun isForNumberBigInt() {
        val s: Any = 13.n

        assertIs<BigInt>(s)
    }

    @Test
    fun isForStringBigInt() {
        val s: Any = "42".n

        assertIs<BigInt>(s)
    }

    @Test
    fun isForObject() {
        val a = Any()

        assertIsNot<BigInt>(a)
    }

    @Test
    fun isForString() {
        val a: Any = "bigint"

        assertIsNot<BigInt>(a)
    }
}
