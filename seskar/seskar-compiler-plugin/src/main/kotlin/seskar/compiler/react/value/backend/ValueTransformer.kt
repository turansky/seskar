package seskar.compiler.react.value.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.IrVararg
import org.jetbrains.kotlin.ir.expressions.IrVarargElement
import org.jetbrains.kotlin.ir.types.getClass
import org.jetbrains.kotlin.ir.util.getArgumentsWithIr
import org.jetbrains.kotlin.ir.util.isNullable
import org.jetbrains.kotlin.ir.util.kotlinFqName
import org.jetbrains.kotlin.ir.util.properties
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import seskar.compiler.common.backend.irCall

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
            elements[index] = getValue(element)
        }
    }

    private fun getValue(
        element: IrExpression,
    ): IrExpression {
        val klass = element.type.getClass()!!

        // WA Check how to access `rawValue`
        if (klass.kotlinFqName == DURATION)
            return toString(element, element.type.isNullable())

        val value = klass.properties.first()
        val getter = value.getter!!

        val call = irCall(getter.symbol)
        call.dispatchReceiver = element

        return when (getter.returnType) {
            context.irBuiltIns.longType,
                -> toString(call, element.type.isNullable())

            else -> call
        }
    }

    private fun toString(
        element: IrExpression,
        nullable: Boolean,
    ): IrExpression =
        if (nullable) {
            irCall(context.irBuiltIns.extensionToString)
                .also { it.arguments[0] = element }
        } else {
            irCall(context.irBuiltIns.memberToString)
                .also { it.dispatchReceiver = element }
        }
}
