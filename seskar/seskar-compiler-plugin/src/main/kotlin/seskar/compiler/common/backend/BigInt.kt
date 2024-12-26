package seskar.compiler.common.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

private val BIG_INT = CallableId(
    packageName = FqName("js.core"),
    callableName = Name.identifier("BigInt"),
)

internal fun bigIntConst(
    context: IrPluginContext,
    value: Int,
): IrExpression {
    val create = irCall(context.referenceFunctions(BIG_INT).first())

    create.putValueArgument(0, intConst(context, value))

    return create
}
