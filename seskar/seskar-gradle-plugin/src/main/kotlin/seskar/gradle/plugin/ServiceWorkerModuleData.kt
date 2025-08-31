package seskar.gradle.plugin

import seskar.gradle.plugin.ServiceWorkers.GENERATED_SERVICE_WORKER_MODULE_SUFFIX

data class ServiceWorkerModuleData(
    val modulePath: String,
    val export: String,
)

fun ServiceWorkerModuleData(
    export: String,
): ServiceWorkerModuleData {
    val names = export
        .split(LAZY_DELIMITER)

    require(names.size == 4) {
        "Unable to calculate worklet module data from export '$export'"
    }

    val (_, fileName, _, _) = names

    return ServiceWorkerModuleData(
        modulePath = "./$fileName$GENERATED_SERVICE_WORKER_MODULE_SUFFIX",
        export = export,
    )
}
