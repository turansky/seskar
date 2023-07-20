package seskar.compiler.value.backend

import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.ir.declarations.IrClass

internal fun IrClass.isJsValue(): Boolean {
    if (kind != ClassKind.CLASS)
        return false

    return isValue
}
