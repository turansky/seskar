package seskar.js.internal

internal fun hasType(
    o: Any,
    type: String,
): Boolean =
    jsTypeOf(o) == type
