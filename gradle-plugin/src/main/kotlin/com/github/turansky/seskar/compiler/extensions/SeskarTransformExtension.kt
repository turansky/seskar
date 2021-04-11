package com.github.turansky.seskar.compiler.extensions

import com.github.turansky.seskar.compiler.backend.UnionTransformer
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.visitors.transformChildrenVoid

internal class SeskarTransformExtension : IrGenerationExtension {
    override fun generate(
        moduleFragment: IrModuleFragment,
        pluginContext: IrPluginContext
    ) {
        moduleFragment.transformChildrenVoid(UnionTransformer(pluginContext))
    }
}
