package seskar.compiler.lazy.extensions

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.platform.isJs
import seskar.compiler.lazy.backend.LazyModuleTransformer

internal class LazyModuleExtension : IrGenerationExtension {
    override fun generate(
        moduleFragment: IrModuleFragment,
        pluginContext: IrPluginContext,
    ) {
        pluginContext.platform
            ?.takeIf { it.isJs() }
            ?: return

        moduleFragment.transformChildren(LazyModuleTransformer(pluginContext), null)
    }
}
