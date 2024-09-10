package seskar.compiler.react.lazy.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid

internal class LazyComponentTransformer(
    private val context: IrPluginContext,
) : IrElementTransformerVoid()
