package seskar.gradle.plugin

import java.io.FilterReader
import java.io.Reader
import java.io.StringReader
import java.io.StringWriter

internal abstract class FileTransformReader(
    input: Reader,
    transformer: FileTransformer,
) : FilterReader(createReader(input, transformer))

private fun createReader(
    input: Reader,
    transformer: FileTransformer,
): Reader {
    val newBody = StringWriter()
        .also(input::transferTo)
        .toString()
        .let(transformer::transform)

    return StringReader(newBody)
}
