package io.github.turansky.seskar.compiler.backend

private val LOWER_TO_UPPER = Regex("([a-z])([A-Z])")

internal fun String.kebab(): String =
    snake().replace("_", "-")

internal fun String.snake(): String =
    replace(LOWER_TO_UPPER, "$1_$2")
        .lowercase()
