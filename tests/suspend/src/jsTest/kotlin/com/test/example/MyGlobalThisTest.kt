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
        globalThis.getResponse = { url: String ->
            Promise.resolve("$url?42")
        }
    }

    @Test
    fun testGetResources() = runTest {
        val data = myGlobalThis.getResponse("http://github.com")
        assertEquals("http://github.com?42", data)
    }
}
