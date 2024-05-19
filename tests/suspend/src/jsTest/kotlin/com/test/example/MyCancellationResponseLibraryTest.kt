package com.test.example

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.time.Duration.Companion.milliseconds

class MyCancellationResponseLibraryTest {
    @Test
    fun testGetCancellableResponseOnlyWithOptions() = runTest {
        val handler = UnhandledExceptionHandler()
        handler.start()

        val job = launch {
            getCancellableResponseOnlyWithOptions()
        }

        delay(100.milliseconds)
        job.cancelAndJoin()
        delay(200.milliseconds)

        assertContentEquals(
            expected = emptyList(),
            actual = handler.finish(),
        )
    }
}
