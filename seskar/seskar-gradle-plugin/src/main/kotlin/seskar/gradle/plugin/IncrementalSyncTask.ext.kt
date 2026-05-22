package seskar.gradle.plugin

import org.gradle.api.file.Directory
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.internal.provider.ProviderInternal
import org.jetbrains.kotlin.gradle.tasks.IncrementalSyncTask
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

private val ORIGINAL_FILE_FILTERS: List<String> =
    GenerationOptions.entries
        .map { "**/*${it.originalSuffix}" }

internal fun IncrementalSyncTask.excludeOriginalSources() {
    from.setFrom(from.from.map(::withDirectoryFilter))
}

private fun withDirectoryFilter(
    source: Any,
): Any {
    if (!isDirectoryProvider(source))
        return source

    return source.map { value ->
        value.asFileTree.matching {
            exclude(ORIGINAL_FILE_FILTERS)
        }
    }
}

@OptIn(ExperimentalContracts::class)
private fun isDirectoryProvider(
    source: Any?,
): Boolean {
    contract {
        returns(true) implies (source is DirectoryProperty)
    }

    return source is ProviderInternal<*> && source.type == Directory::class.java
}
