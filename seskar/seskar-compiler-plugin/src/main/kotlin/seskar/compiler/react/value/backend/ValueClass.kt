package seskar.compiler.react.value.backend

import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.types.getClass

internal fun downCastRequired(
    type: IrType,
): Boolean {
    val klass = type.getClass()
        ?: return false

    return klass.isJsValue()
}
