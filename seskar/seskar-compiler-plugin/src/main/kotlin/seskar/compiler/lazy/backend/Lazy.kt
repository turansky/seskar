package seskar.compiler.lazy.backend

import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.util.hasAnnotation
import org.jetbrains.kotlin.ir.util.isTopLevel
import org.jetbrains.kotlin.name.FqName

internal const val LAZY_DELIMITER = "--"

private val LAZY = FqName("js.lazy.Lazy")

internal fun IrProperty.isLazy(): Boolean =
    hasAnnotation(LAZY) && isTopLevel
