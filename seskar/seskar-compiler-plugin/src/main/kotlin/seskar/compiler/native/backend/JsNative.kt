package seskar.compiler.native.backend

import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.util.hasAnnotation
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

private class Mapper(
    className: String
) {
    val marker = FqName("seskar.js.$className")

    val annotation = ClassId(
        FqName("seskar.js.internal"),
        Name.identifier("${className}Internal"),
    )
}

private val MAPPERS = listOf(
    Mapper("JsNativeGetter"),
    Mapper("JsNativeSetter"),
    Mapper("JsNativeInvoke"),
)

internal fun IrFunction.nativeAnnotation(): ClassId? =
    MAPPERS.asSequence()
        .filter { hasAnnotation(it.marker) }
        .map { it.annotation }
        .firstOrNull()
