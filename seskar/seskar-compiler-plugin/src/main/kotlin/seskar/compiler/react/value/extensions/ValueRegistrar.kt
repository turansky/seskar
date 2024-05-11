package seskar.compiler.react.value.extensions

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.config.CompilerConfiguration
import seskar.compiler.common.extensions.JsCompilerPluginRegistrar

class ValueRegistrar : JsCompilerPluginRegistrar() {
    override fun ExtensionStorage.registerExtensions(
        configuration: CompilerConfiguration,
    ) {
        IrGenerationExtension.registerExtension(ValueTransformExtension())
    }
}
