package com.test.memo

import react.displayName
import kotlin.test.Test
import kotlin.test.assertEquals

class DisplayNameTest {
    @Test
    fun fc() {
        assertEquals(Counter.displayName, "Counter")
    }

    @Test
    fun vfc() {
        assertEquals(VoidCounter.displayName, "VoidCounter")
    }
}
