package com.test.example

import kotlin.test.Test

class GraphItemTypeTest {
    @Test
    fun test() {
        assertEquals(1, GraphItemType.NODE)
        assertEquals(2, GraphItemType.EDGE)
        assertEquals(4, GraphItemType.PORT)
        assertEquals(8, GraphItemType.LABEL)
    }
}
