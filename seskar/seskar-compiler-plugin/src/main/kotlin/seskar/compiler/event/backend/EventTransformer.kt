package seskar.compiler.event.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.types.getClass
import org.jetbrains.kotlin.ir.util.constructors
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import seskar.compiler.common.backend.*

internal class EventTransformer(
    private val context: IrPluginContext,
) : IrElementTransformerVoid() {
    override fun visitClass(
        declaration: IrClass,
    ): IrStatement {
        if (!isReallyExternal(declaration))
            return declaration

        return super.visitClass(declaration)
    }

    override fun visitProperty(
        declaration: IrProperty,
    ): IrStatement {
        val type = declaration.eventType()
            ?: return declaration

        declaration.addInlineGetter(
            context = context,
            value = eventInstance(declaration, type),
        )

        return declaration
    }

    private fun eventInstance(
        declaration: IrProperty,
        type: String,
    ): IrExpression {
        val getter = declaration.getter!!

        val constructor = getter
            .returnType
            .getClass()!!
            .constructors
            .single()

        val call = irConstructorCall(constructor.symbol)
        call.arguments[0] = irGet(getter.dispatchReceiverParameter!!)
        call.arguments[1] = stringConst(context, type)

        return call
    }
}
