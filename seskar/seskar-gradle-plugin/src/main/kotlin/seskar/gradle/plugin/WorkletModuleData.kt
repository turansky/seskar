package seskar.gradle.plugin

import seskar.gradle.plugin.GenerationOptions.WORKLET_MODULE

data class WorkletModuleData(
    val modulePath: String,
    val export: String,
)

fun WorkletModuleData(
    export: String,
): WorkletModuleData {
    val names = export
        .split(LAZY_DELIMITER)

    require(names.size == 4) {
        "Unable to calculate worklet module data from export '$export'"
    }

    val (_, fileName, _, _) = names

    return WorkletModuleData(
        modulePath = "./$fileName${WORKLET_MODULE.generatedSuffix}",
        export = export,
    )
}
