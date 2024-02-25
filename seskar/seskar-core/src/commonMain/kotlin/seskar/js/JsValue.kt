package seskar.js

@Target(
    AnnotationTarget.PROPERTY,
    AnnotationTarget.FUNCTION,
)
@Retention(AnnotationRetention.BINARY)
annotation class JsIntValue(
    val value: Int,
)

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.FUNCTION,
)
@Retention(AnnotationRetention.BINARY)
annotation class JsValue(
    val value: String,
)
