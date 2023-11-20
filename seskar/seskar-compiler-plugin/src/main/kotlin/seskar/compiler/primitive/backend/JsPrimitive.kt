package seskar.compiler.primitive.backend

import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.types.getClass
import org.jetbrains.kotlin.ir.util.hasAnnotation
import org.jetbrains.kotlin.name.FqName

private val JS_PRIMITIVE = FqName("seskar.js.JsPrimitive")

internal fun getPrimitiveTypeName(
    type: IrType,
): String? {
    val clazz = type.getClass()
        ?: return null

    if (!clazz.hasAnnotation(JS_PRIMITIVE))
        return null

    return clazz.name.identifier
}
