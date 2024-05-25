package seskar.compiler.alias.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.IrValueParameter
import org.jetbrains.kotlin.ir.declarations.createBlockBody
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.util.isTopLevel
import org.jetbrains.kotlin.ir.util.kotlinFqName
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid
import seskar.compiler.common.backend.irGet
import seskar.compiler.common.backend.irReturn
import seskar.compiler.common.backend.isReallyExternal

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

        val alias = declaration.alias()
            ?: return declaration

        addFunctionBody(declaration, alias)
        return declaration
    }

    private fun addFunctionBody(
        declaration: IrFunction,
        alias: Alias,
    ) {
        val statement = irReturn(
            type = declaration.returnType,
            returnTargetSymbol = declaration.symbol,
            value = getAliasExpression(declaration, alias),
        )

        declaration.isInline = true
        declaration.isExternal = false
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
            is PropertyAlias -> TODO()
        }
    }

    private fun getValue(
        target: IrValueParameter,
        index: Int,
    ): IrExpression {
        return irGet(target)
    }
}
