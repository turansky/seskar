@file:Suppress(
    "NOTHING_TO_INLINE",
)

package seskar.js.internal

internal inline fun hasType(
    o: Any?,
    type: String,
): Boolean =
    jsTypeOf(o) == type
