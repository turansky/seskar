package com.test.counter

import js.array.ReadonlyArray

fun useDependencies(
    vararg dependencies: Any?,
): ReadonlyArray<Any?> =
    dependencies
