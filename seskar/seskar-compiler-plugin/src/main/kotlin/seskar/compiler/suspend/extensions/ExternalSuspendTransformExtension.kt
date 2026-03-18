package seskar.compiler.suspend.extensions

import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.visitors.transformChildrenVoid
import seskar.compiler.common.backend.SeskarPluginContext
import seskar.compiler.common.extensions.SeskarGenerationExtension
import seskar.compiler.suspend.backend.ExternalSuspendTransformer

internal class ExternalSuspendTransformExtension :
    SeskarGenerationExtension() {

    override fun generate(
        moduleFragment: IrModuleFragment,
        pluginContext: SeskarPluginContext,
    ) {
        moduleFragment.transformChildrenVoid(ExternalSuspendTransformer(pluginContext))
    }
}
