package seskar.compiler.native.backend

import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.util.hasAnnotation
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

private val ANNOTATION_MAP = mapOf(
    FqName("seskar.js.JsNativeGetter") to ClassId(FqName("kotlin.js"), Name.identifier("nativeGetter")),
    FqName("seskar.js.JsNativeSetter") to ClassId(FqName("kotlin.js"), Name.identifier("nativeSetter")),
    FqName("seskar.js.JsNativeInvoke") to ClassId(FqName("kotlin.js"), Name.identifier("nativeInvoke")),
)

internal fun IrFunction.nativeAnnotation(): ClassId? {
    for ((marker, annotation) in ANNOTATION_MAP) {
        if (hasAnnotation(marker))
            return annotation
    }

    return null
}
