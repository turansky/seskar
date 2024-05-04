package com.test.example

import js.globals.globalThis
import js.promise.Promise
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class getGlobalResponseTest {
    @BeforeTest
    fun setUp() {
        globalThis.getGlobalResponse = { url: String ->
            Promise.resolve("$url?13")
        }
    }

    @Test
    fun testGetResources() = runTest {
        val data = getGlobalResponse("http://github.com")
        assertEquals("http://github.com?13", data)
    }
}
