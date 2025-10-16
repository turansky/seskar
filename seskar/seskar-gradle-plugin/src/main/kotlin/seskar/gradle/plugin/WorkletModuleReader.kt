package seskar.gradle.plugin

import seskar.gradle.plugin.Exports.EMPTY_EXPORTS
import seskar.gradle.plugin.Exports.getExports
import java.io.Reader

internal class WorkletModuleReader(
    input: Reader,
) : FileTransformReader(
    input = input,
    transformer = WorkletModuleTransformer(),
)

private class WorkletModuleTransformer :
    FileTransformer {
    override fun transform(
        content: String,
    ): String {
        val exports = getExports(content)
        if (exports.isEmpty())
            return EMPTY_EXPORTS

        val data = WorkletModuleData(
            exports.singleOrNull()
                ?: error("Unable to find worklet module export!")
        )

        // language=javascript
        return """
        export const ${data.export} = () => {
            // workaround for Vite/Webpack
            class Worker {
                constructor(url) { 
                    this.url = url 
                }
            }

            return new Worker(new URL("${data.modulePath}", import.meta.url), { type: "module"}).url
        }
        """.trimIndent()
    }
}
