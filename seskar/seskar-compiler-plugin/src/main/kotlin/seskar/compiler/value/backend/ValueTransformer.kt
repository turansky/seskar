package seskar.compiler.value.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.IrVararg
import org.jetbrains.kotlin.ir.expressions.IrVarargElement
import org.jetbrains.kotlin.ir.expressions.impl.IrCallImpl
import org.jetbrains.kotlin.ir.types.getClass
import org.jetbrains.kotlin.ir.types.isNullable
import org.jetbrains.kotlin.ir.util.getArgumentsWithIr
import org.jetbrains.kotlin.ir.util.properties
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid

internal class ValueTransformer(
    private val context: IrPluginContext,
) : IrElementTransformerVoid() {
    override fun visitCall(
        expression: IrCall,
    ): IrExpression {
        if (isHookCall(expression))
            visitHookCall(expression)

        return super.visitCall(expression)
    }

    private fun visitHookCall(
        expression: IrCall,
    ) {
        val dependencies = expression.getArgumentsWithIr()
            .firstOrNull { (parameter) -> parameter.name == DEPENDENCIES && parameter.varargElementType == context.irBuiltIns.anyNType }
            ?.second
            ?: return

        if (dependencies is IrVararg)
            visitVarargElements(dependencies.elements)
    }

    private fun visitVarargElements(
        elements: MutableList<IrVarargElement>,
    ) {
        val valueElements = elements.asSequence()
            .filterIsInstance<IrExpression>()
            .filter { downCastRequired(it.type) }
            .toList()

        if (valueElements.isEmpty())
            return

        for (element in valueElements) {
            val index = elements.indexOf(element)
            elements[index] = transformVarargElement(element)
        }
    }

    private fun transformVarargElement(
        element: IrExpression,
    ): IrExpression {
        val klass = element.type.getClass()!!

        return if (klass.isJsValue()) {
            getValue(element)
        } else {
            toString(element)
        }
    }

    private fun getValue(
        element: IrExpression,
    ): IrExpression {
        val klass = element.type.getClass()!!
        val value = klass.properties.first()

        val call = IrCallImpl.fromSymbolOwner(
            startOffset = element.startOffset,
            endOffset = element.endOffset,
            symbol = value.getter!!.symbol,
        )

        call.dispatchReceiver = element

        return call
    }

    private fun toString(
        element: IrExpression,
    ): IrExpression =
        if (element.type.isNullable()) {
            val call = IrCallImpl.fromSymbolOwner(
                startOffset = element.startOffset,
                endOffset = element.endOffset,
                symbol = context.symbols.extensionToString,
            )

            call.extensionReceiver = element
            call
        } else {
            val call = IrCallImpl.fromSymbolOwner(
                startOffset = element.startOffset,
                endOffset = element.endOffset,
                symbol = context.symbols.memberToString,
            )

            call.dispatchReceiver = element
            call
        }
}
