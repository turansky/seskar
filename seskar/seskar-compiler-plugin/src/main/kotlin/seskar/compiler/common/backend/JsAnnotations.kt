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

private val JS_QUALIFIER = ClassId(
    FqName("kotlin.js"),
    Name.identifier("JsQualifier"),
)

private val JS_NAME = ClassId(
    FqName("kotlin.js"),
    Name.identifier("JsName"),
)

internal fun JsQualifier(
    context: IrPluginContext,
    value: String,
): IrConstructorCall =
    annotation(
        context = context,
        classId = JS_QUALIFIER,
        parameters = arrayOf(value),
    )

internal fun JsName(
    context: IrPluginContext,
    value: String,
): IrConstructorCall =
    annotation(
        context = context,
        classId = JS_NAME,
        parameters = arrayOf(value),
    )

internal fun annotation(
    context: IrPluginContext,
    classId: ClassId,
    vararg parameters: String,
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
        valueArgumentsCount = parameters.size,
        origin = null,
    )

    for ((index, value) in parameters.withIndex()) {
        val valueParameter = IrConstImpl.string(
            startOffset = UNDEFINED_OFFSET,
            endOffset = UNDEFINED_OFFSET,
            type = context.irBuiltIns.stringType,
            value = value,
        )

        annotation.putValueArgument(index, valueParameter)
    }

    return annotation
}
