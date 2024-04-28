package seskar.compiler.value.backend

import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.util.getAnnotation
import org.jetbrains.kotlin.name.FqName

private val JS_VIRTUAL = FqName("seskar.js.JsVirtual")

internal fun IrClass.isJsVirtual(): Boolean =
    isExternal && getAnnotation(JS_VIRTUAL) != null
