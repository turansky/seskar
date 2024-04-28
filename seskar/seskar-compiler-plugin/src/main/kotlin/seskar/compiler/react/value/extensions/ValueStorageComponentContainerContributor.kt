package seskar.compiler.react.value.extensions

import org.jetbrains.kotlin.container.StorageComponentContainer
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.extensions.StorageComponentContainerContributor
import org.jetbrains.kotlin.platform.TargetPlatform

class ValueStorageComponentContainerContributor : StorageComponentContainerContributor {
    override fun registerModuleComponents(
        container: StorageComponentContainer,
        platform: TargetPlatform,
        moduleDescriptor: ModuleDescriptor,
    ) {
        // TODO: implement
        // container.useInstance(ValueDeclarationChecker)
    }
}
