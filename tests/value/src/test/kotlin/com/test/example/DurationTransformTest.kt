package com.test.example

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class DurationTransformTest {
    @Test
    fun seconds_13() {
        val duration = 13.seconds

        val array = useArray(duration)
        assertEquals("13s", array[0])
    }

    @Test
    fun minutes_42() {
        val duration = 42.minutes

        val array = useArray(duration)
        assertEquals("42m", array[0])
    }
}
