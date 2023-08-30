package seskar.compiler.key.backend

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid

internal class DefaultKeyTransformer(
    @Suppress("unused")
    private val context: IrPluginContext,
) : IrElementTransformerVoid()
