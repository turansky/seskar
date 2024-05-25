package seskar.compiler.alias.extensions

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.config.CompilerConfiguration
import seskar.compiler.common.extensions.JsCompilerPluginRegistrar

class AliasRegistrar : JsCompilerPluginRegistrar() {
    override fun ExtensionStorage.registerExtensions(
        configuration: CompilerConfiguration,
    ) {
        IrGenerationExtension.registerExtension(AliasTransformExtension())
    }
}
