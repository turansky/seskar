package seskar.js

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
annotation class JsAlias(
    val value: String,
)
