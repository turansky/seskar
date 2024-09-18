package com.test.lazy

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class LazyFunctionTest {
    @Test
    fun lazyFunction1() = runTest {
        val data = lazyFunction1.invoke()

        assertEquals("42", data)
    }

    @Test
    fun lazyFunction2() = runTest {
        val data = lazyFunction2.invoke()

        assertEquals("13", data)
    }
}
