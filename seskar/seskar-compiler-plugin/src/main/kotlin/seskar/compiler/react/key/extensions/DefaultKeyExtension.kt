package seskar.compiler.react.key.extensions

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.platform.isJs
import seskar.compiler.react.key.backend.DefaultKeyTransformer

internal class DefaultKeyExtension : IrGenerationExtension {
    override fun generate(
        moduleFragment: IrModuleFragment,
        pluginContext: IrPluginContext,
    ) {
        pluginContext.platform
            ?.takeIf { it.isJs() }
            ?: return

        moduleFragment.transformChildren(DefaultKeyTransformer(pluginContext), null)
    }
}
