package seskar.compiler.react.key.extensions

import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import seskar.compiler.common.backend.SeskarPluginContext
import seskar.compiler.common.extensions.SeskarGenerationExtension
import seskar.compiler.react.key.backend.DefaultKeyTransformer

internal class DefaultKeyExtension :
    SeskarGenerationExtension(jsOnly = true) {

    override fun generate(
        moduleFragment: IrModuleFragment,
        pluginContext: SeskarPluginContext,
    ) {
        moduleFragment.transformChildren(DefaultKeyTransformer(pluginContext), null)
    }
}
