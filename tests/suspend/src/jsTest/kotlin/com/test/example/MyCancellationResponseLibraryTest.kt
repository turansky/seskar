package com.test.example

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.time.Duration.Companion.milliseconds

class MyCancellationResponseLibraryTest {
    @Test
    fun testGetCancellableResponseOnlyWithOptions() = runTest {
        val job = launch {
            getCancellableResponseOnlyWithOptions()
        }

        delay(100.milliseconds)
        job.cancelAndJoin()
        delay(200.milliseconds)
    }
}
