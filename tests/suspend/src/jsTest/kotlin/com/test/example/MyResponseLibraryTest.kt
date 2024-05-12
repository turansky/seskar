package com.test.example

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class MyResponseLibraryTest {
    @Test
    fun testGetResource() = runTest {
        val data = getResponse("http://github.com")
        assertEquals("http://github.com?37", data)
    }

    @Test
    fun testGetResourceOptional_withPromise() = runTest {
        val data = getResponseOptional("http://github.com")
        assertEquals("http://github.com?23", data)
    }

    @Test
    fun testGetResourceOptional_noPromise() = runTest {
        val data = getResponseOptional("http://github.io")
        assertEquals(null, data)
    }
}
