package seskar.compiler.alias.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.util.isTopLevel
import org.jetbrains.kotlin.ir.util.kotlinFqName
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import seskar.compiler.common.backend.*

private val GET_INDEXED_VALUE = CallableId(
    packageName = FqName("seskar.js.internal"),
    callableName = Name.identifier("getValueByIndex"),
)

internal class AliasTransformer(
    private val context: IrPluginContext,
) : IrElementTransformerVoid() {
    override fun visitClass(
        declaration: IrClass,
    ): IrStatement {
        if (!isReallyExternal(declaration))
            return declaration

        return super.visitClass(declaration)
    }

    override fun visitFunction(
        declaration: IrFunction,
    ): IrStatement {
        if (declaration.isTopLevel)
            return declaration

        if (declaration !is IrOverridableMember)
            return declaration

        val alias = declaration.alias()
            ?: return declaration

        addFunctionBody(declaration, alias)
        return declaration
    }

    private fun <T> addFunctionBody(
        declaration: T,
        alias: Alias,
    ) where T : IrFunction,
            T : IrOverridableMember {
        val statement = irReturn(
            type = declaration.returnType,
            returnTargetSymbol = declaration.symbol,
            value = getAliasExpression(declaration, alias),
        )

        declaration.isInline = true
        declaration.isExternal = false
        declaration.modality = Modality.FINAL
        declaration.body = context.irFactory.createBlockBody(
            startOffset = UNDEFINED_OFFSET,
            endOffset = UNDEFINED_OFFSET,
            statements = listOfNotNull(statement),
        )
    }

    private fun getAliasExpression(
        declaration: IrFunction,
        alias: Alias,
    ): IrExpression {
        val dispatchReceiverParameter = declaration.dispatchReceiverParameter
            ?: error("No receiver for function ${declaration.kotlinFqName}")

        return when (alias) {
            is ThisAlias -> irGet(dispatchReceiverParameter)
            is IndexedAccessAlias -> getValue(dispatchReceiverParameter, alias.index)
            is PropertyAlias -> TODO("Property alias implementation is coming soon!")
        }
    }

    private fun getValue(
        target: IrValueParameter,
        index: Int,
    ): IrExpression {
        val getValue = irCall(context.referenceFunctions(GET_INDEXED_VALUE).single())

        getValue.arguments[0] = irGet(target)
        getValue.arguments[1] = intConst(context, index)

        return getValue
    }
}
