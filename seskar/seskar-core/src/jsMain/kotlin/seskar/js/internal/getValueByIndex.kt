package seskar.js.internal

internal actual fun getValueByIndex(
    target: JsArrayInternal<*>,
    index: Int,
): Any? =
    target[index]
