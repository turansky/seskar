package seskar.js

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.BINARY)
annotation class JsSpecialName(
    val value: String,
)
