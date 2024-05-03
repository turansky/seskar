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
        globalThis.getGlobalResponse = {
            Promise.resolve(13)
        }
    }

    @Test
    fun testGetResources() = runTest {
        val data = getGlobalResponse("http://github.com")
        assertEquals(13, data)
    }
}
