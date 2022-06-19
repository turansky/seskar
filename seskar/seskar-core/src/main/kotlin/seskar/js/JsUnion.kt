package seskar.js

import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.PROPERTY

@Target(CLASS)
annotation class JsUnion(
    val case: Case = Case.ORIGINAL,
)

enum class Case {
    ORIGINAL,
    KEBAB,
    SNAKE,

    ;
}

@Target(PROPERTY)
annotation class JsInt(
    val value: Int
)

@Target(PROPERTY)
annotation class JsString(
    val value: String
)
