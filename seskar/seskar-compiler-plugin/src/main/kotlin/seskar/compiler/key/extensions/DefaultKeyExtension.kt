package seskar.compiler.key.extensions

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.platform.isJs

internal class DefaultKeyExtension : IrGenerationExtension {
    override fun generate(
        moduleFragment: IrModuleFragment,
        pluginContext: IrPluginContext,
    ) {
        pluginContext.platform
            ?.takeIf { it.isJs() }
            ?: return

        // moduleFragment.transformChildrenVoid(DefaultKeyTransformer(pluginContext))
    }
}
