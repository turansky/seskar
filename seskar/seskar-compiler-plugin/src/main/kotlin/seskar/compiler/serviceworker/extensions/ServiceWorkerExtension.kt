package seskar.compiler.serviceworker.extensions

import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import seskar.compiler.common.backend.SeskarPluginContext
import seskar.compiler.common.extensions.SeskarGenerationExtension
import seskar.compiler.serviceworker.backend.ServiceWorkerTransformer

internal class ServiceWorkerExtension :
    SeskarGenerationExtension(jsOnly = true) {

    override fun generate(
        moduleFragment: IrModuleFragment,
        pluginContext: SeskarPluginContext,
    ) {
        moduleFragment.transformChildren(ServiceWorkerTransformer(pluginContext), null)
    }
}
