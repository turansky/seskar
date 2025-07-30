package seskar.compiler.native.backend

import org.jetbrains.kotlin.ir.backend.js.utils.isDispatchReceiver
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.util.OperatorNameConventions.GET
import org.jetbrains.kotlin.util.OperatorNameConventions.INVOKE
import org.jetbrains.kotlin.util.OperatorNameConventions.SET

private fun nativeAnnotation(
    className: String,
): ClassId =
    ClassId(
        FqName("seskar.js.internal"),
        Name.identifier("${className}Internal"),
    )

private val ANNOTATION_MAP = mapOf(
    GET to nativeAnnotation("JsNativeGetter"),
    SET to nativeAnnotation("JsNativeSetter"),
    INVOKE to nativeAnnotation("JsNativeInvoke"),
)

internal fun IrFunction.nativeAnnotation(): ClassId? {
    if (!isExternal)
        return null

    if (this !is IrSimpleFunction)
        return null

    if (!isOperator)
        return null

    if (!parametersValid())
        return null

    return ANNOTATION_MAP[name]
}

private fun IrFunction.parametersValid(): Boolean {
    val size = parameters
        .filterNot { it.isDispatchReceiver }
        .size

    return when (name) {
        GET -> size == 1
        SET -> size == 2
        else -> true
    }
}
