package seskar.compiler.value.backend

import org.jetbrains.kotlin.ir.declarations.IrAnnotationContainer
import org.jetbrains.kotlin.ir.util.getAnnotation
import org.jetbrains.kotlin.name.FqName
import seskar.compiler.common.backend.value

private val JS_RAW_VALUE = FqName("seskar.js.JsRawValue")
private val JS_VALUE = FqName("seskar.js.JsValue")

internal fun IrAnnotationContainer.value(): Value? {
    val jsRaw = getAnnotation(JS_RAW_VALUE)
    if (jsRaw != null) {
        val value = jsRaw.value<String>()
        val intValue = value.toIntOrNull()
        if (intValue != null)
            return IntValue(intValue)

        error("Unable to parse raw value: '$value'")
    }

    val jsString = getAnnotation(JS_VALUE)
    if (jsString != null) {
        return StringValue(jsString.value<String>())
    }

    return null
}
