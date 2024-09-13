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
    val content = writer.toString()

    return StringReader("""import { lazy } from "react"""" + "\n\n" + content)
}
