package seskar.gradle.plugin

import seskar.gradle.plugin.GenerationOptions.WORKER_FACTORY

data class WorkerData(
    val workerPath: String,
    val export: String,
)

fun WorkerData(
    export: String,
): WorkerData {
    val names = export
        .split(LAZY_DELIMITER)

    require(names.size == 4) {
        "Unable to calculate worker data from export '$export'"
    }

    val (_, fileName, _, _) = names

    return WorkerData(
        workerPath = "./$fileName${WORKER_FACTORY.generatedSuffix}",
        export = export,
    )
}
