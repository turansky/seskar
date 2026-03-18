package seskar.compiler.react.displayname.backend

import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import seskar.compiler.common.backend.SeskarPluginContext
import seskar.compiler.common.backend.irCall
import seskar.compiler.common.backend.stringConst

private val WITH_DISPLAY_NAME = CallableId(
    packageName = FqName("seskar.react.internal"),
    callableName = Name.identifier("withDisplayName"),
)

internal fun withDisplayName(
    context: SeskarPluginContext,
    componentFactory: IrExpression, /* IrCall? */
    displayName: String,
): IrExpression {
    val withDisplayName = context.findFunction(WITH_DISPLAY_NAME)

    val call = irCall(withDisplayName)

    call.arguments[0] = componentFactory
    call.arguments[1] = stringConst(context, displayName)

    return call
}
