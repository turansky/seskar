package com.test.example

import kotlin.test.Test

class BooleanItemTypeTest {
    @Test
    fun testFalse() {
        assertEquals(false, BooleanItemType.f)
    }

    @Test
    fun testTrue() {
        assertEquals(true, BooleanItemType.t)
    }
}
