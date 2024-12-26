package seskar.js

@Target(
    AnnotationTarget.PROPERTY,
)
@Retention(AnnotationRetention.BINARY)
annotation class JsRawValue(
    val value: String,
)
