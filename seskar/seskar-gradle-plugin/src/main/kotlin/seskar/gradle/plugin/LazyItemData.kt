package seskar.gradle.plugin

import seskar.gradle.plugin.LazyItemType.LAZY_FUNCTION
import seskar.gradle.plugin.LazyItemType.LAZY_REACT_COMPONENT
import seskar.gradle.plugin.Modules.ORIGINAL_MODULE_SUFFIX

data class LazyItemData(
    val name: String,
    val originalFilePath: String,
    val type: LazyItemType,
    val export: String,
)

fun LazyItemData(
    export: String,
): LazyItemData {
    val names = export
        .split(LAZY_DELIMITER)

    require(names.size == 5) {
        "Unable to calculate lazy item from export '$export'"
    }

    val (_, fileName, name, typeId, _) = names
    val type = when (typeId) {
        "lazy__function" -> LAZY_FUNCTION
        "react__component" -> LAZY_REACT_COMPONENT

        else -> error("Invalid type id: '$typeId'")
    }

    return LazyItemData(
        name = name,
        originalFilePath = "./$fileName$ORIGINAL_MODULE_SUFFIX",
        type = type,
        export = export,
    )
}
