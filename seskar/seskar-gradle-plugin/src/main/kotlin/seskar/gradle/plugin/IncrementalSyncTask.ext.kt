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
    val initialSources = from.from

    val initialCompileDirectoryProvider = initialSources
        .singleOrNull(::isKotlinCompileDirectoryProvider)
        ?: error("Unable to find single compile directory provider!")

    val newCompileDirectoryProvider = initialCompileDirectoryProvider
        .let { it as Provider<*> }
        .map { it as Directory }
        .map { directory ->
            directory.asFileTree.matching {
                exclude(ORIGINAL_FILE_FILTERS)
            }
        }

    val newSources = initialSources
        .minus(initialCompileDirectoryProvider)
        .plus(newCompileDirectoryProvider)

    from.setFrom(newSources)
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
