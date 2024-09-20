package com.test.lazy

import kotlinx.coroutines.test.runTest
import kotlin.js.Promise
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import js.lazy.invoke

class LazyFunctionTest {
    @Test
    fun lazyFunction1() = runTest {
        assertIs<Promise<*>>(lazyFunction1)

        val data = lazyFunction1.invoke()

        assertEquals("42", data)
    }

    @Test
    fun lazyFunction2() = runTest {
        assertIs<Promise<*>>(lazyFunction2)

        val data = lazyFunction2.invoke()

        assertEquals("13", data)
    }
}
