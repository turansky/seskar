package seskar.gradle.plugin

import org.gradle.api.file.Directory
import org.gradle.api.internal.provider.ValueSupplier
import org.gradle.api.internal.provider.ValueSupplier.TaskProducer
import org.gradle.api.provider.Provider
import org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile
import org.jetbrains.kotlin.gradle.tasks.IncrementalSyncTask

private val ORIGINAL_FILE_FILTERS: List<String> =
    GenerationOptions.entries
        .map { "**/*${it.originalSuffix}" }

internal fun IncrementalSyncTask.excludeOriginalSources() {
    from.setFrom(from.from.map(::withDirectoryFilter))
}

private fun withDirectoryFilter(
    source: Any,
): Any {
    if (!isKotlinCompileDirectoryProvider(source))
        return source

    return (source as Provider<*>)
        .map { it as Directory }
        .map { directory ->
            directory.asFileTree.matching {
                exclude(ORIGINAL_FILE_FILTERS)
            }
        }
}

private fun isKotlinCompileDirectoryProvider(
    source: Any,
): Boolean {
    if (source !is Provider<*>)
        return false

    if (source !is ValueSupplier)
        return false

    val producer = source.producer as? TaskProducer
        ?: return false

    val values = mutableListOf<Boolean>()
    producer.visitContentProducerTasks {
        values.add(this is KotlinJsCompile)
    }

    if (values.none { it })
        return false

    require(values.size == 1) {
        "Expected exactly one 'KotlinJsCompile' task"
    }

    return true
}
