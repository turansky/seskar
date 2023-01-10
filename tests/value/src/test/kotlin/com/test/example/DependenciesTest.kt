package com.test.example

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DependenciesTest {
    @Test
    fun initial() = runTest {
        assertEquals(1, 1)
    }
}
