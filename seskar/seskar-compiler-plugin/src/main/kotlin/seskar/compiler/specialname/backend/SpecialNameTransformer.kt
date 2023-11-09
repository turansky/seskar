package seskar.compiler.specialname.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.declarations.createBlockBody
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.impl.IrCallImpl
import org.jetbrains.kotlin.ir.expressions.impl.IrConstImpl
import org.jetbrains.kotlin.ir.expressions.impl.IrGetValueImpl
import org.jetbrains.kotlin.ir.expressions.impl.IrReturnImpl
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

private val GET_PROPERTY = CallableId(
    packageName = FqName("seskar.js.internal"),
    className = null,
    callableName = Name.identifier("getProperty"),
)

internal class SpecialNameTransformer(
    private val context: IrPluginContext,
) : IrElementTransformerVoid() {
    override fun visitProperty(
        declaration: IrProperty,
    ): IrStatement {
        val specialName = declaration.specialName()
            ?: return declaration

        inlineProperty(declaration, specialName)
        return declaration
    }

    private fun inlineProperty(
        declaration: IrProperty,
        specialName: String,
    ) {
        val getter = declaration.getter
            ?: error("No default getter!")

        getter.isInline = true
        getter.body = context.irFactory.createBlockBody(
            startOffset = declaration.startOffset,
            endOffset = declaration.endOffset,
            statements = listOf(
                IrReturnImpl(
                    startOffset = declaration.startOffset,
                    endOffset = declaration.endOffset,
                    type = context.irBuiltIns.anyNType,
                    returnTargetSymbol = getter.symbol,
                    value = getValue(declaration, specialName),
                )
            )
        )
    }

    private fun getValue(
        declaration: IrProperty,
        specialName: String,
    ): IrExpression {
        val getProperty = context.referenceFunctions(GET_PROPERTY).single()

        val call = IrCallImpl.fromSymbolOwner(
            startOffset = declaration.startOffset,
            endOffset = declaration.endOffset,
            symbol = getProperty,
        )

        call.putValueArgument(
            index = 0,
            valueArgument = IrGetValueImpl(
                startOffset = declaration.startOffset,
                endOffset = declaration.endOffset,
                type = context.irBuiltIns.anyType,
                symbol = declaration.getter!!.dispatchReceiverParameter!!.symbol,
            )
        )
        call.putValueArgument(
            index = 1,
            valueArgument = IrConstImpl.string(
                startOffset = declaration.startOffset,
                endOffset = declaration.endOffset,
                type = context.irBuiltIns.stringType,
                value = specialName,
            )
        )

        return call
    }
}
