package com.test.example

import kotlinx.coroutines.Deferred

suspend fun <T> Deferred<T>.toResult(): Result<T> =
    try {
        Result.success(await())
    } catch (e: Throwable) {
        Result.failure(e)
    }
