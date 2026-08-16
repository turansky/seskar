package seskar.compiler.union.extensions

import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar

class UnionExtensionRegistrar : FirExtensionRegistrar() {
    override fun ExtensionRegistrarContext.configurePlugin() {
        +::UnionCompanionGenerationExtension

        // services
        +::UnionExtensionSessionComponent
    }
}
