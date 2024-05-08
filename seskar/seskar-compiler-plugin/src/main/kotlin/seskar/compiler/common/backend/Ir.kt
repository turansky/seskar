package seskar.compiler.common.backend

import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.declarations.IrValueParameter
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.IrGetValue
import org.jetbrains.kotlin.ir.expressions.impl.IrCallImpl
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol

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
