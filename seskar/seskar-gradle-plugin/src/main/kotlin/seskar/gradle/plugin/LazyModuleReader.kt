package seskar.gradle.plugin

import seskar.gradle.plugin.Exports.EMPTY_EXPORTS
import seskar.gradle.plugin.Exports.getExports
import seskar.gradle.plugin.LazyItemType.LAZY_FUNCTION
import seskar.gradle.plugin.LazyItemType.LAZY_REACT_COMPONENT
import java.io.Reader

internal class LazyModuleReader(
    input: Reader,
) : FileTransformReader(
    input = input,
    transformer = LazyModuleTransformer(),
)

private class LazyModuleTransformer :
    FileTransformer {
    override fun transform(
        content: String,
    ): String {
        val lazyItems = getExports(content)
            .map(::createLazyItem)

        if (lazyItems.isEmpty()) {
            return EMPTY_EXPORTS
        }

        val imports = lazyItems.asSequence()
            .mapNotNull { it.imports }
            .distinct()
            .joinToString("\n")

        return sequenceOf(imports)
            .plus(lazyItems.map { it.body })
            .joinToString("\n\n")
    }
}

private val LAZY_ITEM_FACTORY_MAP = mapOf(
    LAZY_FUNCTION to LazyFunctionFactory(),
    LAZY_REACT_COMPONENT to ReactLazyComponentFactory(),
)

private fun createLazyItem(
    export: String,
): LazyItem {
    val data = LazyItemData(export)
    val factory = LAZY_ITEM_FACTORY_MAP.getValue(data.type)
    return factory.create(data)
}
