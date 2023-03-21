package seskar.react

inline val isDevelopmentMode: Boolean
    get() = process.env.NODE_ENV !== "production"

@PublishedApi
internal external val process: dynamic
