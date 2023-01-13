package seskar.compiler.react.extensions

import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.extensions.StorageComponentContainerContributor

class ReactRegistrar : CompilerPluginRegistrar() {
    override val supportsK2: Boolean = false

    override fun ExtensionStorage.registerExtensions(
        configuration: CompilerConfiguration,
    ) {
        StorageComponentContainerContributor.registerExtension(
            extension = ReactStorageComponentContainerContributor(),
        )
    }
}
