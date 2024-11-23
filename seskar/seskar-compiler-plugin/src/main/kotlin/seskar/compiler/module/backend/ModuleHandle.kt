package seskar.compiler.module.backend

import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.util.hasAnnotation
import org.jetbrains.kotlin.ir.util.isTopLevel
import org.jetbrains.kotlin.name.FqName

internal const val MODULE_HANDLE_DELIMITER = "$$"

private val MODULE_HANDLE = FqName("js.module.JsModuleHandle")

internal fun IrProperty.isModuleHandle(): Boolean =
    isTopLevel && hasAnnotation(MODULE_HANDLE)
