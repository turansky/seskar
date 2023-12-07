package seskar.compiler.typeguard.backend

import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.expressions.IrConst
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.util.getAnnotation
import org.jetbrains.kotlin.name.FqName

private val JS_TYPE_GUARD = FqName("seskar.js.JsTypeGuard")

private fun IrConstructorCall.value(): String {
    val argument = getValueArgument(0) as IrConst<*>
    return argument.value as String
}

internal fun IrProperty.specialName(): String? {
    val name = getAnnotation(JS_TYPE_GUARD)
        ?: return null

    return name.value()
}
