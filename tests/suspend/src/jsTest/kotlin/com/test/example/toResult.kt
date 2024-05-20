package com.test.example

suspend fun <T> toResult(
    block: suspend () -> T,
): Result<T> =
    try {
        Result.success(block())
    } catch (e: Throwable) {
        Result.failure(e)
    }
