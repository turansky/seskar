package com.test.example

import js.core.BigInt
import js.core.n
import js.core.unaryMinus
import kotlin.test.Test

class BigIntItemTypeTest {
    @Test
    fun testPositive() {
        assertEquals(15.n, BigIntItemType.P_15)
        assertEquals(234769.n, BigIntItemType.P_234769)
    }

    @Test
    fun testLongPositive() {
        assertEquals(
            BigInt("23476913131313131313131313131313131313131313131313131313234769"),
            BigIntItemType.P_23476913131313131313131313131313131313131313131313131313234769,
        )
    }

    @Test
    fun testNegative() {
        assertEquals(-15.n, BigIntItemType.N_15)
        assertEquals(-234769.n, BigIntItemType.N_234769)
    }

    @Test
    fun testLongNegative() {
        assertEquals(
            BigInt("-23476913131313131313131313131313131313131313131313131313234769"),
            BigIntItemType.N_23476913131313131313131313131313131313131313131313131313234769,
        )
    }
}
