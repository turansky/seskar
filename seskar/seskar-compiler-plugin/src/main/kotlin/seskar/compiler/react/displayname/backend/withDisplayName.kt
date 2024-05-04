package seskar.compiler.react.displayname.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.impl.IrConstImpl
import org.jetbrains.kotlin.ir.types.defaultType
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import seskar.compiler.common.backend.irCall

private val WITH_DISPLAY_NAME = CallableId(
    packageName = FqName("seskar.react"),
    callableName = Name.identifier("withDisplayName"),
)

internal fun withDisplayName(
    context: IrPluginContext,
    componentFactory: IrExpression, /* IrCall? */
    displayName: String,
): IrExpression {
    val withDisplayName = context.referenceFunctions(WITH_DISPLAY_NAME).single()

    val call = irCall(withDisplayName)

    val displayNameConst = IrConstImpl.string(
        startOffset = componentFactory.startOffset,
        endOffset = componentFactory.endOffset,
        type = context.symbols.string.defaultType,
        value = displayName,
    )

    call.putValueArgument(0, componentFactory)
    call.putValueArgument(1, displayNameConst)

    return call
}
