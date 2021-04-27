package com.test.example

import kotlin.test.Test
import kotlin.test.assertEquals

class GraphItemTypeTest {
    @Test
    fun test() {
        assertEquals<Any>(1, GraphItemType.NODE)
        assertEquals<Any>(2, GraphItemType.EDGE)
        assertEquals<Any>(4, GraphItemType.PORT)
        assertEquals<Any>(8, GraphItemType.LABEL)
    }
}
