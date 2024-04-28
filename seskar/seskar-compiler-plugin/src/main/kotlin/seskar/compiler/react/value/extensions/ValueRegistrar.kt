package seskar.compiler.react.value.extensions

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.extensions.StorageComponentContainerContributor

class ValueRegistrar : CompilerPluginRegistrar() {
    override val supportsK2: Boolean = true

    override fun ExtensionStorage.registerExtensions(
        configuration: CompilerConfiguration,
    ) {
        StorageComponentContainerContributor.registerExtension(
            extension = ValueStorageComponentContainerContributor(),
        )

        IrGenerationExtension.registerExtension(ValueTransformExtension())
    }
}
