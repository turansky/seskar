package seskar.compiler.workers.extensions

import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import seskar.compiler.common.backend.SeskarPluginContext
import seskar.compiler.common.extensions.SeskarGenerationExtension
import seskar.compiler.workers.backend.WorkerFactoryTransformer

internal class WorkerFactoryExtension :
    SeskarGenerationExtension(jsOnly = true) {

    override fun generate(
        moduleFragment: IrModuleFragment,
        pluginContext: SeskarPluginContext,
    ) {
        moduleFragment.transformChildren(WorkerFactoryTransformer(pluginContext), null)
    }
}
