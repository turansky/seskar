package seskar.compiler.alias.backend

import org.jetbrains.kotlin.ir.declarations.IrDeclarationBase
import org.jetbrains.kotlin.ir.util.getAnnotation
import org.jetbrains.kotlin.name.FqName
import seskar.compiler.common.backend.value

private val JS_ALIAS = FqName("seskar.js.JsAlias")

sealed class Alias

object ThisAlias : Alias()
class IndexedAccessAlias(
    val index: Int,
) : Alias()

class PropertyAlias(
    val name: String,
) : Alias()

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

    val value = annotation.value<String>()
    return parseAlias(value)
        ?: error("Invalid alias value: '$value'")
}
