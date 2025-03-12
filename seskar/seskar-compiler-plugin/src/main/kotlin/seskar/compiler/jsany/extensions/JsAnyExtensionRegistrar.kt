package seskar.compiler.jsany.extensions

import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar

class JsAnyExtensionRegistrar : FirExtensionRegistrar() {
    override fun ExtensionRegistrarContext.configurePlugin() {
        +::JsAnySupertypeGenerationExtension
    }
}
