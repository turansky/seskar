package seskar.compiler.typeguard.backend

import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.expressions.IrConst
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.util.getAnnotation
import org.jetbrains.kotlin.name.FqName

internal class TypeGuardData(
    val property: String,
    val value: String,
)

private val JS_TYPE_GUARD = FqName("seskar.js.JsTypeGuard")

private fun IrConstructorCall.value(index: Int): String {
    val argument = getValueArgument(index) as IrConst<*>
    return argument.value as String
}

internal fun IrProperty.typeGuardData(): TypeGuardData? {
    val guard = getAnnotation(JS_TYPE_GUARD)
        ?: return null

    return TypeGuardData(
        property = guard.value(0),
        value = guard.value(1),
    )
}
