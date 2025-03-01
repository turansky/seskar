package seskar.js.internal

@Deprecated("Remove after wrappers release!")
internal inline fun getIndexedValue(
    target: dynamic,
    index: Int,
): Any? =
    target[index]
