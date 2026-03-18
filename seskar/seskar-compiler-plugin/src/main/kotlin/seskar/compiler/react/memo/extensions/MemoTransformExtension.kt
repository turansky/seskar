package seskar.compiler.react.memo.extensions

import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.visitors.transformChildrenVoid
import seskar.compiler.common.backend.SeskarPluginContext
import seskar.compiler.common.extensions.SeskarGenerationExtension
import seskar.compiler.react.memo.backend.MemoTransformer

internal class MemoTransformExtension :
    SeskarGenerationExtension(jsOnly = true) {

    override fun generate(
        moduleFragment: IrModuleFragment,
        pluginContext: SeskarPluginContext,
    ) {
        moduleFragment.transformChildrenVoid(MemoTransformer(pluginContext))
    }
}
