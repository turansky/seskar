package seskar.compiler.value.backend

import org.jetbrains.kotlin.ir.declarations.IrAnnotationContainer
import org.jetbrains.kotlin.ir.util.getAnnotation
import org.jetbrains.kotlin.name.FqName
import seskar.compiler.common.backend.value

private val JS_INT_VALUE = FqName("seskar.js.JsIntValue")
private val JS_VALUE = FqName("seskar.js.JsValue")

internal fun IrAnnotationContainer.value(): Value? {
    val jsInt = getAnnotation(JS_INT_VALUE)
    if (jsInt != null) {
        return IntValue(jsInt.value<Int>())
    }

    val jsString = getAnnotation(JS_VALUE)
    if (jsString != null) {
        return StringValue(jsString.value<String>())
    }

    return null
}
