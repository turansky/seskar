package seskar.compiler.value.backend

import org.jetbrains.kotlin.ir.declarations.IrAnnotationContainer
import org.jetbrains.kotlin.ir.util.getAnnotation
import org.jetbrains.kotlin.name.FqName
import seskar.compiler.common.backend.value

private val JS_RAW_VALUE = FqName("seskar.js.JsRawValue")
private val JS_VALUE = FqName("seskar.js.JsValue")

internal fun IrAnnotationContainer.value(): Value? {
    val jsInt = getAnnotation(JS_RAW_VALUE)
    if (jsInt != null) {
        return IntValue(jsInt.value<String>().toInt())
    }

    val jsString = getAnnotation(JS_VALUE)
    if (jsString != null) {
        return StringValue(jsString.value<String>())
    }

    return null
}
