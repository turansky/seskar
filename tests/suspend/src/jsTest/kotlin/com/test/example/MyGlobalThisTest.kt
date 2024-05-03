package com.test.example

import js.globals.globalThis
import js.promise.Promise
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class MyGlobalThisTest {
    @BeforeTest
    fun setUp() {
        globalThis.getResponse = {
            Promise.resolve(42)
        }
    }

    @Test
    fun testGetResources() = runTest {
        val data = myGlobalThis.getResponse("http://github.com")
        assertEquals(42, data)
    }
}
