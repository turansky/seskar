package seskar.compiler.common.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.expressions.impl.IrConstImpl
import org.jetbrains.kotlin.ir.expressions.impl.IrConstructorCallImpl
import org.jetbrains.kotlin.ir.types.defaultType
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

private val JS_NAME = ClassId(
    FqName("kotlin.js"),
    Name.identifier("JsName"),
)

internal fun JsName(
    context: IrPluginContext,
    value: String,
): IrConstructorCall =
    annotation(
        context = context,
        classId = JS_NAME,
        value = value,
    )

private fun annotation(
    context: IrPluginContext,
    classId: ClassId,
    value: String,
): IrConstructorCall {
    val type = context.referenceClass(classId)!!.defaultType
    val symbol = context.referenceConstructors(classId).single()
    val annotation = IrConstructorCallImpl(
        startOffset = UNDEFINED_OFFSET,
        endOffset = UNDEFINED_OFFSET,
        type = type,
        symbol = symbol,
        typeArgumentsCount = 0,
        constructorTypeArgumentsCount = 0,
        valueArgumentsCount = 1,
        origin = null,
    )

    val valueParameter = IrConstImpl.string(
        startOffset = UNDEFINED_OFFSET,
        endOffset = UNDEFINED_OFFSET,
        type = context.irBuiltIns.stringType,
        value = value,
    )

    annotation.putValueArgument(0, valueParameter)

    return annotation
}
