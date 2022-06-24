package com.test.example

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class DurationTransformTest {
    @Test
    fun seconds_13() {
        val array = useArray(13.seconds)
        assertEquals("13s", array[0])
    }

    @Test
    fun minutes_42() {
        val array = useArray(42.minutes)
        assertEquals("42m", array[0])
    }
}