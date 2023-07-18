package com.test.example

import com.test.counter.useDependencies
import kotlin.test.Test
import kotlin.test.assertEquals

class NullableCountTest {
    @Test
    fun null_count() {
        val count = useNullableCount(nonnull = false)
        val dependencies = useDependencies(count)

        assertEquals(null, dependencies[0])
    }

    @Test
    fun nonnull_count() {
        val count = useNullableCount(nonnull = true)
        val dependencies = useDependencies(count)

        assertEquals(42, dependencies[0])
    }
}

private fun useNullableCount(
    nonnull: Boolean,
): Count? =
    if (nonnull) {
        Count(42)
    } else {
        null
    }
