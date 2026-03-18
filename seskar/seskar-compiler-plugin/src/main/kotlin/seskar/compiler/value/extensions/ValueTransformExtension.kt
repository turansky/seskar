package seskar.compiler.value.extensions

import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.visitors.transformChildrenVoid
import seskar.compiler.common.backend.SeskarPluginContext
import seskar.compiler.common.extensions.SeskarGenerationExtension
import seskar.compiler.value.backend.ValueTransformer

internal class ValueTransformExtension :
    SeskarGenerationExtension() {

    override fun generate(
        moduleFragment: IrModuleFragment,
        pluginContext: SeskarPluginContext,
    ) {
        moduleFragment.transformChildrenVoid(ValueTransformer(pluginContext))
    }
}
