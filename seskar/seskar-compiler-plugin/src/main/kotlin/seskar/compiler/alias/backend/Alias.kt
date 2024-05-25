package seskar.compiler.alias.backend

import org.jetbrains.kotlin.ir.declarations.IrDeclarationBase
import org.jetbrains.kotlin.ir.expressions.IrConst
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.util.getAnnotation
import org.jetbrains.kotlin.name.FqName

private val JS_ALIAS = FqName("seskar.js.JsAlias")

sealed class Alias

object ThisAlias : Alias()
class IndexedAccessAlias(
    val index: Int,
) : Alias()

class PropertyAlias(
    val name: String,
) : Alias()

private fun IrConstructorCall.value(): String {
    val argument = getValueArgument(0) as IrConst<*>
    return argument.value as String
}

private fun parseAlias(
    value: String,
): Alias? {
    if (value == "<this>")
        return ThisAlias

    if (value.startsWith("[")) {
        val index = value.removeSurrounding("[", "]")
            .toIntOrNull()
            ?: return null

        if (index < 0 || index >= 1000)
            return null

        return IndexedAccessAlias(index = index)
    }

    return PropertyAlias(name = value)
}

internal fun IrDeclarationBase.alias(): Alias? {
    val annotation = getAnnotation(JS_ALIAS)
        ?: return null

    val value = annotation.value()
    return parseAlias(value)
        ?: error("Invalid alias value: '$value'")
}
