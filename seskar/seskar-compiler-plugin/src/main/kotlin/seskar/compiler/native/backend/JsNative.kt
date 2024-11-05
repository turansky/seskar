package seskar.compiler.native.backend

import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.util.getAnnotation
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

private val JS_NATIVE = FqName("seskar.js.JsNative")

private val ANNOTATION_MAP = mapOf(
    "get" to ClassId(FqName("kotlin.js"), Name.identifier("nativeGetter")),
    "set" to ClassId(FqName("kotlin.js"), Name.identifier("nativeSetter")),

    "invoke" to ClassId(FqName("kotlin.js"), Name.identifier("nativeInvoke")),
    "invokeAsync" to ClassId(FqName("kotlin.js"), Name.identifier("nativeInvoke")),
)

internal fun IrFunction.nativeAnnotation(): ClassId? {
    getAnnotation(JS_NATIVE) ?: return null

    return ANNOTATION_MAP.getValue(name.identifier)
}
