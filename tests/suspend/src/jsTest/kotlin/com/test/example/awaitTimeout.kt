package com.test.example

import kotlinx.coroutines.suspendCancellableCoroutine
import web.timers.clearTimeout
import web.timers.setTimeout
import kotlin.coroutines.resume
import kotlin.time.Duration

suspend fun awaitTimeout(
    timeout: Duration,
) {
    suspendCancellableCoroutine { continuation ->
        val id = setTimeout(timeout) {
            continuation.resume(Unit)
        }

        continuation.invokeOnCancellation {
            clearTimeout(id)
        }
    }
}
