package seskar.compiler.common.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrElement
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
    source: IrElement,
    name: String,
): IrConstructorCall {
    val type = context.referenceClass(JS_NAME)!!.defaultType
    val symbol = context.referenceConstructors(JS_NAME).single()
    val annotation = IrConstructorCallImpl(
        startOffset = source.startOffset,
        endOffset = source.endOffset,
        type = type,
        symbol = symbol,
        typeArgumentsCount = 0,
        constructorTypeArgumentsCount = 0,
        valueArgumentsCount = 1,
        origin = null,
    )

    val nameParameter = IrConstImpl.string(
        startOffset = source.startOffset,
        endOffset = source.endOffset,
        type = context.irBuiltIns.stringType,
        value = name,
    )

    annotation.putValueArgument(0, nameParameter)

    return annotation
}
