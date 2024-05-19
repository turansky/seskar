package com.test.example

import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.milliseconds

class MyCancellationResponseLibraryTest {
    @Test
    fun testGetCancellableResponseOnlyWithOptions() = runTest {
        val collector = PromiseRejectionCollector()

        val dataJob = launch {
            getCancellableResponseOnlyWithOptions()
        }

        launch {
            awaitTimeout(100.milliseconds)
            dataJob.cancel()
        }

        awaitTimeout(300.milliseconds)

        val rejectExceptions = collector.leave()

        assertEquals(1, rejectExceptions.size)

        assertEquals(
            "REQUEST TIMEOUT ERROR",
            rejectExceptions.single().message,
        )
    }
}
