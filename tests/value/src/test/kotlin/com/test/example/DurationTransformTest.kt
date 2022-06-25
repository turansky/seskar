package com.test.example

import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration
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

    @Test
    fun seconds_13_nullable() {
        val duration = getDuration(13.seconds)

        val array = useArray(duration)
        assertEquals("13s", array[0])
    }

    @Test
    fun minutes_42_nullable() {
        val duration = getDuration(42.minutes)

        val array = useArray(duration)
        assertEquals("42m", array[0])
    }

    @Ignore
    @Test
    fun null_value() {
        val duration: Duration? = getDuration(5.seconds, true)

        val array = useArray(duration)
        assertEquals("null", array[0])
    }
}

private fun getDuration(
    value: Duration,
    ignore: Boolean = false,
): Duration? =
    if (!ignore) value else null
