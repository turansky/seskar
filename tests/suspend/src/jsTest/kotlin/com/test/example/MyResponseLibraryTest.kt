package com.test.example

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class MyResponseLibraryTest {
    @Test
    fun testGetResources() = runTest {
        val data = getResponse("http://github.com")
        assertEquals("http://github.com?37", data)
    }
}
