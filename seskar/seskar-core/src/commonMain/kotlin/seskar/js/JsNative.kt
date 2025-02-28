package seskar.js

@Deprecated(
    "Please use more specific annotation",
    level = DeprecationLevel.ERROR,
)

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
annotation class JsNative
