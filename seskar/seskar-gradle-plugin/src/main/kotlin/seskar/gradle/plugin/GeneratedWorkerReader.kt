package seskar.gradle.plugin

import seskar.gradle.plugin.Exports.EXPORTS_SECTION_END
import seskar.gradle.plugin.Exports.EXPORTS_SECTION_START
import seskar.gradle.plugin.Exports.parseExports
import java.io.Reader

internal class GeneratedWorkerReader(
    input: Reader,
) : FileTransformReader(
    input = input,
    transformer = GeneratedWorkerTransformer(),
)

private class GeneratedWorkerTransformer :
    FileTransformer {
    private fun transformExportToCall(
        exports: String,
    ): String {
        val factoryExport = parseExports(exports)
            .singleOrNull()
        // TODO: create JS error instead
            ?: error("Unable to find worker call!")

        return "\n\n${factoryExport.localName}()\n"
    }

    override fun transform(
        content: String,
    ): String =
        replaceBlock(
            content = content,
            blockStart = EXPORTS_SECTION_START,
            blockEnd = EXPORTS_SECTION_END,
            transform = ::transformExportToCall,
        )
}
