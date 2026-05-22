package seskar.gradle.plugin

import org.gradle.api.file.Directory
import org.gradle.api.provider.Provider
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
    if (source !is Provider<*>)
        return source

    return source.map { value ->
        if (value !is Directory)
            return@map value

        value.asFileTree.matching {
            exclude(ORIGINAL_FILE_FILTERS)
        }
    }
}
