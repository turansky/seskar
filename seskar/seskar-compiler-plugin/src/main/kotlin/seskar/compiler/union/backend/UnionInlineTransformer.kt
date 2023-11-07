package seskar.compiler.union.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.IrPropertyReference
import org.jetbrains.kotlin.ir.expressions.impl.IrConstImpl
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid

internal class UnionInlineTransformer(
    private val context: IrPluginContext,
) : IrElementTransformerVoid() {
    override fun visitPropertyReference(
        expression: IrPropertyReference,
    ): IrExpression {
        val value = expression.symbol.owner.value()
            ?: return expression

        return valueConstant(expression, value)
    }

    private fun valueConstant(
        declaration: IrPropertyReference,
        value: Value,
    ): IrExpression {
        return when (value) {
            is IntValue ->
                IrConstImpl.int(
                    startOffset = declaration.startOffset,
                    endOffset = declaration.endOffset,
                    type = context.irBuiltIns.intType,
                    value = value.value,
                )

            is StringValue ->
                IrConstImpl.string(
                    startOffset = declaration.startOffset,
                    endOffset = declaration.endOffset,
                    type = context.irBuiltIns.stringType,
                    value = value.value,
                )
        }
    }
}
