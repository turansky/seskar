package com.test.example

import js.errors.JsError
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.time.Duration.Companion.milliseconds

class MyCancellationResponseLibraryTest {
    @Test
    fun testGetCancellableResponseOnlyWithOptions() = runTest {
        val collector = PromiseRejectionCollector()

        val job = launch {
            getCancellableResponseOnlyWithOptions()
        }

        delay(100.milliseconds)
        job.cancel()
        delay(200.milliseconds)

        assertContentEquals(
            expected = listOf(JsError("REQUEST TIMEOUT ERROR")),
            actual = collector.leave(),
        )
    }
}
