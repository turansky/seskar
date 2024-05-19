package seskar.compiler.common.backend

import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin
import org.jetbrains.kotlin.ir.declarations.IrValueParameter
import org.jetbrains.kotlin.ir.declarations.IrVariable
import org.jetbrains.kotlin.ir.declarations.impl.IrVariableImpl
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.IrGetValue
import org.jetbrains.kotlin.ir.expressions.impl.IrCallImpl
import org.jetbrains.kotlin.ir.expressions.impl.IrConstructorCallImpl
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol
import org.jetbrains.kotlin.ir.symbols.impl.IrVariableSymbolImpl
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.name.Name

internal fun irVariable(
    name: Name,
    type: IrType,
): IrVariable =
    IrVariableImpl(
        startOffset = UNDEFINED_OFFSET,
        endOffset = UNDEFINED_OFFSET,
        origin = IrDeclarationOrigin.IR_TEMPORARY_VARIABLE,
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
    parameter: IrValueParameter,
): IrGetValue =
    IrGetValueLocal(
        startOffset = UNDEFINED_OFFSET,
        endOffset = UNDEFINED_OFFSET,
        type = parameter.type,
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
