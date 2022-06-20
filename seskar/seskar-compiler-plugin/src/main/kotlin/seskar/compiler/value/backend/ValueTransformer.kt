package seskar.compiler.value.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.IrVararg
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid

internal class ValueTransformer(
    private val context: IrPluginContext,
) : IrElementTransformerVoid() {
    override fun visitVararg(expression: IrVararg): IrExpression {
        expression.elements
            .asSequence()
            .filterIsInstance<IrExpression>()
            .filter { downCastRequired(it.type) }
            .forEach { it.type = context.irBuiltIns.anyNType }

        return super.visitVararg(expression)
    }
}
