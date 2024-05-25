@file:Suppress(
    "NOTHING_TO_INLINE",
)

package seskar.js.internal

internal inline fun getIndexedValue(
    target: dynamic,
    index: Int,
): Any? =
    target[index]
