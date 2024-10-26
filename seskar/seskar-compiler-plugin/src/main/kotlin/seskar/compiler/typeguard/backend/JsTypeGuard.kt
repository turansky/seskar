package seskar.compiler.typeguard.backend

import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.util.getAnnotation
import org.jetbrains.kotlin.name.FqName
import seskar.compiler.common.backend.value

internal class TypeGuardData(
    val property: String,
    val value: String,
)

private val JS_TYPE_GUARD = FqName("seskar.js.JsTypeGuard")

internal fun IrClass.typeGuardData(): TypeGuardData? {
    val guard = getAnnotation(JS_TYPE_GUARD)
        ?: return null

    return TypeGuardData(
        property = guard.value(0),
        value = guard.value(1),
    )
}
