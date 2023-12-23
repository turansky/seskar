package seskar.compiler.native.backend

import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.util.hasAnnotation
import org.jetbrains.kotlin.name.FqName

private val JS_SEALED = FqName("seskar.js.JsSealed")

internal fun IrClass.isJsSealed(): Boolean =
    isExternal && hasAnnotation(JS_SEALED)
