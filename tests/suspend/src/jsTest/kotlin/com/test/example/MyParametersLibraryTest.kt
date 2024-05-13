package com.test.example

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class MyParametersLibraryTest {
    @Test
    fun allParameters() = runTest {
        val data = getParameters("aaa", "bbb", "ccc")
        assertContentEquals(arrayOf("aaa", "bbb", "ccc"), data)
    }
}
