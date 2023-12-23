package seskar.compiler.native.extensions

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.visitors.transformChildrenVoid
import seskar.compiler.native.backend.NativeTransformer
import seskar.compiler.native.backend.SealedTransformer

internal class NativeTransformExtension : IrGenerationExtension {
    override fun generate(
        moduleFragment: IrModuleFragment,
        pluginContext: IrPluginContext,
    ) {
        moduleFragment.transformChildrenVoid(NativeTransformer(pluginContext))
        moduleFragment.transformChildrenVoid(SealedTransformer())
    }
}
