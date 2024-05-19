package com.test.example

import js.errors.JsError
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
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
        assertContentEquals(
            expected = listOf(JsError("REQUEST TIMEOUT ERROR")),
            actual = rejectExceptions,
        )
    }
}
