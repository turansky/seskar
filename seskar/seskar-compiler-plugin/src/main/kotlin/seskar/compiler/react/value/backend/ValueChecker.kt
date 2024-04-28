package seskar.compiler.react.value.backend

import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.types.*
import org.jetbrains.kotlin.ir.util.constructors

internal fun checkValue(
    klass: IrClass,
) {
    val constructor = klass.constructors.singleOrNull()
        ?: return // TODO: throw error

    val value = constructor.valueParameters.singleOrNull()
        ?: return // TODO: throw error

    val type = value.type

    if (type.isMarkedNullable()) {
        throw IllegalArgumentException("Value class `${klass.name.identifier}` has nullable value and can't be used as hook dependency")
    }

    if (!validValueType(type)) {
        throw IllegalArgumentException("Value class `${klass.name.identifier}` has non-supported value type and can't be used as hook dependency")
    }
}

private fun validValueType(
    type: IrType,
): Boolean {
    return when {
        type.isString() -> true
        type.isInt() -> true
        type.isDouble() -> true
        type.isLong() -> true

        else -> false
    }
}
