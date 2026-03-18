package seskar.compiler.lazy.extensions

import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import seskar.compiler.common.backend.SeskarPluginContext
import seskar.compiler.common.extensions.SeskarGenerationExtension
import seskar.compiler.lazy.backend.LazyModuleTransformer

internal class LazyModuleExtension :
    SeskarGenerationExtension(jsOnly = true) {

    override fun generate(
        moduleFragment: IrModuleFragment,
        pluginContext: SeskarPluginContext,
    ) {
        moduleFragment.transformChildren(LazyModuleTransformer(pluginContext), null)
    }
}
