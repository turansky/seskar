package com.test.counter

import js.core.ReadonlyArray

fun useDependencies(
    vararg dependencies: Any?,
): ReadonlyArray<Any?> =
    dependencies
