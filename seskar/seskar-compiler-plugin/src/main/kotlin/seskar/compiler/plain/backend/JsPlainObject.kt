package seskar.compiler.plain.backend

import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.util.hasAnnotation
import org.jetbrains.kotlin.name.FqName

private val JS_PLAIN_OBJECT = FqName("js.objects.JsPlainObject")

internal fun IrClass.isPlainObject(): Boolean =
    hasAnnotation(JS_PLAIN_OBJECT)
