package seskar.compiler.value.backend

import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.util.getAnnotation
import org.jetbrains.kotlin.name.FqName

private val JS_VALUE = FqName("seskar.js.JsValue")

internal fun IrClass.isJsValue(): Boolean {
    if (kind != ClassKind.CLASS)
        return false

    if (!isValue)
        return false

    getAnnotation(JS_VALUE)
        ?: return false

    return true
}
