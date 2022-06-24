package seskar.compiler.value.backend

import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.types.getClass
import org.jetbrains.kotlin.ir.util.kotlinFqName

internal fun downCastRequired(
    type: IrType,
): Boolean {
    val klass = type.getClass()
        ?: return false

    return klass.isJsValue() || klass.kotlinFqName == DURATION
}
