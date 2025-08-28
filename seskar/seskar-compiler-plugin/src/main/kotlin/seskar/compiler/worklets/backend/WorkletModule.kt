package seskar.compiler.worklets.backend

import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.types.classFqName
import org.jetbrains.kotlin.ir.util.isTopLevel
import org.jetbrains.kotlin.name.FqName

internal const val WORKLET_MODULE_DELIMITER = "$$"

private val WORKLET_MODULES = setOf(
    FqName("web.audio.AudioWorkletModule"),
    FqName("web.css.painting.PaintWorkletModule"),

    FqName("web.serviceworker.ServiceWorkerModule"),
)

internal fun IrProperty.isWorkletModule(): Boolean =
    isTopLevel && getter?.returnType?.classFqName in WORKLET_MODULES
