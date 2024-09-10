package seskar.compiler.react.lazy.extensions

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.platform.isJs
import seskar.compiler.react.lazy.backend.LazyComponentTransformer

internal class LazyComponentExtension : IrGenerationExtension {
    override fun generate(
        moduleFragment: IrModuleFragment,
        pluginContext: IrPluginContext,
    ) {
        pluginContext.platform
            ?.takeIf { it.isJs() }
            ?: return

        moduleFragment.transformChildren(LazyComponentTransformer(pluginContext), null)
    }
}
