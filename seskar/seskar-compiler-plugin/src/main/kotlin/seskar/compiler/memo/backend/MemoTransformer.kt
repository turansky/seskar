package seskar.compiler.memo.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid

internal class MemoTransformer(
    context: IrPluginContext,
) : IrElementTransformerVoid() {
    // TODO: implement
    init {
        context.symbols
    }
}
