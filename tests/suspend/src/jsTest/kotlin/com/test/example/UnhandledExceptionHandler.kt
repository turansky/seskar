@file:Suppress(
    "INVISIBLE_MEMBER",
    "INVISIBLE_REFERENCE",
)

package com.test.example

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.internal.ensurePlatformExceptionHandlerLoaded
import kotlin.coroutines.CoroutineContext

class UnhandledExceptionHandler {
    private val collector = ExceptionCollector()

    fun start() {
        ensurePlatformExceptionHandlerLoaded(collector)
    }

    fun finish(): List<Throwable> =
        collector.exceptions
}

class ExceptionCollector : CoroutineExceptionHandler {
    override val key: CoroutineContext.Key<*> = CoroutineExceptionHandler

    private val _exceptions = mutableListOf<Throwable>()

    val exceptions: List<Throwable>
        get() = _exceptions.toList()

    override fun handleException(
        context: CoroutineContext,
        exception: Throwable,
    ) {
        _exceptions += exception
    }
}
