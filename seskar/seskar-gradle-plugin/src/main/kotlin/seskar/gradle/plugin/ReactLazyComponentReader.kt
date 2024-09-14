package seskar.gradle.plugin

import seskar.gradle.plugin.Components.ORIGINAL_COMPONENT_SUFFIX
import java.io.FilterReader
import java.io.Reader
import java.io.StringReader
import java.io.StringWriter

internal class ReactLazyComponentReader(
    input: Reader,
) : FilterReader(lazyComponentTransformer(input))

private fun lazyComponentTransformer(
    input: Reader,
): Reader {
    val writer = StringWriter()
    input.transferTo(writer)

    val componentProvider = getComponentProvider(writer.toString())
        ?: return StringReader("export {}")

    val componentName = componentProvider
        .removePrefix("get_")
        .substringBefore("__react__component")

    val originalComponentPath = "./$componentName$ORIGINAL_COMPONENT_SUFFIX"

    // language=javascript
    val proxyBody = """
    import { lazy } from "react"

    const component = lazy(() => 
        import("$originalComponentPath")
            .then(module => module.${componentProvider}())
            .then(component => ({ default: component }))
    )
    
    const provider = () => component
    
    export {
        provider as $componentProvider,
    }
    """.trimIndent()

    return StringReader(proxyBody)
}

private fun getComponentProvider(
    content: String,
): String? {
    val exports = content
        .substringAfter("\nexport {", "")
        .substringBefore("\n};", "")
        .splitToSequence("\n")
        .map { it.trim() }
        .map { it.removeSuffix(",") }
        .filter { it.isNotEmpty() }
        .map { it.substringAfter(" as ") }
        .toList()

    if (exports.isEmpty())
        return null

    val provider = exports.singleOrNull()
    if (provider != null)
        return provider

    error(
        "Unable to create lazy component wrapper from following content:" +
                "\n-----------------\n" +
                content +
                "\n-----------------\n",
    )
}
