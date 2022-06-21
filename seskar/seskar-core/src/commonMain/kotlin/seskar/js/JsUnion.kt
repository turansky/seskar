package seskar.js

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class JsUnion(
    val case: Case = Case.ORIGINAL,
)

enum class Case {
    ORIGINAL,
    KEBAB,
    SNAKE,

    ;
}

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.BINARY)
annotation class JsInt(
    val value: Int
)

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.BINARY)
annotation class JsString(
    val value: String
)
