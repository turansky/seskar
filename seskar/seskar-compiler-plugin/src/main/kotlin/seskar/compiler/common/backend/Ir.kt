package seskar.compiler.common.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin
import org.jetbrains.kotlin.ir.declarations.IrValueParameter
import org.jetbrains.kotlin.ir.declarations.IrVariable
import org.jetbrains.kotlin.ir.declarations.impl.IrVariableImpl
import org.jetbrains.kotlin.ir.expressions.*
import org.jetbrains.kotlin.ir.expressions.impl.*
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol
import org.jetbrains.kotlin.ir.symbols.IrReturnTargetSymbol
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol
import org.jetbrains.kotlin.ir.symbols.impl.IrVariableSymbolImpl
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.name.Name

private val SESKAR_LOCAL_VARIABLE = IrDeclarationOrigin.GeneratedByPlugin(SeskarPluginKey)

internal fun irVariable(
    name: Name,
    type: IrType,
): IrVariable =
    IrVariableImpl(
        startOffset = UNDEFINED_OFFSET,
        endOffset = UNDEFINED_OFFSET,
        origin = SESKAR_LOCAL_VARIABLE,
        symbol = IrVariableSymbolImpl(),
        name = name,
        type = type,
        isVar = false,
        isConst = false,
        isLateinit = false,
    )

internal fun irGet(
    property: IrPropertySymbol,
): IrExpression =
    irCall(property.owner.getter!!.symbol)

internal fun irGet(
    variable: IrVariable,
): IrExpression =
    IrGetValueImpl(
        startOffset = UNDEFINED_OFFSET,
        endOffset = UNDEFINED_OFFSET,
        symbol = variable.symbol,
    )

internal fun irGet(
    parameter: IrValueParameter,
): IrGetValue =
    IrGetValueImpl(
        startOffset = UNDEFINED_OFFSET,
        endOffset = UNDEFINED_OFFSET,
        symbol = parameter.symbol,
    )

internal fun irCall(
    symbol: IrSimpleFunctionSymbol,
): IrCall =
    IrCallImpl.fromSymbolOwner(
        startOffset = UNDEFINED_OFFSET,
        endOffset = UNDEFINED_OFFSET,
        symbol = symbol,
    )

internal fun irConstructorCall(
    symbol: IrConstructorSymbol,
): IrConstructorCall =
    IrConstructorCallImpl.fromSymbolOwner(
        type = symbol.owner.returnType,
        constructorSymbol = symbol,
    )

internal fun irReturn(
    type: IrType,
    returnTargetSymbol: IrReturnTargetSymbol,
    value: IrExpression,
): IrReturn =
    IrReturnImpl(
        startOffset = UNDEFINED_OFFSET,
        endOffset = UNDEFINED_OFFSET,
        type = type,
        returnTargetSymbol = returnTargetSymbol,
        value = value,
    )

internal inline fun <reified T> IrConstructorCall.value(
    index: Int = 0,
): T {
    val argument = getValueArgument(index) as IrConst
    return argument.value as T
}

internal fun intConst(
    context: IrPluginContext,
    value: Int,
): IrExpression =
    IrConstImpl.int(
        startOffset = UNDEFINED_OFFSET,
        endOffset = UNDEFINED_OFFSET,
        type = context.irBuiltIns.intType,
        value = value,
    )

internal fun stringConst(
    context: IrPluginContext,
    value: String,
): IrExpression =
    IrConstImpl.string(
        startOffset = UNDEFINED_OFFSET,
        endOffset = UNDEFINED_OFFSET,
        type = context.irBuiltIns.stringType,
        value = value,
    )
