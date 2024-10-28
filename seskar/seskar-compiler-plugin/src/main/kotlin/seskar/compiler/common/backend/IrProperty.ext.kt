package seskar.compiler.common.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.declarations.createBlockBody
import org.jetbrains.kotlin.ir.expressions.IrExpression

internal fun IrProperty.addInlineGetter(
    context: IrPluginContext,
    value: IrExpression,
) {
    val getter = getter
        ?: error("No default getter!")

    getter.isInline = true
    getter.body = context.irFactory.createBlockBody(
        startOffset = UNDEFINED_OFFSET,
        endOffset = UNDEFINED_OFFSET,
        statements = listOf(
            irReturn(
                type = context.irBuiltIns.nothingNType,
                returnTargetSymbol = getter.symbol,
                value = value,
            )
        )
    )
}
