package seskar.compiler.jsany.extensions

import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrarAdapter
import seskar.compiler.common.extensions.JsCompilerPluginRegistrar

class JsAnyPluginRegistrar : JsCompilerPluginRegistrar() {
    override fun ExtensionStorage.registerExtensions(configuration: CompilerConfiguration) {
        FirExtensionRegistrarAdapter.registerExtension(JsAnyExtensionRegistrar())
    }
}
