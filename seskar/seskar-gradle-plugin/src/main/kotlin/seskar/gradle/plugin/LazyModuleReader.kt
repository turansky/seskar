package seskar.gradle.plugin

import seskar.gradle.plugin.LazyItemType.LAZY_FUNCTION
import seskar.gradle.plugin.LazyItemType.LAZY_REACT_COMPONENT
import java.io.FilterReader
import java.io.Reader
import java.io.StringReader
import java.io.StringWriter

internal class LazyModuleReader(
    input: Reader,
) : FilterReader(lazyComponentTransformer(input))

private fun lazyComponentTransformer(
    input: Reader,
): Reader {
    val writer = StringWriter()
    input.transferTo(writer)

    val lazyItems = getExports(writer.toString())
        .map(::createLazyItem)

    if (lazyItems.isEmpty()) {
        return StringReader("export {}")
    }

    val imports = lazyItems.asSequence()
        .mapNotNull { it.imports }
        .distinct()
        .joinToString("\n")

    val proxyBody = sequenceOf(imports)
        .plus(lazyItems.map { it.body })
        .joinToString("\n\n")

    return StringReader(proxyBody)
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

private fun getExports(
    content: String,
): List<String> =
    content
        .substringAfter("\nexport {", "")
        .substringBefore("\n};", "")
        .splitToSequence("\n")
        .map { it.trim() }
        .map { it.removeSuffix(",") }
        .filter { it.isNotEmpty() }
        .map { it.substringAfter(" as ") }
        .toList()
