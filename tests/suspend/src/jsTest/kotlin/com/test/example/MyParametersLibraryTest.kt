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

    @Test
    fun oneParameter() = runTest {
        val data = getParameters("aaa")
        assertContentEquals(arrayOf("aaa", undefined, undefined), data)
    }

    @Test
    fun twoParameter() = runTest {
        val data = getParameters(a = "aaa", c = "ccc")
        assertContentEquals(arrayOf("aaa", undefined, "ccc"), data)
    }
}
