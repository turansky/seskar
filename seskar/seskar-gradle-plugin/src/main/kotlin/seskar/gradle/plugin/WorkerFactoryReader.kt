package seskar.gradle.plugin

import seskar.gradle.plugin.Exports.EMPTY_EXPORTS
import seskar.gradle.plugin.Exports.getExports
import java.io.Reader

internal class WorkerFactoryReader(
    input: Reader,
) : FileTransformReader(
    input = input,
    transformer = WorkerFactoryTransformer(),
)

private class WorkerFactoryTransformer :
    FileTransformer {
    override fun transform(
        content: String,
    ): String {
        val exports = getExports(content)
        if (exports.isEmpty())
            return EMPTY_EXPORTS

        val data = WorkerData(
            exports.singleOrNull()
                ?: error("Unable to find worker factory export!")
        )

        // language=javascript
        return """
        export const ${data.export} = () => {
            return new Worker("${data.workerPath}", { type: "module" })
        }
        """.trimIndent()
    }
}
