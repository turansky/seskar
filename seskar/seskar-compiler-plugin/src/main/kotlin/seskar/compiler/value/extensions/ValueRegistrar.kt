package seskar.compiler.value.extensions

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.com.intellij.mock.MockProject
import org.jetbrains.kotlin.compiler.plugin.ComponentRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.extensions.StorageComponentContainerContributor

class ValueRegistrar : ComponentRegistrar {
    override fun registerProjectComponents(
        project: MockProject,
        configuration: CompilerConfiguration,
    ) {
        StorageComponentContainerContributor.registerExtension(
            project = project,
            extension = ValueStorageComponentContainerContributor(),
        )

        IrGenerationExtension.registerExtension(project, ValueTransformExtension())
    }
}
