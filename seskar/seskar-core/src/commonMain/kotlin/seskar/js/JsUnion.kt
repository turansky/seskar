package seskar.js

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class JsUnion

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.BINARY)
annotation class JsIntValue(
    val value: Int,
)

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.BINARY)
annotation class JsString(
    val value: String
)
