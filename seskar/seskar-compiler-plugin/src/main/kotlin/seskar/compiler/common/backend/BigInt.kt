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

private val BIG_INT_REGEX = Regex("""(-*\d+)n""")

@JvmInline
value class BigInt(
    val value: String,
) {
    override fun toString(): String =
        "${value}n"
}

fun String.toBigIntOrNull(): BigInt? =
    BIG_INT_REGEX.matchEntire(this)
        ?.let { BigInt(it.groupValues[1]) }

internal fun bigIntConst(
    context: IrPluginContext,
    value: BigInt,
): IrExpression {
    val create = irCall(context.referenceFunctions(BIG_INT).last())

    create.arguments[0] = stringConst(context, value.value)

    return create
}
