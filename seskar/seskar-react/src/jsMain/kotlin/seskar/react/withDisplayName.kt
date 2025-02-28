package seskar.react

private external val process: dynamic

internal inline fun <T : Any> withDisplayName(
    target: T,
    displayName: String,
): T {
    if (process.env.NODE_ENV !== "production") {
        if (!target.asDynamic().displayName) {
            target.asDynamic().displayName = displayName
        }
    }

    return target
}
