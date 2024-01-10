package seskar.compiler.mixin.backend

import org.jetbrains.kotlin.ir.interpreter.hasAnnotation
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.name.FqName

private val JS_MIXIN = FqName("seskar.js.JsMixin")

internal fun IrType.isMixin(): Boolean =
    hasAnnotation(JS_MIXIN)
