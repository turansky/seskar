package seskar.compiler.specialname.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
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

private val SET_PROPERTY = CallableId(
    packageName = FqName("seskar.js.internal"),
    className = null,
    callableName = Name.identifier("setProperty"),
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
                    value = getValue(getter, specialName),
                )
            )
        )

        val setter = declaration.setter
            ?: return

        setter.isInline = true
        setter.body = context.irFactory.createBlockBody(
            startOffset = declaration.startOffset,
            endOffset = declaration.endOffset,
            statements = listOf(
                setValue(setter, specialName),
            )
        )
    }

    private fun getValue(
        getter: IrSimpleFunction,
        specialName: String,
    ): IrExpression {
        val getProperty = context.referenceFunctions(GET_PROPERTY).single()

        val call = IrCallImpl.fromSymbolOwner(
            startOffset = getter.startOffset,
            endOffset = getter.endOffset,
            symbol = getProperty,
        )

        call.putValueArgument(
            index = 0,
            valueArgument = IrGetValueImpl(
                startOffset = getter.startOffset,
                endOffset = getter.endOffset,
                type = context.irBuiltIns.anyType,
                symbol = getter.dispatchReceiverParameter!!.symbol,
            )
        )
        call.putValueArgument(
            index = 1,
            valueArgument = IrConstImpl.string(
                startOffset = getter.startOffset,
                endOffset = getter.endOffset,
                type = context.irBuiltIns.stringType,
                value = specialName,
            )
        )

        return call
    }

    private fun setValue(
        setter: IrSimpleFunction,
        specialName: String,
    ): IrExpression {
        val setProperty = context.referenceFunctions(SET_PROPERTY).single()

        val call = IrCallImpl.fromSymbolOwner(
            startOffset = setter.startOffset,
            endOffset = setter.endOffset,
            symbol = setProperty,
        )

        call.putValueArgument(
            index = 0,
            valueArgument = IrGetValueImpl(
                startOffset = setter.startOffset,
                endOffset = setter.endOffset,
                type = context.irBuiltIns.anyType,
                symbol = setter.dispatchReceiverParameter!!.symbol,
            )
        )

        call.putValueArgument(
            index = 1,
            valueArgument = IrConstImpl.string(
                startOffset = setter.startOffset,
                endOffset = setter.endOffset,
                type = context.irBuiltIns.stringType,
                value = specialName,
            )
        )

        call.putValueArgument(
            index = 2,
            valueArgument = IrGetValueImpl(
                startOffset = setter.startOffset,
                endOffset = setter.endOffset,
                type = context.irBuiltIns.anyType,
                symbol = setter.valueParameters[0].symbol,
            )
        )

        return call
    }
}
