package seskar.compiler.native.extensions

import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.visitors.transformChildrenVoid
import seskar.compiler.common.backend.SeskarPluginContext
import seskar.compiler.common.extensions.SeskarGenerationExtension
import seskar.compiler.native.backend.NativeTransformer

internal class NativeTransformExtension :
    SeskarGenerationExtension() {

    override fun generate(
        moduleFragment: IrModuleFragment,
        pluginContext: SeskarPluginContext,
    ) {
        moduleFragment.transformChildrenVoid(NativeTransformer(pluginContext))
    }
}
