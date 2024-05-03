package com.test.example

import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@Ignore
class GlobalThisTest {
    @Test
    fun testGetResources() = runTest {
        val data = localGlobalThis.getResponse("http://github.com")
        assertEquals(42, data)
    }
}
