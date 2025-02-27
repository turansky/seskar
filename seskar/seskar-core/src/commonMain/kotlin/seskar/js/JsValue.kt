package seskar.js

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.PROPERTY,
)
@Retention(AnnotationRetention.BINARY)
annotation class JsValue(
    val value: String,
)
