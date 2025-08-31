package seskar.gradle.plugin

import seskar.gradle.plugin.Exports.EMPTY_EXPORTS
import seskar.gradle.plugin.Exports.getExports
import java.io.Reader

internal class ServiceWorkerModuleReader(
    input: Reader,
) : FileTransformReader(
    input = input,
    transformer = ServiceWorkerModuleTransformer(),
)

private class ServiceWorkerModuleTransformer :
    FileTransformer {
    override fun transform(
        content: String,
    ): String {
        val exports = getExports(content)
        if (exports.isEmpty())
            return EMPTY_EXPORTS

        val data = ServiceWorkerModuleData(
            exports.singleOrNull()
                ?: error("Unable to find worklet module export!")
        )

        // language=javascript
        return """
        export const ${data.export} = () => {
            return new URL("${data.modulePath}", import.meta.url)
        }
        """.trimIndent()
    }
}
