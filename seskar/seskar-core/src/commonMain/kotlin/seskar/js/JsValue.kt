package seskar.js

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.BINARY)
annotation class JsIntValue(
    val value: Int,
)

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.PROPERTY,
)
@Retention(AnnotationRetention.BINARY)
annotation class JsValue(
    val value: String,
)
