package com.test.example

import com.test.counter.useDependencies
import kotlin.test.Test
import kotlin.test.assertEquals

class NullableAgeTest {
    @Test
    fun null_age() {
        val age = useNullableAge(nonnull = false)
        val dependencies = useDependencies(age)

        assertEquals(null, dependencies[0])
    }

    @Test
    fun nonnull_age() {
        val age = useNullableAge(nonnull = true)
        val dependencies = useDependencies(age)

        assertEquals(42, dependencies[0])
    }
}

private fun useNullableAge(
    nonnull: Boolean,
): Age? =
    if (nonnull) {
        Age(42)
    } else {
        null
    }
