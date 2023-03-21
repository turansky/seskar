@file:Suppress(
    "NOTHING_TO_INLINE",
)

package seskar.react

internal inline fun <T : Any> withDisplayName(
    target: T,
    displayName: String,
): T {
    if (process.env.NODE_ENV !== "production") {
        target.asDynamic().displayName = displayName
    }

    return target
}
