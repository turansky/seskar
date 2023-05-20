package com.test.memo

import com.test.env.BUILD_NUMBER
import com.test.env.NUMBER
import kotlin.test.Test
import kotlin.test.assertEquals

class EnvTest {
    @Test
    fun buildNumber() {
        assertEquals("generic-number", BUILD_NUMBER)
    }

    @Test
    fun vfc() {
        assertEquals("42", NUMBER)
    }
}
