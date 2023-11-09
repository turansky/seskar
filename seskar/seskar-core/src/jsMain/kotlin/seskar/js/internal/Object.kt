package seskar.js.internal

internal fun getProperty(
    o: Any,
    propertyName: String,
): Any? =
    o.asDynamic()[propertyName]

internal fun setProperty(
    o: Any,
    propertyName: String,
    value: Any?,
) {
    o.asDynamic()[propertyName] = value
}
