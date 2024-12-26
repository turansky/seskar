package com.test.example

import kotlin.test.Test

class DoubleItemTypeTest {
    @Test
    fun testPositive() {
        assertEquals(1.5, DoubleItemType.P_1_5)
        assertEquals(234.769, DoubleItemType.P_234_769)
    }

    @Test
    fun testNegative() {
        assertEquals(-1.5, DoubleItemType.N_1_5)
        assertEquals(-234.769, DoubleItemType.N_234_769)
    }
}
