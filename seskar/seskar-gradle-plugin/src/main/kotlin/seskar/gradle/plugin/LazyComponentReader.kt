package seskar.gradle.plugin

import java.io.FilterReader
import java.io.Reader
import java.io.StringReader
import java.io.StringWriter

internal class LazyComponentReader(
    input: Reader,
) : FilterReader(lazyComponentTransformer(input))

private fun lazyComponentTransformer(
    input: Reader,
): Reader {
    val writer = StringWriter()
    input.transferTo(writer)

    val componentProvider = getComponentProvider(writer.toString())
        ?: return StringReader("export {}")

    // language=javascript
    val proxyBody = """
    import { lazy } from "react"

    const component = lazy(
        () => Promise.resolve({
            default: () => "LAZY COMPONENT $componentProvider !!!"
            /* default: componentProvider.get(), */
        })
    )
    
    const provider = {
        get: () => component
    }
    
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
