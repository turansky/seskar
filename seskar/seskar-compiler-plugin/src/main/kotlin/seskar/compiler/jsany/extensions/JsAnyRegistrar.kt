package seskar.compiler.jsany.extensions

import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar

class JsAnyRegistrar : FirExtensionRegistrar() {
    override fun ExtensionRegistrarContext.configurePlugin() {
        +::JsAnySupertypeGenerationExtension
    }
}
