package seskar.compiler.worklets.extensions

import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import seskar.compiler.common.backend.SeskarPluginContext
import seskar.compiler.common.extensions.SeskarGenerationExtension
import seskar.compiler.worklets.backend.WorkletTransformer

internal class WorkletExtension :
    SeskarGenerationExtension(jsOnly = true) {

    override fun generate(
        moduleFragment: IrModuleFragment,
        pluginContext: SeskarPluginContext,
    ) {
        moduleFragment.transformChildren(WorkletTransformer(pluginContext), null)
    }
}
