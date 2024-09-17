package com.test.env

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
