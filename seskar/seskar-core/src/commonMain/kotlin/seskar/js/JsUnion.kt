package seskar.js

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class JsUnion

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
