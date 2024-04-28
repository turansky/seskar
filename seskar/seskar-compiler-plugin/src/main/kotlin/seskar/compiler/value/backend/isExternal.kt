package seskar.compiler.value.backend

import org.jetbrains.kotlin.ir.declarations.IrClass

// WA for https://youtrack.jetbrains.com/issue/KT-67627
internal fun isExternal(
    declaration: IrClass,
): Boolean {
    if (declaration.isExternal)
        return true

    val parent = declaration.parent as? IrClass
        ?: return false

    return isExternal(parent)
}
