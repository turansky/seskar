package seskar.compiler.common.backend

import org.jetbrains.kotlin.ir.declarations.IrAttributeContainer
import org.jetbrains.kotlin.ir.expressions.IrGetValue
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin
import org.jetbrains.kotlin.ir.symbols.IrValueSymbol
import org.jetbrains.kotlin.ir.types.IrType

// WA for smooth migration
// TODO: remove after migration on Kotlin `2.0.0`
internal class IrGetValueLocal(
    override val startOffset: Int,
    override val endOffset: Int,
    override var symbol: IrValueSymbol,
) : IrGetValue() {
    override var type: IrType = symbol.owner.type
    override var origin: IrStatementOrigin? = null
    override var attributeOwnerId: IrAttributeContainer = this
    override var originalBeforeInline: IrAttributeContainer? = null
}
