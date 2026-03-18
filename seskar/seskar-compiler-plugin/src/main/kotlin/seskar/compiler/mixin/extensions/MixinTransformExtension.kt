package seskar.compiler.mixin.extensions

import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.visitors.transformChildrenVoid
import seskar.compiler.common.backend.SeskarPluginContext
import seskar.compiler.common.extensions.SeskarGenerationExtension
import seskar.compiler.mixin.backend.MixinTransformer

internal class MixinTransformExtension :
    SeskarGenerationExtension() {

    override fun generate(
        moduleFragment: IrModuleFragment,
        pluginContext: SeskarPluginContext,
    ) {
        moduleFragment.transformChildrenVoid(MixinTransformer())
    }
}
