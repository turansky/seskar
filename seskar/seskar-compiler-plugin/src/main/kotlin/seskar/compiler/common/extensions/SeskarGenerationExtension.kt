package seskar.compiler.common.extensions

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.platform.isJs
import seskar.compiler.common.backend.SeskarPluginContext

internal abstract class SeskarGenerationExtension(
    private val jsOnly: Boolean = false,
) : IrGenerationExtension {
    final override fun generate(
        moduleFragment: IrModuleFragment,
        pluginContext: IrPluginContext,
    ) {
        if (jsOnly) {
            pluginContext.platform
                ?.takeIf { it.isJs() }
                ?: return
        }

        generate(
            moduleFragment = moduleFragment,
            pluginContext = SeskarPluginContext(pluginContext),
        )
    }

    abstract fun generate(
        moduleFragment: IrModuleFragment,
        pluginContext: SeskarPluginContext,
    )
}
