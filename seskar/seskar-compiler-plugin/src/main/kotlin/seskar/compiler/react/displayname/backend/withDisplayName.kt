package seskar.compiler.react.displayname.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import seskar.compiler.common.backend.irCall
import seskar.compiler.common.backend.stringConst

private val WITH_DISPLAY_NAME = CallableId(
    packageName = FqName("seskar.react.internal"),
    callableName = Name.identifier("withDisplayName"),
)

internal fun withDisplayName(
    context: IrPluginContext,
    componentFactory: IrExpression, /* IrCall? */
    displayName: String,
): IrExpression {
    val withDisplayName = context.referenceFunctions(WITH_DISPLAY_NAME).single()

    val call = irCall(withDisplayName)

    call.putValueArgument(0, componentFactory)
    call.putValueArgument(1, stringConst(context, displayName))

    return call
}
