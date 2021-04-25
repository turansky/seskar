package seskar.js

import kotlin.annotation.AnnotationTarget.PROPERTY

@Target(PROPERTY)
annotation class JsValue(
    val value: String
)
